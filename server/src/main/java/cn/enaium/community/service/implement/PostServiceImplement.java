/*
 * Copyright (c) 2022 Enaium
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 */

package cn.enaium.community.service.implement;

import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.community.mapper.CategoryMapper;
import cn.enaium.community.mapper.PostMapper;
import cn.enaium.community.mapper.UserMapper;
import cn.enaium.community.model.entity.PostEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.service.PostService;
import cn.enaium.community.util.AuthUtil;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@Service
public class PostServiceImplement extends ServiceImpl<PostMapper, PostEntity> implements PostService {

    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public PostServiceImplement(PostMapper postMapper, UserMapper userMapper, CategoryMapper categoryMapper) {
        this.postMapper = postMapper;
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    public Result<Page<PostEntity>> posts(ParamMap<String, Object> params) {
        return Result.success(postMapper.selectPage(new Page<>(params.getInt("current", 1), Math.min(params.getInt("size", 10), 20)), queryWrapper(query -> {

            if (StpUtil.hasPermission("post.view.delete")) {

                if (params.has("delete")) {
                    query.eq("del", params.getBoolean("delete"));
                }

            } else {
                query.eq("del", false);
            }

            if (params.has("title")) {
                val title = params.getString("title");
                if (title.isBlank() && query.isEmptyOfWhere()) {
                    query.ne("0", "0");
                } else {
                    query.like("title", title);
                }
            }

            if (params.has("viewCount")) {
                query.ge("view_count", params.getInt("viewCount"));
            }

            if (params.has("commentCount")) {
                query.ge("comment_count", params.getInt("commentCount"));
            }

            if (params.has("voteUp")) {
                query.ge("vote_up", params.getInt("voteUp"));
            }

            if (params.has("voteDown")) {
                query.ge("vote_down", params.getInt("voteDown"));
            }

            if (params.has("createTime")) {
                query.ge("create_time", new Date(params.getLong("createTime")));
            }

            if (params.has("updateTime")) {
                query.ge("update_time", new Date(params.getLong("updateTime")));
            }

            boolean noDraft = true;

            if (params.has("categoryId")) {
                query.eq("category_id", params.getInt("categoryId"));
            }

            if (params.has("userId")) {
                val userId = params.getLong("userId");
                if (userId == AuthUtil.getId()) {
                    noDraft = false;
                }
                query.eq("user_id", userId);
            }

            if (StpUtil.hasPermission("post.view.draft")) {
                if (params.has("draft")) {
                    query.eq("draft", params.getBoolean("draft"));
                }
            } else {
                if (noDraft) {
                    query.eq("draft", false);
                }
            }


            query.orderByDesc("update_time");
        })));
    }

    public Result<Object> publish(ParamMap<String, Object> params) {
        val postEntity = new PostEntity();
        if (params.has("categoryId")) {
            val categoryId = params.getInt("categoryId");
            if (categoryMapper.selectById(categoryId) == null) {
                return Result.fail(Result.Code.CATEGORY_NOT_EXIST);
            }
            postEntity.setCategoryId(categoryId);
        }

        if (params.has("title")) {
            val title = params.getString("title");

            if (title.isBlank()) {
                return Result.fail(Result.Code.TITLE_BLANK);
            }

            if (title.isBlank()) {
                return Result.fail(Result.Code.CONTENT_BLANK);
            }
            postEntity.setTitle(title);
        }

        if (params.has("content")) {
            postEntity.setContent(params.getString("content"));
        }


        if (params.has("draft")) {
            postEntity.setDraft(params.getBoolean("draft"));
        }

        if (params.has("del")) {
            postEntity.setDel(params.getBoolean("del"));
        }

        postEntity.setUpdateTime(new Date());

        if (params.has("id")) {
            val post = postMapper.selectById(params.getLong("id"));
            if (post == null) {
                return Result.fail(Result.Code.POST_NOT_EXIST);
            }

            if (post.getUserId() != StpUtil.getLoginIdAsLong() && StpUtil.hasRoleOr("user", "banned")) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }

            postEntity.setId(params.getLong("id"));
            postMapper.updateById(postEntity);
        } else {
            postEntity.setCreateTime(new Date());
            postEntity.setUserId(StpUtil.getLoginIdAsLong());
            postMapper.insert(postEntity);
            val userEntity = userMapper.selectById(AuthUtil.getId());
            userEntity.setPostCount(new AtomicInteger(userEntity.getPostCount()).incrementAndGet());
            userMapper.updateById(userEntity);
        }

        return Result.success();
    }

    public Result<PostEntity> info(ParamMap<String, Object> params) {
        val id = params.getLong("id");
        val postEntity = postMapper.selectById(id);

        if (postEntity == null) {
            return Result.fail(Result.Code.POST_NOT_EXIST);
        }

        if (postEntity.getDraft()) {
            if (!StpUtil.hasPermission("post.view.draft") || !postEntity.getUserId().equals(AuthUtil.getId())) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }
        }

        if (postEntity.getDel()) {
            if (!StpUtil.hasPermission("post.view.delete")) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }
        }


        postEntity.setViewCount(new AtomicInteger(postEntity.getViewCount()).incrementAndGet());
        postMapper.updateById(postEntity);
        return Result.success(postEntity);
    }

    public Result<Object> update(ParamMap<String, Object> params) {
        val entity = postMapper.selectById(params.getLong("id"));
        if (params.has("voteUp")) {
            entity.setVoteUp(new AtomicInteger(entity.getVoteUp()).incrementAndGet());
        } else if (params.has("voteDown")) {
            entity.setVoteDown(new AtomicInteger(entity.getVoteDown()).incrementAndGet());
        }
        entity.setUpdateTime(new Date());
        postMapper.updateById(entity);
        return Result.success();
    }
}