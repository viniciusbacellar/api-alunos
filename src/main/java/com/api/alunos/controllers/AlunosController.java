package com.api.alunos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.alunos.repositories.AlunosRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Alunos")
public class AlunosController {

	@Autowired
	private AlunosRepository alunosRepository;

	@ApiOperation(value = "Retorna uma lista de alunos")
	@GetMapping("/alunos")
	public List<Alunos> findAll() {
		return alunosRepository.findAll();
	}

	@ApiOperation(value = "Retorna um unico aluno")
	@GetMapping("/alunos/{id}")
	public Optional<Alunos> alunoUnico(@PathVariable Integer id) {
		return alunosRepository.findById(id);
	}

	@ApiOperation(value = "Salva um aluno")
	@PostMapping("/alunos")
	public Alunos salvaAluno(@RequestBody Alunos aluno) {
		return alunosRepository.save(aluno);
	}

	@ApiOperation(value = "Deleta um aluno")
	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		alunosRepository.deleteById(id);
		return ResponseEntity.noContent().build();
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
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
