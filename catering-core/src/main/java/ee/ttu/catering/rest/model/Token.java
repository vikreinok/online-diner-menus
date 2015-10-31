package ee.ttu.catering.rest.model;

import ee.ttu.catering.rest.model.base.IdEntity;
import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

@Entity
@Table(name = "token")
@ApiObject(name= "Token", description="Used to persist authentication info")
public class Token extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 64)
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access", nullable = false)
    private Date lastAccess;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void generateRandomValue() {
    	 SecureRandom random = new SecureRandom();

//        String aValue = RandomStringUtils.randomAlphanumeric(64);
        String aValue = new BigInteger(130, random).toString(64);
        setValue(aValue);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

}
