package com.example.rest.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.rest.model.base.IdEntity;

@Entity
@Table(name="menu_item")
public class MenuItem extends IdEntity{

	private static final long serialVersionUID = 1L;

	public static enum Status {
		AVAILABE, OUT;
	}

	private String name;

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

	public void setStatus(Status status) {
		this.status = status;
	}

}
