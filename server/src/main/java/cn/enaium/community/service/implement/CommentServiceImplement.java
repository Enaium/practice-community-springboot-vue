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
import cn.enaium.community.mapper.CommentMapper;
import cn.enaium.community.mapper.PostMapper;
import cn.enaium.community.mapper.UserMapper;
import cn.enaium.community.model.entity.CommentEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.service.CommentService;
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
public class CommentServiceImplement extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    private final CommentMapper commentMapper;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    public CommentServiceImplement(CommentMapper commentMapper, PostMapper postMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
    }

    public Result<Object> publish(ParamMap<String, Object> params) {
        val postId = params.getLong("postId");
        val content = params.getString("content");

        val postEntity = postMapper.selectById(postId);
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

        val commentEntity = new CommentEntity();
        commentEntity.setPostId(postId);
        commentEntity.setUserId(AuthUtil.getId());
        commentEntity.setContent(content);
        commentEntity.setCreateTime(new Date());
        commentEntity.setUpdateTime(new Date());
        commentMapper.insert(commentEntity);

        postEntity.setCommentCount(new AtomicInteger(postEntity.getCommentCount()).incrementAndGet());
        postEntity.setUpdateTime(new Date());
        postMapper.updateById(postEntity);
        val userEntity = userMapper.selectById(AuthUtil.getId());
        userEntity.setCommentCount(new AtomicInteger(userEntity.getCommentCount()).incrementAndGet());
        userMapper.updateById(userEntity);
        return Result.success();
    }

    public Result<Page<CommentEntity>> comments(ParamMap<String, Object> params) {
        return Result.success(commentMapper.selectPage(new Page<>(params.getInt("current", 1), Math.min(params.getInt("size", 10), 20)), queryWrapper(query -> {
            if (StpUtil.hasPermission("post.view.delete")) {
                if (params.has("delete")) {
                    query.eq("del", params.getBoolean("delete"));
                }
            } else {
                query.eq("del", false);
            }

            if (params.has("content")) {
                val content = params.getString("content");
                if (content.isBlank() && query.isEmptyOfWhere()) {
                    query.ne("0", "0");
                } else {
                    query.like("content", content);
                }
            }

            if (params.has("voteUp")) {
                query.ge("vote_up", params.getInt("voteUp"));
            }

            if (params.has("voteDown")) {
                query.ge("vote_down", params.getInt("voteDown"));
            }

            if (params.has("postId")) {
                query.eq("post_id", params.getLong("postId"));
            }

            if (params.has("createTime")) {
                query.ge("create_time", new Date(params.getLong("createTime")));
            }

            if (params.has("updateTime")) {
                query.ge("update_time", new Date(params.getLong("updateTime")));
            }

            query.orderByDesc("update_time");
        })));
    }

    public Result<CommentEntity> info(ParamMap<String, Object> params) {
        return Result.success(commentMapper.selectById(params.getLong("id")));
    }

    public Result<Object> update(ParamMap<String, Object> params) {
        val entity = commentMapper.selectById(params.getLong("id"));
        if (params.has("voteUp")) {
            entity.setVoteUp(new AtomicInteger(entity.getVoteUp()).incrementAndGet());
        } else if (params.has("voteDown")) {
            entity.setVoteDown(new AtomicInteger(entity.getVoteDown()).incrementAndGet());
        }

        if (params.has("del")) {
            if (!StpUtil.hasPermission("comment.delete")) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }

            entity.setDel(params.getBoolean("del"));
        }

        entity.setUpdateTime(new Date());
        commentMapper.updateById(entity);
        return Result.success();
    }
}




