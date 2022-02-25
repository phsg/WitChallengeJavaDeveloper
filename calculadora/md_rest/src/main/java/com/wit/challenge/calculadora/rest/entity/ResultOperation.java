package com.wit.challenge.calculadora.rest.entity;

import lombok.Getter;

/**
 * Retorna o resultado da operação
 * 
 * @author phsg5
 *
 */
@Getter
public class ResultOperation {

	private String result;

	public ResultOperation(String result) {
		super();
		this.result = result;
	}

}
