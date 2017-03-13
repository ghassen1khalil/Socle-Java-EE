package com.sifast.socle.javaee.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class TimestampEntity implements Serializable, PreInsertEventListener, PreUpdateEventListener {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    @JsonIgnore
    @XmlTransient
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", nullable = true)
    @JsonIgnore
    @XmlTransient
    private Date updateDate;

    public TimestampEntity() {
        super();
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        if (event.getEntity() instanceof TimestampEntity) {
            TimestampEntity timestampEntity = (TimestampEntity) event.getEntity();
            timestampEntity.creationDate = Date.from(Instant.now());
            setPropertyState(event.getState(), event.getPersister().getPropertyNames(), "creationDate", Date.from(Instant.now()));
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        if (event.getEntity() instanceof TimestampEntity) {
            TimestampEntity timestampEntity = (TimestampEntity) event.getEntity();
            timestampEntity.updateDate = Date.from(Instant.now());
            setPropertyState(event.getState(), event.getPersister().getPropertyNames(), "updateDate", Date.from(Instant.now()));
        }
        return false;
    }

    private void setPropertyState(Object[] propertyStates, String[] propertyNames, String propertyName, Object propertyState) {
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyName.equals(propertyNames[i])) {
                propertyStates[i] = propertyState;
                return;
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TimestampEntity other = (TimestampEntity) obj;
        if (creationDate == null) {
            if (other.creationDate != null) {
                return false;
            }
        } else if (!creationDate.equals(other.creationDate)) {
            return false;
        }
        if (updateDate == null) {
            if (other.updateDate != null) {
                return false;
            }
        } else if (!updateDate.equals(other.updateDate)) {
            return false;
        }
        return true;
    }

}
