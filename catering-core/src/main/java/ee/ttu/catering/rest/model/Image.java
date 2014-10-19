package ee.ttu.catering.rest.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue
	private int ID;
	
	@Column(nullable = false)
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] image;

	public long getId() {
		return ID;
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
