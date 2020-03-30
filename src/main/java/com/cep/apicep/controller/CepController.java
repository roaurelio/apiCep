package com.cep.apicep.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cep.apicep.dto.CepDto;
import com.cep.apicep.exception.ErroConsultaViaCepException;
import com.cep.apicep.exception.ErroParametroNaoInformadoException;
import com.cep.apicep.exception.InvalidPathVariableException;
import com.cep.apicep.model.Cep;
import com.cep.apicep.service.CepService;


@RestController 
public class CepController {
	
	@Autowired
	private CepService cepService;
	
	@GetMapping("/cep/{cep}")
	@Transactional
	public ResponseEntity<CepDto> obterCep(@PathVariable String cep, 
			UriComponentsBuilder uriBuilder) {
		
		if (Objects.equals(cep, null) || cep.length()!=8) {
			throw new InvalidPathVariableException("Cep inválido");
		}
	
		Cep objCep = cepService.obterCep(cep);
		
		if (!Objects.equals(objCep, null)) {
			return ResponseEntity.ok(new CepDto(objCep));
		}		
		
		try {
			objCep = cepService.buscarCriarCep(cep);
			
			if  (!Objects.equals(objCep, null)) {
				
				URI uri = uriBuilder.path("/cep/{cep}").buildAndExpand(objCep.getCep()).toUri();
				return ResponseEntity.created(uri).body(new CepDto(objCep));
			}

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			throw new ErroConsultaViaCepException(e.getMessage());
		}		
	}
	
	@GetMapping("/ceps")
	public ResponseEntity<List<CepDto>> obterCeps(String ibge, String uf){
		
		List<Cep> lista;
		
		if (ibge == null) {
			
			throw new ErroParametroNaoInformadoException("É necessário informar o ibge para consulta");
		
		} else if (uf == null) {
			
			lista = cepService.obterCepDeCidade(ibge);
		
		} else {
			
			lista = cepService.obterCepDeCidade(ibge, uf);
		}
		
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(CepDto.converter(lista));	
	}

}
