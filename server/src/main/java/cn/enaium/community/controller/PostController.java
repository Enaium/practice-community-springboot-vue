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

package cn.enaium.community.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.enaium.community.annotation.RequestParamMap;
import cn.enaium.community.mapper.CategoryMapper;
import cn.enaium.community.mapper.PostMapper;
import cn.enaium.community.mapper.UserMapper;
import cn.enaium.community.model.entity.CategoryEntity;
import cn.enaium.community.model.entity.PostEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.AuthUtil;
import cn.enaium.community.util.ParamMap;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final CategoryMapper categoryMapper;
    private final PostMapper postMapper;

    private final UserMapper userMapper;


    public PostController(CategoryMapper categoryMapper, PostMapper postMapper, UserMapper userMapper) {
        this.categoryMapper = categoryMapper;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
    }

    /**
     * get all category
     *
     * @return categories
     */
    @GetMapping("/categories")
    public Result<List<CategoryEntity>> categories() {
        return Result.success(categoryMapper.selectList(null));
    }

    /**
     * get all post
     *
     * @param params map param
     * @return all post
     */
    @PostMapping("/posts")
    public Result<List<PostEntity>> posts(@RequestParamMap ParamMap<String, Object> params) {
        return Result.success(postMapper.selectList(queryWrapper(query -> {

            query.eq("del", false);

            boolean noDraft = true;
//            if (params.containsKey("draft")) {
//                val roleList = StpUtil.getRoleList();
//                if (!roleList.contains("admin") && !roleList.contains("super_admin")) {
//                    query.eq("draft", true);
//                }
//            } else {
//                query.eq("draft", false);
//            }

            if (params.containsKey("categoryId")) {
                query.eq("category_id", params.getInt("categoryId"));
            }


            if (params.containsKey("userId")) {
                val userId = params.getLong("userId");
                if (userId == AuthUtil.getId()) {
                    noDraft = false;
                }
                query.eq("user_id", userId);
            }


            if (noDraft) {
                query.eq("draft", false);
            }
        })));
    }

    @PostMapping("/publish")
    public Result<Object> publish(@RequestParamMap ParamMap<String, Object> params) {

        val categoryId = params.getInt("categoryId");
        val title = params.getString("title");
        val content = params.getString("content");
        val draft = params.getBoolean("draft");

        if (title.isBlank()) {
            return Result.fail(Result.Code.TITLE_BLANK);
        }

        if (title.isBlank()) {
            return Result.fail(Result.Code.CONTENT_BLANK);
        }

        if (categoryMapper.selectById(categoryId) == null) {
            return Result.fail(Result.Code.CATEGORY_NOT_EXIST);
        }

        val postEntity = new PostEntity();

        postEntity.setUserId(StpUtil.getLoginIdAsLong());
        postEntity.setCategoryId(categoryId);
        postEntity.setTitle(title);
        postEntity.setContent(content);
        postEntity.setDraft(draft);

        postEntity.setUpdateTime(new Date());

        if (params.containsKey("id")) {
            val post = postMapper.selectById(params.getLong("id"));
            if (post == null) {
                return Result.fail(Result.Code.POST_NOT_EXIST);
            }

            if (post.getUserId() != StpUtil.getLoginIdAsLong()) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }

            postEntity.setId(params.getLong("id"));
            postMapper.updateById(postEntity);
        } else {
            postEntity.setCreateTime(new Date());
            postMapper.insert(postEntity);
            val userEntity = userMapper.selectById(StpUtil.getLoginIdAsLong());
            userEntity.setPostCount(new AtomicInteger(userEntity.getPostCount()).incrementAndGet());
            userMapper.updateById(userEntity);
        }

        return Result.success();
    }

    @PostMapping("/info")
    public Result<PostEntity> post(@RequestParamMap ParamMap<String, Object> params) {
        val id = params.getLong("id");
        val postEntity = postMapper.selectById(id);
        postEntity.setViewCount(new AtomicInteger(postEntity.getViewCount()).incrementAndGet());
        postMapper.updateById(postEntity);
        return Result.success(postEntity);
    }
}
