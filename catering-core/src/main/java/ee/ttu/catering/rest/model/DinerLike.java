package ee.ttu.catering.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ee.ttu.catering.rest.model.base.LikeEntity;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;

@Entity
@Table(name="diner_like")
@ApiObject(name= "Diner like", description="Diner lime information model")
public class DinerLike extends LikeEntity {

	private static final long serialVersionUID = 1L;
	
	@ApiObjectField(description="Many to on relation to diner")
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
