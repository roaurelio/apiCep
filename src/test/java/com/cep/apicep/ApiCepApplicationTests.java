package com.cep.apicep;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.cep.apicep.model.Cep;
import com.cep.apicep.service.CepService;
import com.cep.apicep.service.restTemplate.RestTemplateCep;

@SpringBootTest
class ApiCepApplicationTests {

	
	@Mock
	private RestTemplateCep restTemplateCep;
	
	@Autowired
	private CepService cepService;
	
	@Test
	void buscarCepSucesso() {
		
		try {
			Cep cep = cepService.buscarCriarCep("29102380");
			assertThat(cep).isNotNull();
			assertThat(cep.getCep()).isNotNull();
			
			System.out.println(cep.getBairro());
			System.out.println(cep.getLogradouro());
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void buscarCepErroEntrada() {
		Boolean thrown = false;
		try {
			cepService.buscarCriarCep("2910238");
			
		} catch (HttpClientErrorException | URISyntaxException e) {
			thrown = true;
		}
		assertThat(thrown).isTrue();
		
	}

}
