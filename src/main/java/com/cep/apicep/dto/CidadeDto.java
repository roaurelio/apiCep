package com.cep.apicep.dto;

import com.cep.apicep.model.Cidade;

public class CidadeDto {

	private String ibge;
	private String uf;
	private String localidade;

	
	public CidadeDto() {}


	public CidadeDto(Cidade cidade) {
		
		this.ibge = cidade.getIbge();
		this.uf = cidade.getUf();
		this.localidade = cidade.getLocalidade();
	}


	public String getIbge() {
		return ibge;
	}

	public String getUf() {
		return uf;
	}

	public String getLocalidade() {
		return localidade;
	}

}
