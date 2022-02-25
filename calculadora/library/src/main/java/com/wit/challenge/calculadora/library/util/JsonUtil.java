package com.wit.challenge.calculadora.library.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.challenge.calculadora.library.dto.OperandsDTO;

public class JsonUtil {

	private static final Logger aLogger = LoggerFactory.getLogger(JsonUtil.class);

	public static OperandsDTO deserializeToJson(String pCandidate) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		TypeReference<OperandsDTO> mapType = new TypeReference<OperandsDTO>() {
		};

		try {
			return objectMapper.readValue(pCandidate, mapType);
		} catch (IOException pException) {
			aLogger.error("Error", String.valueOf(pException));
			throw pException;
		}
	}

	public static String serializeToJson(OperandsDTO pCandidate) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";

		try {
			jsonInString = mapper.writeValueAsString(pCandidate);
			aLogger.debug("Serialized message payload: {}", jsonInString);
			return jsonInString;
		} catch (JsonProcessingException pException) {
			aLogger.error("Error", String.valueOf(pException));
			throw pException;
		}
	}

}
