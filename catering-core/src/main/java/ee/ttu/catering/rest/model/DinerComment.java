package ee.ttu.catering.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ee.ttu.catering.rest.model.base.CommentEntity;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;

@Entity
@Table(name="diner_comment")
@ApiObject(name= "Diner comment", description="Diner comment model")
public class DinerComment extends CommentEntity {

	private static final long serialVersionUID = 1L;
	
	@ApiObjectField(description="Many to one relation to diener")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Diner diner;
	
	@JsonBackReference
	public Diner getDiner() {
		return diner;
	}

	public void setDiner(Diner diner) {
		this.diner = diner;
	}

}
