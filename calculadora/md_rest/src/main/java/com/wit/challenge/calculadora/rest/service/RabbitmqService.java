package com.wit.challenge.calculadora.rest.service;

import java.util.HashMap;

import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wit.challenge.calculadora.library.constants.RabbitConstants;
import com.wit.challenge.calculadora.library.constants.SystemConstants;
import com.wit.challenge.calculadora.library.dto.OperandsDTO;
import com.wit.challenge.calculadora.library.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitmqService {

	@Autowired
	private RabbitTemplate aRabbitTemplate;

	/**
	 * Monta a mensagem de envio
	 */
	private Message rabbitMessage(OperandsDTO pMensage) throws JsonProcessingException {
		String vMdcCorrelationId = MDC.get(SystemConstants.CORRELATION_ID);
		String vMdcOperation = MDC.get(SystemConstants.ORIGIN);

		Message vMessage = MessageBuilder.withBody(JsonUtil.serializeToJson(pMensage).getBytes()).build();
		vMessage.getMessageProperties().setHeader(SystemConstants.CORRELATION_ID, vMdcCorrelationId);
		vMessage.getMessageProperties().setHeader(SystemConstants.ORIGIN, vMdcOperation);

		return vMessage;
	}

	public String sendMessage(OperandsDTO pMensage) {
		try {
			Message vMessage = rabbitMessage(pMensage);

			log.trace("client service send：{}", vMessage.toString());

			Message vResult = aRabbitTemplate.sendAndReceive(RabbitConstants.RPC_EXCHANGE, RabbitConstants.QUEUE_OP_MSG,
					vMessage);

			String vResponse = "";
			if (vResult != null) {
				String vCorrelationId = vMessage.getMessageProperties().getCorrelationId();

				HashMap<String, Object> vHeaders = (HashMap<String, Object>) vResult.getMessageProperties().getHeaders();

				String vMsgId = (String) vHeaders.get("spring_returned_message_correlation");

				if (vMsgId.equals(vCorrelationId)) {
					vResponse = new String(vResult.getBody());
					log.trace("client serveice receive：{}", vResponse);
				}
			}
			return vResponse;

		} catch (Exception pException) {
			pException.printStackTrace();
			log.trace("Eror", pException.toString());
			return "Erro";
		}
	}

}
