package com.rabbitmq.controller;

import com.rabbitmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:            //前端创建订单 20分钟后未支付则取消订单 返还库存
 * @Author wanghefei
 * @date: 2021/5/6 21:38
 */
@RequestMapping("/order")
@Controller
public class OrderContoller {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestParam String orderId){
        orderService.cancelOrder(orderId);
        return "ok";
    }
    @RequestMapping("/refund")
    @ResponseBody
    public String refund(@RequestParam String orderId){
        orderService.refundOrder(orderId);
        return "ok";
    }
}
