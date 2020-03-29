package com.cep.apicep.service.restTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cep.apicep.dto.ViaCepDto;


@Service
public class RestTemplateCep {
	
	private String baseUrl = "http://viacep.com.br/ws/";
	
	public ViaCepDto obterCep(String cep) throws URISyntaxException {
		
		RestTemplate template = new RestTemplate();
		
		URI url = new URI(baseUrl+ cep +"/json");
		return template.getForObject(url, ViaCepDto.class);
	}
}
