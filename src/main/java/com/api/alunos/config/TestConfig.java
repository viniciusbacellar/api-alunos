package com.api.alunos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.alunos.entities.Turma;
import com.api.alunos.repositories.TurmaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private TurmaRepository turmaRepository;
	

	@Override
	public void run(String... args) throws Exception {

		Turma t1 = new Turma(null, "A502", 20);
		
		turmaRepository.saveAll(Arrays.asList(t1));
		
		

	}

}
