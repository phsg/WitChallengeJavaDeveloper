package com.wit.challenge.calculadora.rest.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.wit.challenge.calculadora.library.constants.RabbitConstants;

/**
 * Configuração RabbitMq
 * 
 * @author phsg
 *
 */
@Configuration
@Component
public class RabbitConfig {

	private AmqpAdmin aAmqpAdmin;

	public RabbitConfig(AmqpAdmin pAmqpAdmin) {
		this.aAmqpAdmin = pAmqpAdmin;
	}

	private Binding binding(Queue pQueue, TopicExchange pExchange) {
		return new Binding(pQueue.getName(), Binding.DestinationType.QUEUE, pExchange.getName(), pQueue.getName(),
				null);
	}

	TopicExchange exchange() {
		return new TopicExchange(RabbitConstants.RPC_EXCHANGE);
	}

	@PostConstruct
	private void producer() {
		Queue vQueueMsg = this.queue(RabbitConstants.QUEUE_OP_MSG);
		Queue vQueueReply = this.queue(RabbitConstants.QUEUE_OP_REPLY);

		TopicExchange vExchange = this.exchange();

		Binding vBindingAddMsg = this.binding(vQueueMsg, vExchange);
		Binding vBindingAddReply = this.binding(vQueueReply, vExchange);

		this.aAmqpAdmin.declareQueue(vQueueMsg);
		this.aAmqpAdmin.declareQueue(vQueueReply);

		this.aAmqpAdmin.declareExchange(vExchange);

		this.aAmqpAdmin.declareBinding(vBindingAddMsg);
		this.aAmqpAdmin.declareBinding(vBindingAddReply);
	}

	private Queue queue(String pName) {
		return new Queue(pName);
	}

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory pConnectionFactory) {
		RabbitTemplate template = new RabbitTemplate(pConnectionFactory);
		template.setReplyAddress(RabbitConstants.QUEUE_OP_REPLY);
		template.setReplyTimeout(9000);
		return template;
	}

	@Bean
	SimpleMessageListenerContainer replyAddContainer(ConnectionFactory pConnectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(pConnectionFactory);
		container.setQueueNames(RabbitConstants.QUEUE_OP_REPLY);
		container.setMessageListener(rabbitTemplate(pConnectionFactory));
		return container;
	}

}
