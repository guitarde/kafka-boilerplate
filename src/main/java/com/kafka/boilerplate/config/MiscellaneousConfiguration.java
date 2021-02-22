package com.kafka.boilerplate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MiscellaneousConfiguration implements KafkaListenerConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LocalValidatorFactoryBean validatorFactory;

	@Override
	public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
		logger.debug("Configuring " + registrar);
		registrar.setMessageHandlerMethodFactory(kafkaHandlerMethodFactory());
	}

	@Bean
	public MessageHandlerMethodFactory kafkaHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setValidator(validatorFactory);
		return factory;
	}
}