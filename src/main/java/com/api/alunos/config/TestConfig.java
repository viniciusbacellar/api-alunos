package com.api.alunos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.alunos.entities.Alunos;
import com.api.alunos.repositories.AlunosRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private AlunosRepository alunosRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Alunos a1 = new Alunos(null, "José Silva", "A501", 14);
		Alunos a2 = new Alunos(null, "Marina Pereira", "A601", 15);
		Alunos a3 = new Alunos(null, "Luiz Ribeiro", "A501", 14);
		
		alunosRepository.saveAll(Arrays.asList(a1, a2, a3));
		
	}

}
