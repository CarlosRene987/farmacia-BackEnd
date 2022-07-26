package com.farmacia.demo.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.demo.model.Produto;



@Repository
public interface RepositoryProduto  extends JpaRepository<Produto, Long >{
	public List<Produto> findAllByvalorContainingIgnoreCase (String valor);
	public void deleteById(@Valid String id);

}
