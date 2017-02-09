package br.com.m2msolutions.workerbilhetagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class M2mWorkerBilhetagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(M2mWorkerBilhetagemApplication.class, args);
	}
}
