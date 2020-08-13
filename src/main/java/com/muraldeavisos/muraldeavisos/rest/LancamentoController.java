package com.muraldeavisos.muraldeavisos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muraldeavisos.muraldeavisos.model.entity.Lancamento;
import com.muraldeavisos.muraldeavisos.model.repository.LancamentoRepository;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {
	
	private final LancamentoRepository repository;
	@Autowired
	public LancamentoController(LancamentoRepository repository) {
		this.repository = repository;
	}
	
		@PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Lancamento salvar( @RequestBody Lancamento lancamento ){
	        return repository.save(lancamento);
	    }
		
		@RequestMapping("/busca")
		    public List<Lancamento> getLancamento(@RequestBody Lancamento request){
		        List<Lancamento> lancamento = repository.findAll();
		        return lancamento;
		    }
		
		  @DeleteMapping("{id}")
		    @ResponseStatus(HttpStatus.NO_CONTENT)
		    public void deletar( @PathVariable Integer id ){
		        repository
		            .findById(id)
		            .map( lancamento -> {
		                repository.delete(lancamento);
		                return Void.TYPE;
		            })
		            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviso não encontrado") );
		    }

		    @PutMapping("{id}")
		    @ResponseStatus(HttpStatus.NO_CONTENT)
		    public void atualizar( @PathVariable Integer id,
		                           @RequestBody  Lancamento lancamentoAtualizado ) {
		        repository
		                .findById(id)
		                .map( lancamento -> {
		                	lancamento.setTitulo(lancamentoAtualizado.getTitulo());
		                	lancamento.setDescricao(lancamentoAtualizado.getDescricao());
		                    return repository.save(lancamento);
		                })
		                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviso não encontrado") );
		    }
}
