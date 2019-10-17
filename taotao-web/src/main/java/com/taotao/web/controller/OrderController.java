package com.taotao.web.controller;

import com.taotao.web.bean.Item;
import com.taotao.web.bean.Order;
import com.taotao.web.service.ItemService;
import com.taotao.web.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("order")
@Controller
public class OrderController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    /**
     * 订单确认页
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}",method = RequestMethod.GET)
    public ModelAndView toOrder(@PathVariable("itemId") Long itemId){
        ModelAndView mv=new ModelAndView("order");

        Item item=this.itemService.queryById(itemId);
        mv.addObject("item",item);

        return mv;
    }

    /**
     * 提交订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submit(Order order){
        Map<String,Object> result=new HashMap<>();

        String orderId=this.orderService.submit(order);
        if (StringUtils.isEmpty(orderId)) {
//            订单提交失败
            result.put("status",500);
        }else {
//            订单提交成功
            result.put("status",200);
            result.put("data",orderId);
        }
        return result;
    }

    @RequestMapping(value = "success",method = RequestMethod.GET)
    public ModelAndView success(@RequestParam("id") String orderId){
        ModelAndView mv=new ModelAndView("success");
        Order order=this.orderService.queryByOrderId(orderId);
        System.out.println(order.getOrderId());
        System.out.println(orderId);
//        订单时间
        mv.addObject("order",order);

//        预计送货时间，当前时间向后推两天
        mv.addObject("date",new DateTime().plusDays(2).toString("MM月dd日"));
        return mv;
    }
}
