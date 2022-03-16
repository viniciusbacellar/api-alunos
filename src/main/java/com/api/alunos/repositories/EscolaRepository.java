package com.api.alunos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alunos.entities.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer>{

}
