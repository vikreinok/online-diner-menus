package ee.ttu.catering.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ee.ttu.catering.rest.model.base.CommentEntity;

@Entity
@Table(name="menu_comment")
public class MenuComment extends CommentEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Menu menu;
	
	@JsonBackReference
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	 
}
