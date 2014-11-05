package ee.ttu.catering.rest.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	private String name;

	private String fileName;

	@Column(nullable = false)
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] image;

	public Image() {
	}

	public Image(String name, byte[] image) {
		this.name = name;
		this.image = image;
	}

	public Image(String name, byte[] image, String fileName) {
		this(name, image);
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
