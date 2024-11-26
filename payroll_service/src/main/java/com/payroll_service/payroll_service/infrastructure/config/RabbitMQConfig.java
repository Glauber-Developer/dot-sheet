package com.payroll_service.payroll_service.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue payrollReportingQueue() {
        return new Queue("payroll.reporting.queue", true);
    }

    @Bean
    public Queue userPayrollQueue() {
        return new Queue("user.payroll.queue", true); // Fila durável
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("payroll-exchange");
    }

    @Bean
    public Binding binding(Queue payrollReportingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(payrollReportingQueue).to(exchange).with("payroll-routing-key");
    }
    
    @Bean
    public Binding bindingUserPayroll(Queue userPayrollQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userPayrollQueue).to(exchange).with("user-payroll-routing-key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}