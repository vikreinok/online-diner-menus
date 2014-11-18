package ee.ttu.catering.rest.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import ee.ttu.catering.rest.model.base.IdEntity;


@Entity
@Table(name="menu")
public class Menu extends IdEntity {
	
	private static final long serialVersionUID = 4638627189448098616L;

	@NotBlank
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	private String username;
	
	private String password;
	
	private String picture;
	
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
				+ modifyDate + ", username=" + username + ", password="
				+ password + ", picture=" + picture + ", menuItems="
				+ menuItems + "]";
	}
	
	

}
