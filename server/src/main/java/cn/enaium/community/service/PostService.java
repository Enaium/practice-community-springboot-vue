package cn.enaium.community.service;

import cn.enaium.community.model.entity.PostEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Enaium
 */
public interface PostService extends IService<PostEntity> {

    Result<Page<PostEntity>> posts(ParamMap<String, Object> params);
    @Transactional
    Result<Object> publish(ParamMap<String, Object> params);

    Result<PostEntity> info(ParamMap<String, Object> params);
}
