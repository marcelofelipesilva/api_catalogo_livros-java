package com.api_catalogo_livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_catalogo_livros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
