package cn.enaium.community.service;

import cn.enaium.community.model.entity.CommentEntity;
import cn.enaium.community.model.result.Result;
import cn.enaium.community.util.ParamMap;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Enaium
 */
public interface CommentService extends IService<CommentEntity> {
    @Transactional
    Result<Object> publish(ParamMap<String, Object> params);

    Result<Page<CommentEntity>> comments(ParamMap<String, Object> params);

    Result<CommentEntity> info(ParamMap<String, Object> params);

    @Transactional
    Result<Object> update(ParamMap<String, Object> params);
}
