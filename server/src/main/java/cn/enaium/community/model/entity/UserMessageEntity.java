package cn.enaium.community.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Enaium
 */
@TableName(value = "user_message")
@Data
public class UserMessageEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long user_id;
    private Long post_id;
    private Long comment_id;
    private Integer read;
    private Date create_time;

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
        UserMessageEntity other = (UserMessageEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                && (this.getPost_id() == null ? other.getPost_id() == null : this.getPost_id().equals(other.getPost_id()))
                && (this.getComment_id() == null ? other.getComment_id() == null : this.getComment_id().equals(other.getComment_id()))
                && (this.getRead() == null ? other.getRead() == null : this.getRead().equals(other.getRead()))
                && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getPost_id() == null) ? 0 : getPost_id().hashCode());
        result = prime * result + ((getComment_id() == null) ? 0 : getComment_id().hashCode());
        result = prime * result + ((getRead() == null) ? 0 : getRead().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append(", post_id=").append(post_id);
        sb.append(", comment_id=").append(comment_id);
        sb.append(", read=").append(read);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}