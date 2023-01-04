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

import cn.enaium.community.mapper.RoleMapper;
import cn.enaium.community.model.entity.RoleEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.service.RoleService;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Enaium
 */
@Service
public class RoleServiceImplement extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImplement(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public Result<RoleEntity> info(ParamMap<String, Object> params) {
        val roleEntity = roleMapper.selectByUserId(params.getLong("id"));
        if (roleEntity == null) {
            return Result.fail(Result.Code.USER_NOT_EXIST);
        }
        return Result.success(roleEntity);
    }

    public Result<Object> update(ParamMap<String, Object> params) {
        val id = params.getLong("id");
        val role = params.getInt("role");
        roleMapper.updateByUserId(id, role);
        return Result.success();
    }

    public Result<List<RoleEntity>> roles() {
        return Result.success(roleMapper.selectList(null));
    }
}




