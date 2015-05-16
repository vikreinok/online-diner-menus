package ee.ttu.catering.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.jsondoc.core.annotation.ApiObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.catering.rest.model.base.IdEntity;

@Entity(name = "user")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@ApiObject(name= "User", description="Used to store user credentials for authentication")
public class User extends IdEntity  {

	private static final long serialVersionUID = 4638627189448098616L;
	
    @Column(name = "name", length = 255, unique = true, nullable = false)
    private String name;

    @Column(name = "password", length = 60, nullable = false)
    @JsonIgnore
    private String password;
    
    @Column(name = "fullname", length = 50, nullable = false)
    private String fullname;

    public String geName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
    
}
