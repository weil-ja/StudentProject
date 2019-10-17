package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格参数模板
 */
@Table(name = "tb_item_param")
public class ItemParam extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_cat_id")
    private Long itemCatId;

    @Column(name = "param_data")
    private String paramData;

    public Long getId() {
        return id;
    }

    public ItemParam setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public ItemParam setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
        return this;
    }

    public String getParamData() {
        return paramData;
    }

    public ItemParam setParamData(String paramData) {
        this.paramData = paramData;
        return this;
    }
}
