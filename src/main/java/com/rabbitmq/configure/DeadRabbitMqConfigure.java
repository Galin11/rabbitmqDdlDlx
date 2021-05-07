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
 * @Description:            死信队列 配置类
 * @Author wanghefei
 * @date: 2021/5/6 20:44
 */


@Configuration
public class DeadRabbitMqConfigure {

    //1、声明注册交换机
    @Bean
    public TopicExchange deadTopidExchange(){
        return new TopicExchange("order_exchange_dlx",true,false);
    }
    //2、设置队列过期时间
    @Bean
    public Queue deadQueue () {
        return new Queue("order_cancel_queue_dlx",true);
    }
    //3、
    @Bean
    public Binding deadTopicBinds(){
        return BindingBuilder.bind(deadQueue()).to(deadTopidExchange()).with("dlx.order.*");
    }




























    //2、
}
