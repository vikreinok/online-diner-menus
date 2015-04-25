package ee.ttu.catering.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.catering.rest.model.base.CommentEntity;

@Entity
@Table(name="diner_comment")
public class DinerComment extends CommentEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Diner diner;
	
	@JsonIgnore
	@JsonBackReference
	public Diner getDiner() {
		return diner;
	}

	public void setDiner(Diner diner) {
		this.diner = diner;
	}

}
