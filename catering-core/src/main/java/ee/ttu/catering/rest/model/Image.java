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
	private byte[] data;

	public Image() {
	}

	public Image(String name, byte[] data) {
		this.name = name;
		this.data = data;
	}

	public Image(String name, byte[] data, String fileName) {
		this(name, data);
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
