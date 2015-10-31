package ee.ttu.catering.rest.model.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class LikeEntity extends IdEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	
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
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
