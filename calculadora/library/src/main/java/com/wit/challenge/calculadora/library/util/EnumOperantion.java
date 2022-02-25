package com.wit.challenge.calculadora.library.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum EnumOperantion {

	SUM('+', "Sum"), //
	SUBTRACTION('-', "Subtraction"), //
	MULTIPLICATION('*', "Multiplication"), //
	DIVISION('/', "Division");

	public static EnumOperantion getEnum(Character pSigla) {
		for (EnumOperantion vTemp : EnumOperantion.values()) {
			if (vTemp.getSymbol().equals(pSigla)) {
				return vTemp;
			}
		}
		return null;
	}

	/**
	 * @param pName
	 * @return
	 */
	@JsonCreator
	public static EnumOperantion getEnum(String pName) {
		for (EnumOperantion vTemp : EnumOperantion.values()) {
			if (vTemp.getName().equalsIgnoreCase(pName)) {
				return vTemp;
			}
		}
		return EnumOperantion.getEnum(pName.charAt(0));
	}

	private Character symbol;
	private String name;

	private EnumOperantion(Character pSymbol, String pName) {
		this.symbol = pSymbol;
		this.name = pName;
	}

	@JsonValue
	public String getName() {
		return name;
	}

}
