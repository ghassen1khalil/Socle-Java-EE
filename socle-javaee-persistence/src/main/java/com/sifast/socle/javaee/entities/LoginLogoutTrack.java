package com.sifast.socle.javaee.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_LOGIN_LOGOUT_TRACK")
public class LoginLogoutTrack extends TimestampEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "LOG_USER_ID")
	private User user;

	@Column(name = "LOG_LOGON_DATE", nullable = false)
	private Date logonDate;

	@Column(name = "LOG_LOGOUT_DATE", nullable = true)
	private Date logoutDate;

	@Column(name = "LOG_IP_ADDRESS", length = 15, nullable = false)
	private String ipAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLogonDate() {
		return logonDate;
	}

	public void setLogonDate(Date logonDate) {
		this.logonDate = logonDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((logonDate == null) ? 0 : logonDate.hashCode());
		result = prime * result + ((logoutDate == null) ? 0 : logoutDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		LoginLogoutTrack other = (LoginLogoutTrack) obj;
		if (id != other.id) {
			return false;
		}
		if (ipAddress == null) {
			if (other.ipAddress != null) {
				return false;
			}
		} else if (!ipAddress.equals(other.ipAddress)) {
			return false;
		}
		if (logonDate == null) {
			if (other.logonDate != null) {
				return false;
			}
		} else if (!logonDate.equals(other.logonDate)) {
			return false;
		}
		if (logoutDate == null) {
			if (other.logoutDate != null) {
				return false;
			}
		} else if (!logoutDate.equals(other.logoutDate)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginLogoutTrack [id=").append(id).append(", user=").append(user).append(", logonDate=").append(logonDate).append(", logoutDate=").append(logoutDate)
				.append(", ipAddress=").append(ipAddress).append("]");
		return builder.toString();
	}

}
