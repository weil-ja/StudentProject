package com.taotao.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public ItemCat setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public ItemCat setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemCat setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ItemCat setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ItemCat setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public ItemCat setIsParent(Boolean isParent) {
        this.isParent = isParent;
        return this;
    }

    public String getText() {
        return this.name;
    }

    public String getState(){return this.getIsParent() ? "closed" : "open";}
}
