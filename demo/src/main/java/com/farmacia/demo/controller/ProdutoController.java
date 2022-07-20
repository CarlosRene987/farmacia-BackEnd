package com.farmacia.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farmacia.demo.model.Produto;
import com.farmacia.demo.repository.RepositoryProduto;

@Controller
@RequestMapping("/produto")

@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private RepositoryProduto repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> Getall(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@Valid @PathVariable long id) {
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	
	}
	@GetMapping("/valor/{valor}")
	public ResponseEntity<List<Produto>> GetByValor(@Valid @PathVariable String valor){
		return ResponseEntity.ok(repository.findAllByvalorContainingIgnoreCase(valor));
	}
	
@PostMapping
public ResponseEntity<Produto> Post(@RequestBody Produto produto){
	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
}

@PutMapping
public ResponseEntity<Produto> Put(@RequestBody Produto produto){
	return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
}

@DeleteMapping("{id}")
public void delete(@Valid @PathVariable Long id) {
	repository.deleteById(id);
}
}
