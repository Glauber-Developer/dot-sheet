package com.attendance_service.attendance_service;

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

    public static final String ATTENDANCE_EXCHANGE = "attendance_service_exchange";

    @Bean
    TopicExchange attendanceExchange() {
        return new TopicExchange(ATTENDANCE_EXCHANGE);
    }
    
    @Bean
    Queue attendancePayrollQueue() {
        return new Queue("attendance.payroll.queue", true);
    }

    @Bean
    Queue attendanceReportingQueue() {
        return new Queue("attendance.reporting.queue", true);
    }

    @Bean
    Binding bindingPayroll(Queue attendancePayrollQueue, TopicExchange attendanceExchange) {
        return BindingBuilder.bind(attendancePayrollQueue).to(attendanceExchange).with("attendance.events");
    }

    @Bean
    Binding bindingReporting(Queue attendanceReportingQueue, TopicExchange attendanceExchange) {
        return BindingBuilder.bind(attendanceReportingQueue).to(attendanceExchange).with("attendance.events");
    }


    // public static final String EXCHANGE_NAME = "attendance_service_exchange";
    // public static final String ROUTING_KEY = "attendance.events";
    // public static final String QUEUE_NAME = "myQueue";

    // @Bean
    // public Queue myQueue() {
    //     return new Queue(QUEUE_NAME, false);
    // }

    // @Bean
    // public TopicExchange exchange() {
    //     return new TopicExchange(EXCHANGE_NAME);
    // }

    // @Bean
    // public Binding binding(Queue myQueue, TopicExchange exchange) {
    //     return BindingBuilder.bind(myQueue).to(exchange).with(ROUTING_KEY);
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