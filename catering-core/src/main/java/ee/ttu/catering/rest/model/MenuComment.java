package ee.ttu.catering.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ee.ttu.catering.rest.model.base.CommentEntity;
import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;

@Entity
@Table(name="menu_comment")
@ApiObject(name= "Menu comment")
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
