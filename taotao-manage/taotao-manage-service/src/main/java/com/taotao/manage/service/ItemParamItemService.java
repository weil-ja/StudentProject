package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.taotao.manage.mapper.ItemParamItemMapper;
import com.taotao.manage.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamItemService extends BaseService<ItemParamItem> {

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    public Integer updateItemParamItem(Long itemId,String itemParams) {

        ItemParamItem itemParamItem = new ItemParamItem()
                .setParamData(itemParams);
        itemParamItem.setUpdated(new Date());

        Example example=new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId",itemId);
        return this.itemParamItemMapper.updateByExampleSelective(itemParamItem,example);
    }
}
