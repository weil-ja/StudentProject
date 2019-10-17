package com.taotao.manage.controller;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("content/category")
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 查数据
     *
     * @param pid
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentCategory>> queryListByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long pid) {
        try {
            ContentCategory record = new ContentCategory()
                    .setParentId(pid);
            List<ContentCategory> list = this.contentCategoryService.queryListByWhere(record);
            if (null == list || list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增节点
     *
     * @param contentCategory
     * @return
     */

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentCategory> saevContentCategory(ContentCategory contentCategory){
        try {
            this.contentCategoryService.saveContentCategory(contentCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 重命名
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public  ResponseEntity<Void> reName(@RequestParam("id") Long id,
                                        @RequestParam("name") String name){
        try {
            ContentCategory contentCategory=new ContentCategory()
                    .setId(id)
                    .setName(name);
            this.contentCategoryService.updateSelective(contentCategory);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    /**
     * 删除节点，包括所有子节点
     *
     * @param contentCategory
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public  ResponseEntity<Void> delete(ContentCategory contentCategory){
        try {
            this.contentCategoryService.deleteAll(contentCategory);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
