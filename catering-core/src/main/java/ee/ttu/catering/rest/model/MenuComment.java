package ee.ttu.catering.rest.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity
@Table(name="menu_comment")
public class MenuComment extends IdEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String comment;
	
	@Temporal(TemporalType.DATE)
	private Date created;

	@PrePersist
	void created() {
		this.created = new Date();
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Menu menu;
	

	@JsonBackReference
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
