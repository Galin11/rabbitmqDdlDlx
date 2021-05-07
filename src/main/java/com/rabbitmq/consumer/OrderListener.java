package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:            死信队列消费者
 * @Author wanghefei
 * @date: 2021/5/6 21:13
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("order_cancel_queue_dlx"))
public class OrderListener {
    @RabbitHandler
    public void onMessage (String message){
        System.out.println("message = " + message + "  ||  " +new Date());
    }
}
