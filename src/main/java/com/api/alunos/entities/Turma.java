package com.api.alunos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_turma")
public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private int capacidade;
	
	@ManyToOne
	private Escola escola;
	
	@OneToMany(mappedBy = "turma")
	private List<Alunos> listAluno = new ArrayList<>();
	
	public Turma() {
	}

	
	public Turma(Integer id, String nome, int capacidade) {
		this.id = id;
		this.nome = nome;
		this.capacidade = capacidade;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public List<Alunos> getListAluno() {
		return listAluno;
	}

	public Escola getEscola() {
		return escola;
	}


	public void setEscola(Escola escola) {
		this.escola = escola;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		return id == other.id;
	}
	
	
	
}
