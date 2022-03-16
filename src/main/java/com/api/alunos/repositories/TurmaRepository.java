package com.api.alunos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alunos.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer>{
}
