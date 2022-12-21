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
@TableName(value = "comment")
@Data
public class CommentEntity implements Serializable {
    @TableField(exist = false)

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long user_id;
    private Long post_id;
    private Integer vote_up;
    private Integer vote_down;
    private Date create_time;
    private Date update_time;
    private Integer answer;
    private Integer del;

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
        CommentEntity other = (CommentEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                && (this.getPost_id() == null ? other.getPost_id() == null : this.getPost_id().equals(other.getPost_id()))
                && (this.getVote_up() == null ? other.getVote_up() == null : this.getVote_up().equals(other.getVote_up()))
                && (this.getVote_down() == null ? other.getVote_down() == null : this.getVote_down().equals(other.getVote_down()))
                && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
                && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getPost_id() == null) ? 0 : getPost_id().hashCode());
        result = prime * result + ((getVote_up() == null) ? 0 : getVote_up().hashCode());
        result = prime * result + ((getVote_down() == null) ? 0 : getVote_down().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getDel() == null) ? 0 : getDel().hashCode());
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
        sb.append(", vote_up=").append(vote_up);
        sb.append(", vote_down=").append(vote_down);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", answer=").append(answer);
        sb.append(", del=").append(del);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}