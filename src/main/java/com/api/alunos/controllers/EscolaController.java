package com.api.alunos.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alunos.entities.Escola;
import com.api.alunos.repositories.EscolaRepository;

@RestController
@RequestMapping(value = "/api")
public class EscolaController {

	@Autowired
	private EscolaRepository escolaRepository;
	
	@GetMapping("/escola")
	public ResponseEntity<List<Escola>> findAll() {
		return ResponseEntity.ok().body(escolaRepository.findAll());
	}
	
	@GetMapping("/escola/{id}")
	public ResponseEntity<Optional<Escola>> escolaUnica(@PathVariable Integer id) {
		return ResponseEntity.ok().body(escolaRepository.findById(id));
	}
	
	@PostMapping("/escola")
	@Transactional
	public ResponseEntity<Escola> salvaEscola(@RequestBody Escola escola) {
		return ResponseEntity.ok().body(escolaRepository.save(escola));
	}
	
	@DeleteMapping("/escola/{id}")
	@Transactional
	public ResponseEntity<Void> deletaEscola(@PathVariable Integer id) {
		escolaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/escola/{id}")
	public ResponseEntity<Escola> atualizaEscola(@PathVariable Integer id, @RequestBody Escola escola) {
		return escolaRepository.findById(id)
				.map(record -> {
					if(escola.getNome() != null)
					record.setNome(escola.getNome());
					if(escola.getEndereco() != null)
					record.setEndereco(escola.getEndereco());
					Escola updated = escolaRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
}