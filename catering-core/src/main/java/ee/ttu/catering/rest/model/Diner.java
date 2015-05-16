package ee.ttu.catering.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name = "diner")
@ApiObject(name= "Diner", description="A diner information model")
public class Diner extends IdEntity {

	private static final long serialVersionUID = -7169873625528706006L;

	@ApiObjectField(description="Dinerr name as customer recognises it", allowedvalues= "Not blank")
	@NotBlank
	@Length(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;

	@ApiObjectField(description="Diner description", allowedvalues= "Not blank and value length between 10 - 50")
	@NotBlank
	@Length(min = 10, max = 50, message = "Descritpion should be between 10 and 50 characters")
	private String description;
	
	@ApiObjectField()
	@Temporal(TemporalType.DATE)
	private Date created;

	@ApiObjectField()
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@ApiObjectField()
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Image image;
	
	@ApiObjectField()
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Menu> menu;
	
	@ApiObjectField()
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DinerComment> dinerComments = new ArrayList<DinerComment>();
	
	@ApiObjectField()
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DinerLike> dinerLikes = new ArrayList<DinerLike>();

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
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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

	public List<DinerLike> getDinerLikes() {
		return dinerLikes;
	}

	public void setDinerLikes(List<DinerLike> dinerLikes) {
		this.dinerLikes = dinerLikes;
	}
	
	public void addDinerComment(DinerComment dinerComment) {
		dinerComments.add(dinerComment);
	}
	
	public void addDinerLikes(DinerLike dinerLike) {
		dinerLikes.add(dinerLike);
	}

}
