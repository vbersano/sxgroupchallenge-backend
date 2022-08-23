package com.sxgroup.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sxgroup.challenge.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	public List<Empresa> findAllByNomeContainingIgnoreCase(String nome);

	public List<Empresa> findAllByCnpjContainingIgnoreCase(String cnpj);

}