package com.user_service.user_service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
// import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_EXCHANGE = "user_service_exchange";

    @Bean
    TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }
    
    @Bean
    Queue userAttendanceQueue() {
        return new Queue("user.attendance.queue", true);
    }

    @Bean
    Queue userPayrollQueue() {
        return new Queue("user.payroll.queue", true);
    }

    @Bean
    Binding bindingAttendance(Queue userAttendanceQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userAttendanceQueue).to(userExchange).with("user.events");
    }

    @Bean
    Binding bindingPayroll(Queue userPayrollQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userPayrollQueue).to(userExchange).with("user.payroll.events");
    }

    // public static final String EXCHANGE_NAME = "user_service_exchange";
    // public static final String ROUTING_KEY = "user.events";

    // @Bean
    // public Queue reportingQueue() {
    //     return new Queue("reporting_service_queue", false);
    // }

    // @Bean
    // public TopicExchange exchange() {
    //     return new TopicExchange(EXCHANGE_NAME);
    // }

    // @Bean
    // public Binding binding(Queue reportingQueue, TopicExchange exchange) {
    //     return BindingBuilder.bind(reportingQueue).to(exchange).with(ROUTING_KEY);
    // }

    // @Bean
    // public MessageConverter jsonMessageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }

    // @Bean
    // public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    //     RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(jsonMessageConverter());
    //     return rabbitTemplate;
    // }
}