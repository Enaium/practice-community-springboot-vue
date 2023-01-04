package cn.enaium.community.service;

import cn.enaium.community.model.entity.UserEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Enaium
 */
public interface UserService extends IService<UserEntity> {
    @Transactional
    Result<String> register(ParamMap<String, Object> params);
    Result<String> login(ParamMap<String, Object> params);
    Result<UserEntity> info(ParamMap<String, Object> params);
    @Transactional
    Result<Object> update(ParamMap<String, Object> params);
    Result<Page<UserEntity>> users(ParamMap<String, Object> params);
}
