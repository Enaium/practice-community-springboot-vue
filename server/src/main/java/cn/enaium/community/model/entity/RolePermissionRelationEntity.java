package cn.enaium.community.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Enaium
 */
@TableName(value = "role_permission_relation")
@Data
public class RolePermissionRelationEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer role_id;
    private Integer permission_id;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RolePermissionRelationEntity other = (RolePermissionRelationEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRole_id() == null ? other.getRole_id() == null : this.getRole_id().equals(other.getRole_id()))
                && (this.getPermission_id() == null ? other.getPermission_id() == null : this.getPermission_id().equals(other.getPermission_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRole_id() == null) ? 0 : getRole_id().hashCode());
        result = prime * result + ((getPermission_id() == null) ? 0 : getPermission_id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", role_id=").append(role_id);
        sb.append(", permission_id=").append(permission_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}