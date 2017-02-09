package br.com.m2msolutions.workerbilhetagem.commom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	@Value("${authentication.rj.username}")
	private String usernameRJ;

	@Value("${authentication.rj.password}")
	private String passwordRJ;

	public String getUsernameRJ() {
		return usernameRJ;
	}

	public void setUsernameRJ(String usernameRJ) {
		this.usernameRJ = usernameRJ;
	}

	public String getPasswordRJ() {
		return passwordRJ;
	}

	public void setPasswordRJ(String passwordRJ) {
		this.passwordRJ = passwordRJ;
	}

}
