package com.sifast.socle.javaee.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "T_USER", uniqueConstraints = @UniqueConstraint(columnNames = "USR_EMAIL"))
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "findByLogin", query = "from User u where u.login = :login"), @NamedQuery(name = "findByMail", query = "from User u where u.email = :email") })
public class User extends TimestampEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USR_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "USR_FIRSTNAME")
	private String firstName;

	@Column(name = "USR_LASTNAME")
	private String lastName;

	@Column(name = "USR_EMAIL", nullable = false, length = 200)
	private String email;

	@Column(name = "USR_ADRESS")
	private String address;

	@Column(name = "USR_PHONE", length = 30)
	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name = "USR_BIRTHDATE", length = 10)
	private Date birthDate;

	@Column(name = "USR_LOGIN", nullable = false, unique = true, length = 200)
	private String login;

	@Column(name = "USR_PASSWORD", nullable = false, length = 200)
	private String password;

	@Column(name = "USR_ENABLED")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TJ_USER_ROLE", joinColumns = { @JoinColumn(name = "USR_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROL_ID", nullable = false, updatable = false) })
	@JsonManagedReference
	@XmlTransient
	private Set<Role> roles = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	@XmlTransient
	private Set<PasswordResetToken> passwordresettokens = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	@XmlTransient
	private Set<LoginLogoutTrack> loginLogoutTrack = new HashSet<>(0);

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<PasswordResetToken> getPasswordresettokens() {
		return passwordresettokens;
	}

	public void setPasswordresettokens(Set<PasswordResetToken> passwordresettokens) {
		this.passwordresettokens = passwordresettokens;
	}

	public Set<LoginLogoutTrack> getLoginLogoutTrack() {
		return loginLogoutTrack;
	}

	public void setLoginLogoutTrack(Set<LoginLogoutTrack> loginLogoutTrack) {
		this.loginLogoutTrack = loginLogoutTrack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((loginLogoutTrack == null) ? 0 : loginLogoutTrack.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordresettokens == null) ? 0 : passwordresettokens.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (birthDate == null) {
			if (other.birthDate != null) {
				return false;
			}
		} else if (!birthDate.equals(other.birthDate)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (loginLogoutTrack == null) {
			if (other.loginLogoutTrack != null) {
				return false;
			}
		} else if (!loginLogoutTrack.equals(other.loginLogoutTrack)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (passwordresettokens == null) {
			if (other.passwordresettokens != null) {
				return false;
			}
		} else if (!passwordresettokens.equals(other.passwordresettokens)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (roles == null) {
			if (other.roles != null) {
				return false;
			}
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", email=").append(email)
				.append(", address=").append(address).append(", phone=").append(phone).append(", birthDate=").append(birthDate).append(", login=").append(login)
				.append(", password=").append(password).append(", enabled=").append(enabled).append(", roles=").append(roles).append(", passwordresettokens=")
				.append(passwordresettokens).append(", loginLogoutTrack=").append(loginLogoutTrack).append("]");
		return builder.toString();
	}

}
