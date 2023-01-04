package cn.enaium.community.service;

import cn.enaium.community.model.entity.RoleEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Enaium
 */
public interface RoleService extends IService<RoleEntity> {

    Result<RoleEntity> info(ParamMap<String, Object> params);

    Result<Object> update(ParamMap<String, Object> params);

    Result<List<RoleEntity>> roles();
}
