package com.example.demo.jpa.services.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.modelo.Usuario;
import com.example.demo.jpa.services.IntRabbitSender;

@Service
public class RabbitSenderServicesImpl implements IntRabbitSender {

  @Value("${ejercicioRabbit.rabbitmq.exchange}")
  private String exchange;

  @Value("${ejercicioRabbit.rabbitmq.routingkey}")
  private String routingkey;

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Override
  public void sendUsuario(final Usuario userOptional) {
    rabbitTemplate.convertAndSend(exchange, routingkey, userOptional);

  }

}
