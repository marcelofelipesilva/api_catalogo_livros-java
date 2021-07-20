package com.api_catalago_livros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api_catalago_livros.repository.LivroRepository;
import com.api_catalago_livros.model.Livro;

@RestController("/livro")
public class LivroController {

	@Autowired 
	private LivroRepository _livroRepository;

	@GetMapping(value = "/livro", produces = "application/json")
	public List<Livro> Exibir_Livros() {
		return _livroRepository.findAll();
	}

	@GetMapping(value = "/livro/{id}", produces = "application/json")
	public ResponseEntity<Livro> Exibir_Livro_Id(@PathVariable(value = "id") long id) {
		java.util.Optional<Livro> livro = _livroRepository.findById(id);
		if (livro.isPresent())
			return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/livro", produces = "application/json")
	public <S extends Livro> S Cadastro_Livro(S entity) {

		return _livroRepository.saveAndFlush(entity);
	}

	@PutMapping(value = "/livro/{id}", produces = "application/json")
	public ResponseEntity<Livro> Atualizar_Livro(@PathVariable(value = "id") long id,
			@Validated @RequestBody Livro newLivro) {
		java.util.Optional<Livro> oldLivro = _livroRepository.findById(id);
		if (oldLivro.isPresent()) {
			Livro livro = oldLivro.get();
			livro.setTitulo(newLivro.getTitulo());
			livro.setEditora(newLivro.getEditora());
			livro.setGenero(newLivro.getGenero());
			livro.setSinopse(newLivro.getSinopse());
			livro.setIsbn(newLivro.getIsbn());
			livro.setFoto_livro(newLivro.getFoto_livro());
			_livroRepository.save(livro);
			return new ResponseEntity<Livro>(livro, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/livro/{id}", produces = "application/json")
	public ResponseEntity<Livro> Deletar_Livro(@PathVariable(value = "id") long id) {
		java.util.Optional<Livro> livro = _livroRepository.findById(id);
		if (livro.isPresent()) {
			_livroRepository.delete(livro.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}