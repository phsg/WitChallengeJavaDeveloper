package com.wit.challenge.calculadora.rest.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;

import com.wit.challenge.calculadora.library.constants.SystemConstants;

/**
 * Filtra as requisições HTTP, se não encontrar o CorrelationId adiciona
 * 
 * @author phsg
 *
 */
@WebFilter
public class MdcFilter extends HttpFilter {

	/** */
	private static final long serialVersionUID = -7400656923916282096L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {

			Object vAttribute = request.getHeader(SystemConstants.CORRELATION_ID);
			String vCorrelationId;

			if (vAttribute == null || vAttribute.toString().trim().isEmpty()) {
				vCorrelationId = getCorrelationId();
				response.setHeader(SystemConstants.CORRELATION_ID, vCorrelationId);
			} else {
				vCorrelationId = vAttribute.toString();
			}

			MDC.put(SystemConstants.CORRELATION_ID, vCorrelationId);
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove(SystemConstants.CORRELATION_ID);
		}
	}

	private String getCorrelationId() {
		return UUID.randomUUID().toString();
	}
}