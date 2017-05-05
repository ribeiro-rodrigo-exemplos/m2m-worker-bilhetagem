package br.com.m2msolutions.workerbilhetagem.authentication;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class AnttAuthenticationInterceptor implements ClientHttpRequestInterceptor {

	private String token;

	public AnttAuthenticationInterceptor(String token) {
		this.token = token;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		final String authHeader = token;
		request.getHeaders().add("Authorization", authHeader);
		return execution.execute(request, body);
	}
}
