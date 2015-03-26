package ee.ttu.catering.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name="menu_item")
public class MenuItem extends IdEntity {

	private static final long serialVersionUID = 1L;

//	public static enum Status {
//		AVAILABE, OUT;
//	}
	
	private String name;
	
	private Double price;

//	@Enumerated(EnumType.STRING)
//	@Basic
//	private Status status;
	
	@Temporal(TemporalType.DATE)
	private Date created;

	@PrePersist
	void created() {
		this.created = new Date();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Menu menu;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
