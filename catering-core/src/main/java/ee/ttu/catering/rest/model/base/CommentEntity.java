package ee.ttu.catering.rest.model.base;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@ApiObject(show = false)
@MappedSuperclass
public abstract class CommentEntity extends IdEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiObjectField(description="Comment content")
	private String comment;
	
	@Temporal(TemporalType.DATE)
	private Date created;

	@PrePersist
	void created() {
		this.created = new Date();
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
