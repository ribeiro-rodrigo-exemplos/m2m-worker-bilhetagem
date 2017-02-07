package br.com.m2msolutions.workerbilhetagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.m2msolutions.workerbilhetagem.service.BuscaVendasService;

@SpringBootApplication
@EnableScheduling
public class M2mWorkerBilhetagemApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(M2mWorkerBilhetagemApplication.class, args);

		BuscaVendasService buscaVendas = ctx.getBean(BuscaVendasService.class);
		buscaVendas.buscarVendasService();
		
		// SpringApplication.run(M2mWorkerBilhetagemApplication.class, args);

	}
}
