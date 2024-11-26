package com.user_service.user_service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

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
}