package com.cep.apicep.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cep.apicep.dto.ViaCepDto;
import com.cep.apicep.model.Cep;
import com.cep.apicep.repository.CepRepository;
import com.cep.apicep.service.restTemplate.RestTemplateCep;

@Service
public class CepService {	

	@Autowired
	private RestTemplateCep restTemplateCep;
	
	@Autowired
	private CepRepository repository;

	public List<Cep> obterCepDeCidade(String ibge, String uf) {
		
		return repository.findByCidadeIbgeAndUf(ibge, uf);
	}
	
	public List<Cep> obterCepDeCidade(String ibge) {
		
		return repository.findByCidadeIbge(ibge);
	}

	public Cep obterCep(String cep) {
		Optional<Cep> optional = repository.findByCep(cep);
		
		if (optional.isPresent()) {
			return optional.get();
		}

		return null;	
	}

	public Cep buscarCriarCep(String cep) throws URISyntaxException {
		ViaCepDto viaCepDto = restTemplateCep.obterCep(cep);
		
		if (!Objects.equals(viaCepDto, null)) {
			Cep objCep = viaCepDto.convert();
			repository.save(objCep);
			return objCep;
		}
		return null;	
	}

}
