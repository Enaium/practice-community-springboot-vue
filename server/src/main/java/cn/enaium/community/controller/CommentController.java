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

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.enaium.community.annotation.RequestParamMap;
import cn.enaium.community.model.entity.CommentEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.service.CommentService;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/publish")
    @SaCheckPermission("comment.publish")
    public Result<Object> publish(@RequestParamMap ParamMap<String, Object> params) {
        return commentService.publish(params);
    }

    @PostMapping("/comments")
    public Result<Page<CommentEntity>> comments(@RequestParamMap ParamMap<String, Object> params) {
        return commentService.comments(params);
    }


    @PostMapping("/info")
    public Result<CommentEntity> info(@RequestParamMap ParamMap<String, Object> params) {
        return commentService.info(params);
    }

    @PostMapping("/update")
    public Result<Object> update(@RequestParamMap ParamMap<String, Object> params) {
        return commentService.update(params);
    }
}
