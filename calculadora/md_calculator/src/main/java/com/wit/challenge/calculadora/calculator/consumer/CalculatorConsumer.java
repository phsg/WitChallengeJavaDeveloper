package com.wit.challenge.calculadora.calculator.consumer;

import java.io.IOException;
import java.math.RoundingMode;

import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wit.challenge.calculadora.library.constants.RabbitConstants;
import com.wit.challenge.calculadora.library.constants.SystemConstants;
import com.wit.challenge.calculadora.library.dto.OperandsDTO;
import com.wit.challenge.calculadora.library.util.EnumOperantion;
import com.wit.challenge.calculadora.library.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CalculatorConsumer {

	@Autowired
	private RabbitTemplate aRabbitTemplate;

	private void actionException(Exception pException) {
		log.trace("Error", pException.toString());
		log.error("Error", pException.toString());
		pException.printStackTrace();
	}

	private void logMdc(Message pMessage) {
		String vHeaderCorrelationId = pMessage.getMessageProperties().getHeader(SystemConstants.CORRELATION_ID);
		String vHeaderOperation = pMessage.getMessageProperties().getHeader(SystemConstants.ORIGIN);

		MDC.put(SystemConstants.CORRELATION_ID, vHeaderCorrelationId);
		MDC.put(SystemConstants.ORIGIN, vHeaderOperation);
	}

	@RabbitListener(queues = RabbitConstants.QUEUE_OP_MSG)
	public void process(Message pMessage) {
		try {
			logMdc(pMessage);

			log.trace("server consumer receive: {}" + pMessage.toString());

			String vResult = null;

			try {
				OperandsDTO vOperandosDTO = JsonUtil.deserializeToJson(new String(pMessage.getBody()));
				EnumOperantion vOperation = vOperandosDTO.getOperation();
				switch (vOperation) {
				case SUM:
					vResult = vOperandosDTO.getA().add(vOperandosDTO.getB()).toString();
					break;
				case SUBTRACTION:
					vResult = vOperandosDTO.getA().subtract(vOperandosDTO.getB()).toString();
					break;
				case DIVISION:
					vResult = vOperandosDTO.getA().divide(vOperandosDTO.getB(), RoundingMode.HALF_UP).toString();
					break;
				case MULTIPLICATION:
					vResult = vOperandosDTO.getA().multiply(vOperandosDTO.getB()).toString();
					break;
				default:
					log.info("default");
				}
			} catch (IOException pException) {
				actionException(pException);
				vResult = "Operation fail!";
			} catch (Exception pException) {
				actionException(pException);
				vResult = "Fail!";
			}

			log.trace("vResult {}", vResult);

			Message response = MessageBuilder.withBody((vResult).getBytes()).build();

			CorrelationData correlationData = new CorrelationData(pMessage.getMessageProperties().getCorrelationId());
			aRabbitTemplate.sendAndReceive(RabbitConstants.RPC_EXCHANGE, RabbitConstants.QUEUE_OP_REPLY, response,
					correlationData);
		} finally {
			MDC.remove(SystemConstants.CORRELATION_ID);
			MDC.remove(SystemConstants.ORIGIN);
		}
	}

}
