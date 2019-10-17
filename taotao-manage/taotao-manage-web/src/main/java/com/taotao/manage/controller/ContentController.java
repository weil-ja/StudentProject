package com.taotao.manage.controller;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("content")
@Controller
public class ContentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saevContent(Content content) {
        try {
            content.setId(null);
            this.contentService.save(content);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryListByCategoryId(@RequestParam("categoryId") Long categoryId,
                                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        try {
            EasyUIResult easyUIResult = this.contentService.queryListByCategoryId(categoryId, page, rows);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            LOGGER.error("查询出错! page = " + page + ",rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
