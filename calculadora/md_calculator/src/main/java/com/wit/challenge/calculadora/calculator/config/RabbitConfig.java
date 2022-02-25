package com.wit.challenge.calculadora.calculator.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

	@PostConstruct
	private void consumer() {
		Queue vQueueMsg = this.queue(RabbitConstants.QUEUE_OP_MSG);
		Queue vQueueReply = this.queue(RabbitConstants.QUEUE_OP_REPLY);

		TopicExchange vExchange = this.exchange();

		Binding vBindingMsg = this.binding(vQueueMsg, vExchange);
		Binding vBindingReply = this.binding(vQueueReply, vExchange);

		this.aAmqpAdmin.declareQueue(vQueueMsg);
		this.aAmqpAdmin.declareQueue(vQueueReply);

		this.aAmqpAdmin.declareExchange(vExchange);

		this.aAmqpAdmin.declareBinding(vBindingMsg);
		this.aAmqpAdmin.declareBinding(vBindingReply);
	}

	TopicExchange exchange() {
		return new TopicExchange(RabbitConstants.RPC_EXCHANGE);
	}

	private Queue queue(String pName) {
		return new Queue(pName);
	}

}
