package com.api.alunos.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import com.api.alunos.entities.Escola;
import com.api.alunos.repositories.EscolaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Alunos")
public class EscolaController {

	@Autowired
	private EscolaRepository escolaRepository;
	
	@ApiOperation(value = "Retorna uma lista de todas as escolas")
	@GetMapping("/escola")
	public ResponseEntity<List<Escola>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(escolaRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna uma escola unica com base no ID digitado")
	@GetMapping("/escola/{id}")
	public ResponseEntity<Object> escolaUnica(@PathVariable Integer id) {
		Optional<Escola> escolaOptional = escolaRepository.findById(id);
		if(!escolaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Escola não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(escolaRepository.findById(id));
	}
	
	@ApiOperation(value = "Cria uma nova escola")
	@PostMapping("/escola")
	@Transactional
	public ResponseEntity<Escola> salvaEscola(@RequestBody Escola escola) {
		return ResponseEntity.status(HttpStatus.CREATED).body(escolaRepository.save(escola));
	}
	
	@ApiOperation(value = "Deleta a escola com base no ID digitado")
	@DeleteMapping("/escola/{id}")
	@Transactional
	public ResponseEntity<Object> deletaEscola(@PathVariable Integer id) {
		Optional<Escola> escolaOptional = escolaRepository.findById(id);
		if(!escolaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Escola não encontrada.");
		}
		escolaRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Escola deletada com sucesso.");
	}
	
	@ApiOperation(value = "Atualiza dados da escola especificando o ID")
	@PutMapping("/escola/{id}")
	public ResponseEntity<Escola> atualizaEscola(@PathVariable Integer id, @RequestBody Escola escola) {
		return escolaRepository.findById(id)
				.map(record -> {
					if(escola.getNome() != null)
					record.setNome(escola.getNome());
					if(escola.getEndereco() != null)
					record.setEndereco(escola.getEndereco());
					Escola updated = escolaRepository.save(record);
					return ResponseEntity.status(HttpStatus.OK).body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
}
