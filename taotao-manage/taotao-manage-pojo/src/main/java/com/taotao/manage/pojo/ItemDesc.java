package com.taotao.manage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo{
    
    @Id//对应tb_item中的id
    private Long itemId;
    
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public ItemDesc setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public ItemDesc setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }
    
    

}
