package cn.enaium.community.mapper;

import cn.enaium.community.model.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Enaium
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
    List<PermissionEntity> selectListByUserId(Long userId);
}




