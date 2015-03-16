package ee.ttu.catering.rest.model.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdEntity implements Serializable {
	
	protected static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

//  Causes optimistic locking exception: updated by another transaction
//	@Version
//	private Long entityVersion;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

//	public Long getEntityVersion() {
//		return entityVersion;
//	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ":" + id;
	}
}
