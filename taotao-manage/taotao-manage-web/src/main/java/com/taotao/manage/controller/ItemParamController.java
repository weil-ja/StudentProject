package com.taotao.manage.controller;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("item/param")
@Controller
public class ItemParamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemParamList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            EasyUIResult easyUIResult = this.itemParamService.queryItemParamList(page, rows);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            LOGGER.error("查询商品列表出错! page = " + page + ",rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据商品类目id查询规格参数模板
     *
     * @param itemCatId
     * @return
     */
    @RequestMapping(value = "{itemCatId}",method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryByItemCatId(@PathVariable Long itemCatId){
        try {
            ItemParam record=new ItemParam()
                    .setItemCatId(itemCatId);
            ItemParam itemParam=this.itemParamService.queryOne(record);
            if (null==itemParam){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增规格参数模板
     *
     * @param itemCatId
     * @param paramData
     * @return
     */
    @RequestMapping(value = "{itemCatId}",method = RequestMethod.POST)
    public ResponseEntity<Void> saevItemParam(@PathVariable("itemCatId") Long itemCatId,
                                              @RequestParam("paramData") String paramData){
        try {
            ItemParam record=new ItemParam()
                    .setId(null)
                    .setItemCatId(itemCatId)
                    .setParamData(paramData);
            this.itemParamService.save(record);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
