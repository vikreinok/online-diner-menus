package ee.ttu.catering.rest.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import ee.ttu.catering.rest.model.base.IdEntity;


@Entity
@Table(name="menu")
public class Menu extends IdEntity {
	
	private static final long serialVersionUID = 4638627189448098616L;

	@NotBlank
	@Length(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	@PrePersist
	void created() {
		this.created = this.modifyDate = new Date();
	}

	@PreUpdate
	void updated() {
		this.modifyDate = new Date();
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="menu", fetch=FetchType.EAGER)
	@OrderBy("name ASC")
	private Collection<MenuItem> menuItems;

	
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

	public Collection<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Collection<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	public void addMenuItem( MenuItem  menuItem) {
		this.menuItems.add(menuItem);
	}
	
	public void addMenuItems(Collection<MenuItem> menuItems) {
		this.menuItems.addAll(menuItems);
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", created=" + created + ", modifyDate="
				+ modifyDate + ", menuItems="
				+ menuItems + "]";
	}
	
	
}
