package com.cep.apicep.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cep.apicep.model.Cep;


public interface CepRepository extends JpaRepository<Cep, Long>{
	
	@Query(value = "select cep from Cep cep join fetch cep.cidade c where c.uf=:uf and c.ibge = :ibge")
	public List<Cep> findByCidadeIbgeAndUf(String ibge, String uf);

	public Optional<Cep> findByCep(String cep);

	@Query(value = "select cep from Cep cep join fetch cep.cidade c where c.ibge = :ibge")
	public List<Cep> findByCidadeIbge(String ibge);
}
