package ee.ttu.catering.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name="diner")
public class Diner extends IdEntity {
	
	private static final long serialVersionUID = -7169873625528706006L;

	@NotBlank
	@Length(min=2, max=15, message="Too long name for diner")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@NotBlank
	private String description;
	
	private String picture;
	
	@PrePersist
	void created() {
		this.created = this.modifyDate = new Date();
	}

	@PreUpdate
	void updated() {
		this.modifyDate = new Date();
	}
	
	
	public Diner update(Diner example) {
		this.name = example.getName();
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Diner [name=" + name + ", created=" + created + ", modifyDate="
				+ modifyDate + ", description=" + description + ", picture="
				+ picture + "]";
	}

	
}
