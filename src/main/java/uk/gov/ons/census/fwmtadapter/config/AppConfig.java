package uk.gov.ons.census.fwmtadapter.config;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MarshallingMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class AppConfig {
  @Bean
  public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public Jaxb2Marshaller actionInstructionFieldMarshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath("uk.gov.ons.census.fwmtadapter.model.dto.field");
    return jaxb2Marshaller;
  }

  @Bean
  public MarshallingMessageConverter actionInstructionFieldMarshallingMessageConverter(
      Jaxb2Marshaller actionInstructionFieldMarshaller) {
    MarshallingMessageConverter marshallingMessageConverter =
        new MarshallingMessageConverter(actionInstructionFieldMarshaller);
    marshallingMessageConverter.setContentType("text/xml");
    return marshallingMessageConverter;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(
      ConnectionFactory connectionFactory,
      MarshallingMessageConverter actionInstructionFieldMarshallingMessageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(actionInstructionFieldMarshallingMessageConverter);
    rabbitTemplate.setChannelTransacted(true);
    return rabbitTemplate;
  }

  @Bean
  public MapperFacade mapperFacade() {
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    return mapperFactory.getMapperFacade();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
