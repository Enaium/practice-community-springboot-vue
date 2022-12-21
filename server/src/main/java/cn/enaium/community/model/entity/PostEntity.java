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
@TableName(value = "post")
@Data
public class PostEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long user_id;
    private Integer category_id;
    private String title;
    private String content;
    private Integer draft;
    private Integer vote_up;
    private Integer vote_down;
    private Integer view_count;
    private Integer comment_count;
    private Date create_time;
    private Date update_time;
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
        PostEntity other = (PostEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                && (this.getCategory_id() == null ? other.getCategory_id() == null : this.getCategory_id().equals(other.getCategory_id()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getDraft() == null ? other.getDraft() == null : this.getDraft().equals(other.getDraft()))
                && (this.getVote_up() == null ? other.getVote_up() == null : this.getVote_up().equals(other.getVote_up()))
                && (this.getVote_down() == null ? other.getVote_down() == null : this.getVote_down().equals(other.getVote_down()))
                && (this.getView_count() == null ? other.getView_count() == null : this.getView_count().equals(other.getView_count()))
                && (this.getComment_count() == null ? other.getComment_count() == null : this.getComment_count().equals(other.getComment_count()))
                && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
                && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getCategory_id() == null) ? 0 : getCategory_id().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDraft() == null) ? 0 : getDraft().hashCode());
        result = prime * result + ((getVote_up() == null) ? 0 : getVote_up().hashCode());
        result = prime * result + ((getVote_down() == null) ? 0 : getVote_down().hashCode());
        result = prime * result + ((getView_count() == null) ? 0 : getView_count().hashCode());
        result = prime * result + ((getComment_count() == null) ? 0 : getComment_count().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
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
        sb.append(", category_id=").append(category_id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", draft=").append(draft);
        sb.append(", vote_up=").append(vote_up);
        sb.append(", vote_down=").append(vote_down);
        sb.append(", view_count=").append(view_count);
        sb.append(", comment_count=").append(comment_count);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", del=").append(del);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}