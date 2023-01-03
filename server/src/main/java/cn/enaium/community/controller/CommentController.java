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

import cn.enaium.community.annotation.RequestParamMap;
import cn.enaium.community.mapper.CommentMapper;
import cn.enaium.community.model.entity.CommentEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.AuthUtil;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentMapper commentMapper;

    public CommentController(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @PostMapping("/publish")
    public Result<Object> publish(@RequestParamMap ParamMap<String, Object> params) {
        val postId = params.getLong("postId");
        val content = params.getString("content");
        val commentEntity = new CommentEntity();
        commentEntity.setPostId(postId);
        commentEntity.setUserId(AuthUtil.getId());
        commentEntity.setContent(content);
        commentEntity.setCreateTime(new Date());
        commentEntity.setUpdateTime(new Date());
        commentMapper.insert(commentEntity);
        return Result.success();
    }

    @PostMapping("/comments")
    public Page<CommentEntity> comments(@RequestParamMap ParamMap<String, Object> params) {
        return commentMapper.selectPage(new Page<>(params.getInt("current", 1), Math.min(params.getInt("size", 10), 20)), queryWrapper(wrapper -> {
            if (params.has("postId")) {
                wrapper.eq("post_id", params.getLong("postId"));
            }
        }));
    }

    @PostMapping("/info")
    public Result<CommentEntity> info(@RequestParamMap ParamMap<String, Object> params) {
        return Result.success(commentMapper.selectById(params.getLong("id")));
    }
}
