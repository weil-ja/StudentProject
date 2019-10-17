package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long pid) {
        try {
            ItemCat record = new ItemCat()
                    .setParentId(pid);
            List<ItemCat> list = this.itemCatService.queryListByWhere(record);
            if (null == list || list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "{pid}", method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryItemCatByParentId(
            @PathVariable(value = "pid") Long pid) {
        try {
            ItemCat itemCat = this.itemCatService.queryById(pid);
            if (null == itemCat) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
