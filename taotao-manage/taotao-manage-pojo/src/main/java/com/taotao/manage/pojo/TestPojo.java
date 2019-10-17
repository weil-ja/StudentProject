package com.taotao.manage.pojo;

import java.util.Date;

public abstract class TestPojo<T extends TestPojo<?>> {

    private Date created;
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public T setCreated(Date created) {
        this.created = created;
        return (T) this;
    }

    public Date getUpdated() {
        return updated;
    }

    public T setUpdated(Date updated) {
        this.updated = updated;
        return (T) this;
    }
}
