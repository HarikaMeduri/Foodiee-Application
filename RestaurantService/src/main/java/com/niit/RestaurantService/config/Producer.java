package com.niit.RestaurantService.config;


import com.niit.rabbitmq.domain.RestaurantDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Producer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;


    //@Autowired annotation used with setter methods, it tries to perform byType autowiring on the method
    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitMq1(RestaurantDTO restaurantDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"restaurant_routing",restaurantDTO);
    }

}
