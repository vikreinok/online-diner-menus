package ee.ttu.catering.rest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ee.ttu.catering.rest.model.base.IdEntity;


@Entity
@Table(name="menu")
public class Menu extends IdEntity {
	
	private static final long serialVersionUID = 4638627189448098616L;

	@NotBlank
	@Length(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<MenuItem> menuItems;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<MenuComment> menuComments;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private Diner diner;
	
	@PrePersist
	void created() {
		this.created = this.modifyDate = new Date();
	}

	@PreUpdate
	void updated() {
		this.modifyDate = new Date();
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	
	public void addMenuComment(MenuComment menuComment) {
		menuComments.add(menuComment);
	}
	
	// ---------------- Setters and Getters ----------------
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Menu update(Menu example) {
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

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<MenuComment> getMenuComments() {
		return menuComments;
	}

	public void setMenuComments(List<MenuComment> menuComments) {
		this.menuComments = menuComments;
	}

	public Diner getDiner() {
		return diner;
	}

	public void setDiner(Diner diner) {
		this.diner = diner;
	}

}
