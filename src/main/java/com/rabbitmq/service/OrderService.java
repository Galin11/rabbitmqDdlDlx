package com.rabbitmq.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:            订单业务层
 * @Author wanghefei
 * @date: 2021/5/6 20:41
 */

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplatel;

    public void cancelOrder(String orderId){
        System.out.println(new Date());
        rabbitTemplatel.convertAndSend("order_exchange","order.cancel",orderId);
    }
    public void refundOrder(String orderId){
        System.out.println(new Date());
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(Message message) throws AmqpException {
                //设置消息 3秒后消息过期
                String expire = "3000";
                //从键值对里面取过期时间,这样可以动态设置
//                String time = KeyValueHelper.get("ORDER_WAIT_PAY_TIME");
//                if (StringUtils.isNotBlank(time)){
//                    expire = String.valueOf(Long.valueOf(time) * 60 * 1000);
//                }
                message.getMessageProperties().setExpiration(expire);
                return message;
            }
        };
        rabbitTemplatel.convertAndSend("order_exchange","order.cancel",orderId,messagePostProcessor);
    }

}
