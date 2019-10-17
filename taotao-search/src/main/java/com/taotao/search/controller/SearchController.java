package com.taotao.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@Controller
public class SearchController {

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("q") String keyWords,
           @RequestParam(value = "page",defaultValue = "1")Integer page){
        ModelAndView mv=new ModelAndView("search");

//        搜索关键字
        mv.addObject("query",keyWords);
//        搜索结果数据
        mv.addObject("itemList",null);
//        页数
        mv.addObject("page",page);
        mv.addObject("pages",null);

        return mv;
    }
}
