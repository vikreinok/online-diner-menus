package ee.ttu.catering.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name = "diner")
public class Diner extends IdEntity {

	private static final long serialVersionUID = -7169873625528706006L;

	@NotBlank
	@Length(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;

	@NotBlank
	@Length(min = 10, max = 50, message = "Descritpion should be between 10 and 50 characters")
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date created;

	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Menu> menu;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DinerComment> dinerComments = new ArrayList<DinerComment>();

	public void addDinerComment(DinerComment dinerComment) {
		dinerComments.add(dinerComment);
	}
	
	private String picture;

	@PrePersist
	void created() {
		this.created = this.modifyDate = new Date();
	}

	@PreUpdate
	void updated() {
		this.modifyDate = new Date();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<DinerComment> getDinerComments() {
		return dinerComments;
	}

	public void setDinerComments(List<DinerComment> dinerComments) {
		this.dinerComments = dinerComments;
	}
	

}
