package com.niit.Userdata.config;

import com.niit.rabbitmq.domain.CustomerDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component           // it denote a class as a Component.
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

    public void sendMessageToRabbitMq(CustomerDTO customerDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"customer_routing",customerDTO);
    }
}
