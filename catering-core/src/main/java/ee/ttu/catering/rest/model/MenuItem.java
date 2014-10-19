package ee.ttu.catering.rest.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name="menu_item")
public class MenuItem extends IdEntity {

	private static final long serialVersionUID = 1L;

	public static enum Status {
		AVAILABE, OUT;
	}

	private String name;
	
	private Double price;

	@Enumerated(EnumType.STRING)
	@Basic
	private Status status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Menu menu;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	

}