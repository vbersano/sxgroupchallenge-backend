package com.sxgroup.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sxgroup.challenge.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

	public List<Colaborador> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Colaborador> findAllByCpfContainingIgnoreCase(String cpf);

}