package com.example.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="EXAMPLE")
public class Example implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
    @NotBlank
	private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
	public Example update(Example example) {
		this.name = example.getName();
		return this;
	}

    @Override
    public String toString() {
        return "Example{" + "id=" + id + ", name=" + name + '}';
    }
	
        
        
	
}
