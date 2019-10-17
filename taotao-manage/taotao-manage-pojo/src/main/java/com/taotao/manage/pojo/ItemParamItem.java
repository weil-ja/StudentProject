package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格参数
 */
@Table(name = "tb_item_param_item")
public class ItemParamItem extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "param_data")
    private String paramData;

    public Long getId() {
        return id;
    }

    public ItemParamItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public ItemParamItem setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getParamData() {
        return paramData;
    }

    public ItemParamItem setParamData(String paramData) {
        this.paramData = paramData;
        return this;
    }
}
