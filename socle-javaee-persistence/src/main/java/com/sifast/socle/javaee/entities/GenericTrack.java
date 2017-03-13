package com.sifast.socle.javaee.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sifast.socle.javaee.enums.EventType;

@Entity
@Table(name = "T_GENERIC_TRACK")
public class GenericTrack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TRC_ID")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRC_ENVENT_TYPE")
    private EventType eventType;

    @Column(name = "TRC_ENVENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @Column(name = "TRC_PERFORMED_BY")
    private String performedBy;

    @Column(name = "TRC_ENTITY")
    private String entity;

    @Column(name = "TRC_RECORD_VALUE", columnDefinition = "TEXT")
    private String recordValue;

    public GenericTrack() {
        super();
    }

    public GenericTrack(int id, EventType eventType, Date eventDate, String performedBy, String entity, String recordValue) {
        super();
        this.id = id;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.performedBy = performedBy;
        this.entity = entity;
        this.recordValue = recordValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(String recordValue) {
        this.recordValue = recordValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + id;
        result = prime * result + ((performedBy == null) ? 0 : performedBy.hashCode());
        result = prime * result + ((recordValue == null) ? 0 : recordValue.hashCode());
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
        GenericTrack other = (GenericTrack) obj;
        if (entity == null) {
            if (other.entity != null) {
                return false;
            }
        } else if (!entity.equals(other.entity)) {
            return false;
        }
        if (eventDate == null) {
            if (other.eventDate != null) {
                return false;
            }
        } else if (!eventDate.equals(other.eventDate)) {
            return false;
        }
        if (eventType != other.eventType) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (performedBy == null) {
            if (other.performedBy != null) {
                return false;
            }
        } else if (!performedBy.equals(other.performedBy)) {
            return false;
        }
        if (recordValue == null) {
            if (other.recordValue != null) {
                return false;
            }
        } else if (!recordValue.equals(other.recordValue)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GenericTrack [id=").append(id).append(", eventType=").append(eventType).append(", eventDate=").append(eventDate).append(", performedBy=")
                .append(performedBy).append(", entity=").append(entity).append(", recordValue=").append(recordValue).append("]");
        return builder.toString();
    }

}
