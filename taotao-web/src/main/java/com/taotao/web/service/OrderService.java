package com.taotao.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.httpclient.HttpResult;
import com.taotao.common.service.ApiService;
import com.taotao.web.bean.Order;
import com.taotao.web.bean.User;
import com.taotao.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_ORDER_URL}")
    private String TAOTAO_ORDER_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String submit(Order order) {

//        补充当前用户登录信息
        User user = UserThreadLocal.get();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());

        String url = TAOTAO_ORDER_URL + "/order/create";
        try {
            HttpResult httpResult = this.apiService.doPostJson(url, MAPPER.writeValueAsString(order));
//            响应状态码为200时创建成功
            if (httpResult.getCode().intValue() == 200) {
                String body = httpResult.getBody();
                JsonNode jsonNode = MAPPER.readTree(body);
                if (jsonNode.get("status").asInt() == 200) {
                    //                业务状态码为200时提交成功
                    return jsonNode.get("data").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order queryByOrderId(String orderId) {
        String url = TAOTAO_ORDER_URL + "/order/query/" + orderId;
        try {
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData, Order.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
