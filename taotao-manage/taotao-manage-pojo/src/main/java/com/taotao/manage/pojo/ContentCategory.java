package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_content_category")
public class ContentCategory extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    private Integer status;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_parent")
    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public ContentCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public ContentCategory setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContentCategory setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ContentCategory setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ContentCategory setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public ContentCategory setIsParent(Boolean isParent) {
        this.isParent = isParent;
        return this;
    }

    // 扩展字段，用于EasyUI中tree结构
    public String getText() {
        return getName();
    }

    public String getState() {
        return getIsParent() ? "closed" : "open";
    }

}
