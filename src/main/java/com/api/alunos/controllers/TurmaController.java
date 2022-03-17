package com.api.alunos.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.findAll());
	}
	
	@GetMapping("/turma/{id}")
	public ResponseEntity<Object> turmaUnica(@PathVariable Integer id) {
		Optional<Turma> turmaOptional = turmaRepository.findById(id);
		if(!turmaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.findById(id));
	}
	
	@PostMapping("/turma/{id}")
	@Transactional
	public ResponseEntity<Object> salvaTurma(@PathVariable Integer id, @RequestBody Turma turma) {
		Optional<Escola> escolaOptional = escolaRepository.findById(id);
		if(!escolaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Escola não encontrada.");
		}
		Escola escola = escolaRepository.getById(id);
		turma.setEscola(escola);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaRepository.save(turma));
	}
	
	@DeleteMapping("/turma/{id}")
	@Transactional
	public ResponseEntity<Object> deletaTurma(@PathVariable Integer id) {
		Optional<Turma> turmaOptional = turmaRepository.findById(id);
		if(!turmaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada.");
		}
		turmaRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Turma deletada com sucesso.");
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
					return ResponseEntity.status(HttpStatus.OK).body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
}
