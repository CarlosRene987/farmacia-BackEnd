package com.farmacia.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.demo.model.Categoria;
import com.farmacia.demo.repository.RepositoryCategoria;

@RestController
@RequestMapping("/categoria")

@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CategoriaController {
	
	@Autowired
	private RepositoryCategoria repository;
	
	@GetMapping 
	public ResponseEntity<List<Categoria>> Getall() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable Long Id)
	{
		return repository.findById(Id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String categoria)
	{
		return ResponseEntity.ok(repository.findAllBycategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> AdicionaCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> atualizaCategoria(@RequestBody Categoria categoria)
	
	{
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deletaCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}

}
