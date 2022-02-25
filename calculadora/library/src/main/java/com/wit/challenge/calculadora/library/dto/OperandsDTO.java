package com.wit.challenge.calculadora.library.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.wit.challenge.calculadora.library.util.EnumOperantion;

import lombok.Getter;
import lombok.Setter;

public class OperandsDTO implements Serializable {

	/** */
	private static final long serialVersionUID = -3774290452726264152L;

	@NotNull
	@Getter
	@Setter
	private BigDecimal a;

	@NotNull
	@Getter
	@Setter
	private BigDecimal b;

	@Getter
	@Setter
	private EnumOperantion operation;

	@Override
	public String toString() {
		return "OperandsDTO [a=" + a + ", b=" + b + ", operation=" + operation + "]";
	}

}
