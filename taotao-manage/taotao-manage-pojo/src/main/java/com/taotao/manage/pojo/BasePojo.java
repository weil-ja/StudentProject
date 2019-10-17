package com.taotao.manage.pojo;

import java.util.Date;

public abstract class BasePojo {
    
    private Date created;
    private Date updated;
    public Date getCreated() {
        return created;
    }

    public BasePojo setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public BasePojo setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }
}
