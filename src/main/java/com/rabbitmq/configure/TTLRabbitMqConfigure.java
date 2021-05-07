package com.rabbitmq.configure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:            过期失效队列 配置类
 * @Author wanghefei
 * @date: 2021/5/6 20:44
 */

@Configuration
public class TTLRabbitMqConfigure {

    //1、声明注册交换机
    @Bean
    public TopicExchange ttlTopidExchange(){
        return new TopicExchange("order_exchange",true,false);
    }
    //2、设置队列过期时间 绑定死信队列
    @Bean
    public Queue ttlPopicQueue () {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl",1200000);     //20分钟后队列失效 默认失效时间 （与消息失效时间 时间短的优先级高）
        args.put("x-dead-letter-exchange","order_exchange_dlx");    //20分钟 失效后死信队列接收
        args.put("x-dead-letter-routing-key","dlx.order.cancel");   //死信队列routingKey
        return new Queue("order_cancel_queue",true,false,false,args);
    }
    //3、绑定routingKey
    @Bean
    public Binding ttlTopicBinds(){
        return BindingBuilder.bind(ttlPopicQueue()).to(ttlTopidExchange()).with("order.#");
    }




























    //2、
}
