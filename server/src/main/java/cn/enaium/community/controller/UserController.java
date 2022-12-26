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
import cn.enaium.community.mapper.UserMapper;
import cn.enaium.community.model.entity.UserEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.AuthUtil;
import cn.enaium.community.util.ParamMap;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/info")
    public Result<UserEntity> info(@RequestParamMap ParamMap<String, Object> params) {
        if (params.containsKey("id")) {
            val user = userMapper.selectById(params.getLong("id"));
            if (user == null) {
                return Result.fail(Result.Code.USER_NOT_EXIST);
            }
            user.setPassword(null);
            return Result.success(user);
        }
        val data = userMapper.selectById(StpUtil.getLoginIdAsLong());
        data.setPassword(null);
        return Result.success(data);
    }

    @PostMapping("/update")
    public Result<Object> update(@RequestParamMap ParamMap<String, Object> params) {
//        val id = params.getLong("id");

//        if (id != AuthUtil.getId()) {
//            return Result.fail(Result.Code.NO_PERMISSION);
//        }

        val userEntity = userMapper.selectById(StpUtil.getLoginIdAsLong());
        if (userEntity == null) {
            return Result.fail(Result.Code.USER_NOT_EXIST);
        }

        if (params.containsKey("avatar")) {
            val avatar = params.getString("avatar");
            userEntity.setAvatar((avatar == null || avatar.isBlank()) ? null : avatar);
        }

        if (params.containsKey("username")) {
            val username = params.getString("username");
            if (!username.isBlank()) {
                userEntity.setUsername(username);
            }
        }
        userEntity.setUpdateTime(new Date());
        return Result.success(userMapper.updateById(userEntity));
    }
}
