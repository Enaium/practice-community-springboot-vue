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
import cn.enaium.community.mapper.RoleMapper;
import cn.enaium.community.mapper.UserMapper;
import cn.enaium.community.mapper.UserRoleRelationMapper;
import cn.enaium.community.model.entity.UserEntity;
import cn.enaium.community.model.entity.UserRoleRelationEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.service.UserService;
import cn.enaium.community.util.AuthUtil;
import cn.enaium.community.util.DigestUtil;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.enaium.community.util.WrapperUtil.queryWrapper;

/**
 * @author Enaium
 */
@Service
public class UserServiceImplement extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private final UserMapper userMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;
    private final RoleMapper roleMapper;

    public UserServiceImplement(UserMapper userMapper, UserRoleRelationMapper userRoleRelationMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.userRoleRelationMapper = userRoleRelationMapper;
        this.roleMapper = roleMapper;
    }

    public Result<String> register(ParamMap<String, Object> params) {
        val username = params.getString("username");
        val password = params.getString("password");
        val confirmPassword = params.getString("confirmPassword");

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

    public Result<String> login(ParamMap<String, Object> params) {
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

    public Result<UserEntity> info(ParamMap<String, Object> params) {
        if (params.has("id")) {
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

    public Result<Object> update(ParamMap<String, Object> params) {
        val id = params.getLong("id", AuthUtil.getId());

        if (userMapper.selectById(id) == null) {
            return Result.fail(Result.Code.USER_NOT_EXIST);
        }

        if (StpUtil.hasRoleOr("user", "banned")) {
            if (!id.equals(AuthUtil.getId())) {
                return Result.fail(Result.Code.NO_PERMISSION);
            }
        }

        val userEntity = new UserEntity();

        userEntity.setId(id);

        if (params.has("avatar")) {
            val avatar = params.getString("avatar");
            userEntity.setAvatar((avatar == null || avatar.isBlank()) ? null : avatar);
        }

        if (params.has("username")) {
            val username = params.getString("username");

            if (userMapper.selectOne(queryWrapper(query -> query.eq("username", username))) != null) {
                return Result.fail(Result.Code.USER_ALREADY_EXIST);
            }

            if (!username.isBlank()) {
                userEntity.setUsername(username);
            }
        }

        if (params.has("ban")) {
            StpUtil.checkPermission("user.ban");
            val ban = params.getBoolean("ban");
            userEntity.setBanned(ban);
            roleMapper.updateByUserId(id, ban ? 4 : 3);
        }
        userEntity.setUpdateTime(new Date());
        return Result.success(userMapper.updateById(userEntity));
    }

    public Result<Page<UserEntity>> users(ParamMap<String, Object> params) {
        return Result.success(userMapper.selectPage(new Page<>(params.getInt("current", 1), Math.min(params.getInt("size", 10), 20)), queryWrapper(query -> {
            if (params.has("username")) {
                val username = params.getString("username");

                if (!username.isBlank()) {
                    query.like("username", username);
                }
            }
        })));
    }
}




