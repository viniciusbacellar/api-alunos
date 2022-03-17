package com.api.alunos.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.alunos.entities.Alunos;
import com.api.alunos.repositories.AlunosRepository;

@Controller
public class CadastroController {

	@Autowired
	private AlunosRepository alunosRepository;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView getAlunos() {
		ModelAndView mv = new ModelAndView("cadastro");
		List<Alunos> alunos = alunosRepository.findAll();
		mv.addObject("alunos", alunos);
		return mv;
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String saveAluno(Alunos aluno, BindingResult result, RedirectAttributes attributes) {
		attributes.addAttribute("dataDeNascimento", aluno.getDataDeNascimento());
		alunosRepository.save(aluno);
		return "redirect:/cadastro";
	}
	
}
