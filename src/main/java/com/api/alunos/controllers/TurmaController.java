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
import com.api.alunos.entities.Turma;
import com.api.alunos.repositories.EscolaRepository;
import com.api.alunos.repositories.TurmaRepository;

@RestController
@RequestMapping(value = "/api")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@GetMapping("/turma")
	public ResponseEntity<List<Turma>> findAll() {
		return ResponseEntity.ok().body(turmaRepository.findAll());
	}
	
	@GetMapping("/turma/{id}")
	public ResponseEntity<Optional<Turma>> turmaUnica(@PathVariable Integer id) {
		return ResponseEntity.ok().body(turmaRepository.findById(id));
	}
	
	@PostMapping("/turma/{id}")
	@Transactional
	public ResponseEntity<Turma> salvaTurma(@PathVariable Integer id, @RequestBody Turma turma) {
		Escola escola = escolaRepository.getById(id);
		turma.setEscola(escola);
		return ResponseEntity.ok().body(turmaRepository.save(turma));
	}
	
	@DeleteMapping("/turma/{id}")
	@Transactional
	public ResponseEntity<Void> deletaTurma(@PathVariable Integer id) {
		turmaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/turma/{id}")
	public ResponseEntity<Turma> atualizaTurma(@PathVariable Integer id, @RequestBody Turma turma) {
		return turmaRepository.findById(id)
				.map(record -> {
					if(turma.getNome() != null)
					record.setNome(turma.getNome());
					if(turma.getCapacidade() != null)
					record.setCapacidade(turma.getCapacidade());
					Turma updated = turmaRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
}
