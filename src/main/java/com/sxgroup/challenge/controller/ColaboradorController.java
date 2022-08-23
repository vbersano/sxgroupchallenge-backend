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

import com.sxgroup.challenge.model.Colaborador;
import com.sxgroup.challenge.repository.ColaboradorRepository;

@RestController
@RequestMapping("/colaborador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColaboradorController {

	@Autowired
	private ColaboradorRepository repository;

	@GetMapping
	public ResponseEntity<List<Colaborador>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Colaborador>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Colaborador>> GetByCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(repository.findAllByCpfContainingIgnoreCase(cpf));
	}

	@PostMapping
	public ResponseEntity<Colaborador> post(@RequestBody Colaborador colaborador) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(colaborador));
	}

	@PutMapping
	public ResponseEntity<Colaborador> put(@Valid @RequestBody Colaborador colaborador) {
		return repository.findById(colaborador.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(colaborador)))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Colaborador> delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}