package com.sifast.socle.javaee.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.sifast.socle.javaee.utils.DateUtil;

@Entity
@Table(name = "T_PASSWORD_RESET_TOKEN")
@NamedQueries({ @NamedQuery(name = "findByToken", query = "from PasswordResetToken t where t.token = :token") })
public class PasswordResetToken extends TimestampEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PAS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PAS_TOKEN", unique = true, nullable = false)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @Column(name = "PAS_EXPIRATION_DATE", nullable = false)
    private Date expirationDate;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(String token, User user, int delayForPasswordExpirationInMinute) {
        this.token = token;
        this.user = user;
        this.expirationDate = DateUtil.calculateExpirationDate(delayForPasswordExpirationInMinute);
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public LocalDateTime getExpirationLocalDateTime() {
        return expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((token == null) ? 0 : token.hashCode());
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
        PasswordResetToken other = (PasswordResetToken) obj;
        if (expirationDate == null) {
            if (other.expirationDate != null) {
                return false;
            }
        } else if (!expirationDate.equals(other.expirationDate)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
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
        builder.append("PasswordResetToken [id=").append(id).append(", token=").append(token).append(", user=").append(user).append(", expirationDate=").append(expirationDate)
                .append("]");
        return builder.toString();
    }

}
