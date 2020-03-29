package com.cep.apicep.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TCIDADE")
public class Cidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String ibge;	
	private String uf;	
	private String localidade;
	
	@OneToMany(mappedBy="cidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cep> ceps = new ArrayList<>();
	
	public Cidade() {}
		
	public Cidade(String ibge, String uf, String localidade) {
		
		this.ibge = ibge;
		this.uf = uf;
		this.localidade = localidade;
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


	public List<Cep> getCeps() {
		return ceps;
	}
	
}
