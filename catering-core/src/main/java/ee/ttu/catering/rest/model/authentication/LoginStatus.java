package ee.ttu.catering.rest.model.authentication;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * LoginStatus. Simple DTO (Data Transfert Object) used to give a structure to a
 * login status return in the login process
 */
@XmlRootElement
public class LoginStatus implements Serializable  {

	private static final long serialVersionUID = -8676025441927212055L;

	public LoginStatus() {
	}

	public LoginStatus(boolean loggedIn, String username, String password, Boolean rememberMe) {
		this.loggedIn = loggedIn;
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}


	/**
	 * Boolean logged in current status
	 */
	private boolean loggedIn;

	/**
	 * Username (or login) currently logged in (or null if not logged in)
	 */
	private String username;

	private String password;

	private Boolean rememberMe;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
	
}
