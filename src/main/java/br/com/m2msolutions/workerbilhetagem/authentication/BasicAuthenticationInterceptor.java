package br.com.m2msolutions.workerbilhetagem.authentication;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BasicAuthenticationInterceptor implements ClientHttpRequestInterceptor {

	private String username;
	private String password;

	public BasicAuthenticationInterceptor(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		final String auth = username + ":" + password;
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		final String authHeader = "Basic " + new String(encodedAuth);

		request.getHeaders().add("Authorization", authHeader);

		return execution.execute(request, body);
	}
}
