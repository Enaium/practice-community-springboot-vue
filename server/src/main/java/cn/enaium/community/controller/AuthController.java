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
import cn.enaium.community.mapper.UserRoleRelationMapper;
import cn.enaium.community.model.entity.UserEntity;
import cn.enaium.community.model.entity.UserRoleRelationEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.DigestUtil;
import cn.enaium.community.util.ParamMap;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserMapper userMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;

    public AuthController(UserMapper userMapper, UserRoleRelationMapper userRoleRelationMapper) {
        this.userMapper = userMapper;
        this.userRoleRelationMapper = userRoleRelationMapper;
    }

    @PostMapping("/register")
    public Result<String> register(@RequestParamMap ParamMap<String, Object> params) {
        val username = params.getString("username");
        val password = params.getString("password");
        val confirmPassword = params.getString("confirm_password");

        if (username.isBlank()) {
            return Result.fail(Result.Code.USERNAME_BLANK);
        }

        if (password.isBlank()) {
            return Result.fail(Result.Code.PASSWORD_BLANK);
        }

        val userEntity = userMapper.selectOne(queryWrapper(query -> query.eq("username", username)));

        if (userEntity != null) {
            return Result.fail(Result.Code.USER_ALREADY_EXIST);
        } else if (!password.equals(confirmPassword)) {
            return Result.fail(Result.Code.PASSWORD_NOT_MATCH);
        }

        val entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(DigestUtil.md5(password));
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());

        userMapper.insert(entity);

        val userRoleRelationEntity = new UserRoleRelationEntity();
        userRoleRelationEntity.setUserId(entity.getId());
        userRoleRelationMapper.insert(userRoleRelationEntity);

        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParamMap ParamMap<String, Object> params) {

        val username = params.getString("username");
        val password = params.getString("password");

        if (username.isBlank()) {
            return Result.fail(Result.Code.USERNAME_BLANK);
        }

        if (password.isBlank()) {
            return Result.fail(Result.Code.PASSWORD_BLANK);
        }

        val userEntity = userMapper.selectOne(queryWrapper(query -> query.eq("username", username)));

        if (userEntity == null) {
            return Result.fail(Result.Code.USER_NOT_EXIST);
        } else if (!DigestUtil.md5(password).equals(userEntity.getPassword())) {
            return Result.fail(Result.Code.PASSWORD_NOT_MATCH);
        }

        userEntity.setUpdateTime(new Date());

        userMapper.updateById(userEntity);

        return Result.success(StpUtil.createLoginSession(userEntity.getId()));
    }

    @GetMapping("/logout")
    public Result<Object> logout() {
        StpUtil.logout();
        return Result.success();
    }

    @GetMapping("/isLogin")
    public Result<Boolean> isLogin() {
        return Result.success(StpUtil.isLogin());
    }

    @GetMapping("/id")
    public Result<Long> id() {
        return Result.success(StpUtil.getLoginIdAsLong());
    }
}
