package com.cep.apicep.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cep.apicep.dto.CepDto;
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	@GetMapping("/ceps")
	public ResponseEntity<List<CepDto>> obterCeps(String ibge, String uf){
		
		List<Cep> lista;
		
		if (ibge == null) {
			
			return ResponseEntity.badRequest().build();
		
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