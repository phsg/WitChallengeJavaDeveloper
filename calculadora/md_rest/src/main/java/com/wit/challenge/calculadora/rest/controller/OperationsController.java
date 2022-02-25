package com.wit.challenge.calculadora.rest.controller;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wit.challenge.calculadora.library.constants.SystemConstants;
import com.wit.challenge.calculadora.library.dto.OperandsDTO;
import com.wit.challenge.calculadora.library.util.EnumOperantion;
import com.wit.challenge.calculadora.rest.entity.ResultOperation;
import com.wit.challenge.calculadora.rest.service.RabbitmqService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "operation")
public class OperationsController {

	@Autowired
	private RabbitmqService aRabbitmqService;

	@RequestMapping(//
			value = "/division/v1", //
			method = RequestMethod.GET, //
			produces = MediaType.APPLICATION_JSON_VALUE//
	)
	public ResponseEntity<ResultOperation> division_v1(//
			@Valid OperandsDTO pOperandosDTO//
	) {
		MDC.put(SystemConstants.ORIGIN, EnumOperantion.DIVISION.getName());
		log.trace("Request receive : {}", pOperandosDTO.toString());
		pOperandosDTO.setOperation(EnumOperantion.DIVISION);

		String vResult = this.aRabbitmqService.sendMessage(pOperandosDTO);

		ResultOperation vResultOperation = new ResultOperation(vResult);
		ResponseEntity<ResultOperation> vResponseEntity = new ResponseEntity<>(vResultOperation, HttpStatus.OK);

		return vResponseEntity;
	}

	@RequestMapping(//
			value = "/multiplication/v1", //
			method = RequestMethod.GET, //
			produces = MediaType.APPLICATION_JSON_VALUE//
	)
	public ResponseEntity<ResultOperation> multiplication_v1(//
			@Valid OperandsDTO pOperandosDTO//
	) {
		MDC.put(SystemConstants.ORIGIN, EnumOperantion.MULTIPLICATION.getName());
		log.trace("Request receive : {}", pOperandosDTO.toString());
		pOperandosDTO.setOperation(EnumOperantion.MULTIPLICATION);

		String vResult = this.aRabbitmqService.sendMessage(pOperandosDTO);

		ResultOperation vResultOperation = new ResultOperation(vResult);
		ResponseEntity<ResultOperation> vResponseEntity = new ResponseEntity<>(vResultOperation, HttpStatus.OK);

		return vResponseEntity;
	}

	@RequestMapping(//
			value = "/subtraction/v1", //
			method = RequestMethod.GET, //
			produces = MediaType.APPLICATION_JSON_VALUE//
	)
	public ResponseEntity<ResultOperation> subtraction_v1(//
			@Valid OperandsDTO pOperandosDTO//
	) {
		MDC.put(SystemConstants.ORIGIN, EnumOperantion.SUBTRACTION.getName());
		log.trace("Request receive : {}", pOperandosDTO.toString());
		pOperandosDTO.setOperation(EnumOperantion.SUBTRACTION);

		String vResult = this.aRabbitmqService.sendMessage(pOperandosDTO);

		ResultOperation vResultOperation = new ResultOperation(vResult);
		ResponseEntity<ResultOperation> vResponseEntity = new ResponseEntity<>(vResultOperation, HttpStatus.OK);

		return vResponseEntity;
	}

	@GetMapping(//
			value = "/sum/v1", //
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultOperation> sum_v1(//
			@Valid OperandsDTO pOperandosDTO//
	) {
		MDC.put(SystemConstants.ORIGIN, EnumOperantion.SUM.getName());
		log.trace("Request receive : {}", pOperandosDTO.toString());
		pOperandosDTO.setOperation(EnumOperantion.SUM);

		String vResult = this.aRabbitmqService.sendMessage(pOperandosDTO);

		ResultOperation vResultOperation = new ResultOperation(vResult);
		ResponseEntity<ResultOperation> vResponseEntity = new ResponseEntity<>(vResultOperation, HttpStatus.OK);

		return vResponseEntity;
	}
}
