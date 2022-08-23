package com.sxgroup.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxgroup.challenge.model.Empresa;
import com.sxgroup.challenge.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresa")
@CrossOrigin("*")
public class EmpresaController {

	@Autowired
	private EmpresaRepository repository;

	@GetMapping
	public ResponseEntity<List<Empresa>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Empresa>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<List<Empresa>> GetByCpf(@PathVariable String cnpj) {
		return ResponseEntity.ok(repository.findAllByCnpjContainingIgnoreCase(cnpj));
	}

	@PostMapping
	public ResponseEntity<Empresa> post(@RequestBody Empresa empresa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(empresa));
	}

	@PutMapping
	public ResponseEntity<Empresa> put(@Valid @RequestBody Empresa empresa) {
		return repository.findById(empresa.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(empresa)))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
