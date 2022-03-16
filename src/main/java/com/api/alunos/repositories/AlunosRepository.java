package com.api.alunos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alunos.entities.Alunos;

public interface AlunosRepository extends JpaRepository<Alunos, Integer>{
}
