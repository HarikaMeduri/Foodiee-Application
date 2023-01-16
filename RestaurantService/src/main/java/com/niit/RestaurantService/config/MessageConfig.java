package com.niit.RestaurantService.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
@Configuration
public class MessageConfig {

    private String exchangeName="restaurant_exchange";
    private String registerQueue="restaurant_queue";


    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }


    @Bean
    public Queue registerQueue()
    {
        return new Queue(registerQueue,false);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()   //it convert json obj to appropriate type
    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange exchange)          //BindingBuilder is a class which bind a queue
    {
        return BindingBuilder.bind(registerQueue()).to(directExchange()).with("restaurant_routing");
    }
}
