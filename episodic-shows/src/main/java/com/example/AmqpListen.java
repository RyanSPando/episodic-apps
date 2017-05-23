package com.example;

import com.example.viewings.ViewingRequest;
import com.example.viewings.ViewingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.transaction.annotation.Transactional;


@Configuration
public class AmqpListen implements RabbitListenerConfigurer {

private final ViewingService viewingService;
private final ObjectMapper mapper;

  public AmqpListen(ViewingService viewingService, ObjectMapper mapper) {
    this.viewingService = viewingService;
    this.mapper = mapper;
  }

  @RabbitListener(queues = "episodic-progress")
  @Transactional
  public void receiveMessage(final ProgressMessage message) {

    if (message.getUserId() != null && !Long.valueOf(message.getEpisodeId()).equals(null)) {
      ViewingRequest viewingRequest = new ViewingRequest(message.getEpisodeId(), message.getCreatedAt(), message.getOffset());
      viewingService.updateOrCreateViewing(viewingRequest, message.getUserId());
    }
  }

  @Bean
  public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper(mapper);
    factory.setMessageConverter(converter);
    return factory;
  }

  @Override
  public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
    registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
  }

}

