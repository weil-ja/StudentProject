package com.taotao.web.controller;

import com.taotao.web.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("index")
@Controller
public class IndexContriller {

    @Autowired
    private IndexService indexService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("index");

        //首页大广告
        String indexAD1=this.indexService.queryIndexAD1();
        mv.addObject("indexAD1",indexAD1);

        //右上角小广告
        String indexAD2=this.indexService.queryIndexAD2();
        mv.addObject("indexAD2",indexAD2);
        return mv;
    }
}
