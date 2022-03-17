package com.api.alunos.controllers;

import java.util.List;
import java.util.Optional;

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

import com.api.alunos.entities.Alunos;
import com.api.alunos.entities.Turma;
import com.api.alunos.repositories.AlunosRepository;
import com.api.alunos.repositories.TurmaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Alunos")
public class AlunosController {

	@Autowired
	private AlunosRepository alunosRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	

	@ApiOperation(value = "Retorna uma lista de alunos")
	@GetMapping("/alunos")
	public ResponseEntity<List<Alunos>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(alunosRepository.findAll());
	}

	@ApiOperation(value = "Retorna um unico aluno")
	@GetMapping("/alunos/{id}")
	public ResponseEntity<Object> alunoUnico(@PathVariable Integer id) {
		Optional<Alunos> alunoOptional = alunosRepository.findById(id);
		if(!alunoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunosRepository.findById(id));
	}

	@ApiOperation(value = "Salva um aluno")
	@PostMapping("/alunos")
	public ResponseEntity<Alunos> salvaAluno(@RequestBody Alunos aluno) {
		return ResponseEntity.status(HttpStatus.CREATED).body(alunosRepository.save(aluno));
	}
	
	@ApiOperation(value = "Salva um aluno em uma determinada turma com base no ID digitado")
	@PostMapping("/alunos/{id}")
	public ResponseEntity<Object> salvaAlunoNaTurma(@PathVariable Integer id, @RequestBody Alunos aluno) {
		Optional<Turma> turmaOptional = turmaRepository.findById(id);
		if(!turmaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada.");
		}
		Turma turma = turmaRepository.getById(id);
		aluno.setTurma(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunosRepository.save(aluno));
	}
	

	@ApiOperation(value = "Deleta um aluno")
	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<Object> deletaAluno(@PathVariable Integer id) {
		Optional<Alunos> alunoOptional = alunosRepository.findById(id);
		if(!alunoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
		}
		alunosRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso.");
	}

	@ApiOperation(value = "Atualiza os dados do aluno")
	@PutMapping("/alunos/{id}")
	public ResponseEntity<Alunos> atualizaAluno(@PathVariable Integer id, @RequestBody Alunos aluno) {
		return alunosRepository.findById(id).map(record -> {
			if (aluno.getNome() != null)
				record.setNome(aluno.getNome());
			if (aluno.getDataDeNascimento() != null)
				record.setDataDeNascimento(aluno.getDataDeNascimento());
			Alunos updated = alunosRepository.save(record);
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
