package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.service.ApiService;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamItemService itemParamItemService;

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_WEB_URL}")
    private String TAOTAO_WEB_URL;

    /**
     * 保存
     *
     * @param item
     * @param desc
     * @return
     */
    public Boolean saveItem(Item item, String desc, String itemParams) {
        //强制设置状态不能修改
        item.setStatus(null)
                .setStatus(1)
                .setId(null);
        Integer count1 = super.save(item);

        //保存商品描述数据
        ItemDesc itemDesc = new ItemDesc()
                .setItemId(item.getId())
                .setItemDesc(desc);
        Integer count2 = this.itemDescService.save(itemDesc);

        //保存商品规格数据
        ItemParamItem itemParamItem = new ItemParamItem()
                .setItemId(item.getId())
                .setParamData(itemParams);
        Integer count3 = this.itemParamItemService.save(itemParamItem);

        return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
    }

    /**
     * 查询
     *
     * @param page
     * @param rows
     * @return
     */
    public EasyUIResult queryItemList(Integer page, Integer rows) {
        //设置分页参数
        PageHelper.startPage(page, rows);
        Example example = new Example(Item.class);
        //根据创建时间倒叙排序
        example.setOrderByClause("created DESC");

        List<Item> items = this.itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public Boolean updateItem(Item item, String desc, String itemParams) {
        Integer count1 = super.updateSelective(item);

        ItemDesc itemDesc = new ItemDesc()
                .setItemDesc(desc)
                .setItemId(item.getId());
        Integer count2 = this.itemDescService.updateSelective(itemDesc);

        Integer count3 = this.itemParamItemService.updateItemParamItem(item.getId(),itemParams);

        try {
//        通知其他系统，该商品已更新
            String url=TAOTAO_WEB_URL+"/item/cache/"+item.getId()+".html";
            this.apiService.doPost(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
    }
}
