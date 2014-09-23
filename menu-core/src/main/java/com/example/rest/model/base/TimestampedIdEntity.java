package com.example.rest.model.base;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;


@MappedSuperclass
public abstract class TimestampedIdEntity extends IdEntity {

    @Basic(optional = false)
    private Date created;

    @PrePersist
    protected void persist() {
        if (created == null) {
            created = new Date();
        }
    }

    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
}
