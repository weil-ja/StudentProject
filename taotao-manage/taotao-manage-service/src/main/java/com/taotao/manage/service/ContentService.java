package com.taotao.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public EasyUIResult queryListByCategoryId(Long categoryId, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Content> list=this.contentMapper.queryContentList(categoryId);
        PageInfo<Content> pageInfo=new PageInfo<Content>(list);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
