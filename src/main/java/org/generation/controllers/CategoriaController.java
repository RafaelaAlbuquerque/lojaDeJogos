package org.generation.controllers;

import java.util.List;

import org.generation.models.CategoriaModel;
import org.generation.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/categorias")
	public ResponseEntity<List<CategoriaModel>> buscarTodasCategorias (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/categorias/id/{id}")
	public ResponseEntity<CategoriaModel> buscarCategoriaPorId(@PathVariable){
		return repository.findById(id).map(categoria -> ResponseEntity.ok(categoria))
	}
}
