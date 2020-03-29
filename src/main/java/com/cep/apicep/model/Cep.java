package com.cep.apicep.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TCEP")
public class Cep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="IBGE", referencedColumnName = "IBGE")
	private Cidade cidade;
	
	public Cep () {}
	
	public Cep(String cep, String logradouro, String complemento, String bairro, Cidade cidade) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
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
		
	public Cidade getCidade() {
		return cidade;
	}

}
