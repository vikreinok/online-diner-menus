package ee.ttu.catering.rest.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User model. This class is used both as application data and and security instance. <br/>
 * "@Entity" declare the class as an JPA 2 managed bean.<br/>
 * "@Table" allow us to override standard name of the DB table because "user" is often a key word.<br/>
 * set. <br/>
 * The class implements UserDetails which is the Spring Security interface to be able to use our User object as a user description in Spring Security
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {

  private static final long serialVersionUID = 818129969599480161L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String login;

  private String password;

  private String fullname;

  /**
   * Get id
   *
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set id
   *
   * @param id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get login
   *
   * @return login
   */
  public String getLogin() {
    return login;
  }

  /**
   * Set login
   *
   * @param login
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * Get password. Implements UserDetails.getPassword(). <br/>
   * "@JsonIgnore" will remove password value when performing the JSON serialization in order to not sending all passwords to everyone!
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set password
   *
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get fullname
   *
   * @return fullname
   */
  public String getFullname() {
    return fullname;
  }

  /**
   * Set fullname
   *
   * @param fullname
   */
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }


  /**
   * Get username which here is the login. Implements UserDetails.getUsername
   *
   * @return login
   */
  public String getUsername() {
    return login;
  }

  /**
   * Get authorities which in this simple application is always ROLE_USER if the user is defined. Implements UserDetails.getAuthorities
   *
   * @return authorities
   */
  public Collection<GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    authorities.add(new GrantedAuthority() {
      private static final long serialVersionUID = 323393444706865772L;

      public String getAuthority() {
        return "ROLE_USER";
      }
    });

    return authorities;
  }

  /**
   * Not implemented. Implements UserDetails.isAccountNonExpired
   *
   * @return true
   */
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Not implemented. Implements UserDetails.isAccountNonLocked
   *
   * @return true
   */
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Not implemented. Implements UserDetails.isCredentialsNonExpired
   *
   * @return true
   */
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Not implemented. Implements UserDetails.isEnabled
   *
   * @return true
   */
  public boolean isEnabled() {
    return true;
  }

}
