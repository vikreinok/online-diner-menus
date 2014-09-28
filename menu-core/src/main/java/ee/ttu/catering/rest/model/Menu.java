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
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date created; 
	
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

	@Override
	public String toString() {
		return "Menu [name=" + name + ", created=" + created + ", menuItems="
				+ menuItems + "]";
	}
	

}
