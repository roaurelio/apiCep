package com.cep.apicep.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.cep.apicep.model.Cep;

public class CepDto {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	CidadeDto cidade;
	
	
	public CepDto(Cep cep) {
		
		this.cep = cep.getCep().substring(0, 5)+"-"+cep.getCep().substring(5, 8);
		this.logradouro = cep.getLogradouro();
		this.complemento = cep.getComplemento();
		this.bairro = cep.getBairro();
		
		this.cidade = new CidadeDto(cep.getCidade());
		
	}
	
	public String getCep() {
		return cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public CidadeDto getCidade() {
		return cidade;
	}

	public static List<CepDto> converter(List<Cep> lista) {
		
		return lista.stream().map(CepDto::new).collect(Collectors.toList());
		
	}

}
