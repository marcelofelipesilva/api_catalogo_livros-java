package com.api_catalago_livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_catalago_livros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
