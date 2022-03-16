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

import com.api.alunos.entities.Turma;
import com.api.alunos.repositories.TurmaRepository;

@RestController
@RequestMapping(value = "/api")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@GetMapping("/turma")
	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}
	
	@GetMapping("/turma/{id}")
	public Optional<Turma> turmaUnica(@PathVariable Integer id) {
		return turmaRepository.findById(id);
	}
	
	@PostMapping("/turma")
	public Turma salvaTurma(@RequestBody Turma turma) {
		return turmaRepository.save(turma);
	}
	
	@DeleteMapping("/turma/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		turmaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/turma/{id}")
	public ResponseEntity<Turma> atualizaAluno(@PathVariable Integer id, @RequestBody Turma turma) {
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
