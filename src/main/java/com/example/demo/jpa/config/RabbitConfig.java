package com.example.demo.jpa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${ejercicioRabbit.rabbitmq.queue}")
  String QUEUE_NAME;

  @Value("${ejercicioRabbit.rabbitmq.exchange}")
  String EXCHANGE_NAME;

  @Value("${ejercicioRabbit.rabbitmq.routingkey}")
  String ROUTINGKEY;

  // cola con un nombre
  @Bean
  public Queue queue() {
    return new Queue(QUEUE_NAME);
  }

  // Cre un exchange
  @Bean
  TopicExchange exhange() {
    return new TopicExchange(EXCHANGE_NAME);

  }

  @Bean
  Binding binding(final Queue queue, final TopicExchange exhange) {
    return BindingBuilder.bind(queue).to(exhange).with(ROUTINGKEY);
  }

  // Cambiar el comportamiento del template y hacer conversión para Json

  @Bean
  public RabbitTemplate template(final ConnectionFactory connectionFactory) {
    final RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter((MessageConverter) producerJackson2MessageConverter());
    return template;
  }

  private Object producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
