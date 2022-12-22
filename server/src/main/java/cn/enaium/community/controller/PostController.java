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

import cn.enaium.community.mapper.CategoryMapper;
import cn.enaium.community.mapper.PostMapper;
import cn.enaium.community.model.entity.CategoryEntity;
import cn.enaium.community.model.entity.PostEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.ParamMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final CategoryMapper categoryMapper;
    private final PostMapper postMapper;


    public PostController(CategoryMapper categoryMapper, PostMapper postMapper) {
        this.categoryMapper = categoryMapper;
        this.postMapper = postMapper;
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
    public Result<List<PostEntity>> posts(@RequestBody ParamMap<String, Object> params) {
        return Result.success(postMapper.selectList(queryWrapper(query -> {
            if (params.containsKey("category")) {
                query.eq("category_id", params.getString("category"));
            }

            if (params.containsKey("user_id")) {
                query.eq("user_id", params.getString("user"));
            }
        })));
    }
}
