package com.rabbitmq;

import com.rabbitmq.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        for (int i = 1; i <= 10; i++) {
            orderService.cancelOrder("9527"+i);
        }
    }

}
