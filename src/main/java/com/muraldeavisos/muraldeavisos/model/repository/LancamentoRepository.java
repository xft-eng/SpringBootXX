package com.muraldeavisos.muraldeavisos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muraldeavisos.muraldeavisos.model.entity.Lancamento;

public interface LancamentoRepository  extends JpaRepository<Lancamento, Integer>{
	

}
