package org.generation.controllers;

import java.util.List;

import org.generation.models.CategoriaModel;
import org.generation.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<CategoriaModel> buscarCategoriaPorId(@PathVariable Long id){
		return repository.findById(id).map(categoria -> ResponseEntity.ok(categoria)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/categorias/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModel>> buscarPorDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(descricao));
	}
	

	
	@PostMapping("/categorias/post")
	public CategoriaModel criar (@RequestBody CategoriaModel categoria) {
		repository.save(categoria);
		return categoria;
	}

	@PutMapping("/categorias/put/{id}")
	public CategoriaModel atualizar (@PathVariable Long id, @RequestBody CategoriaModel categoria) {
		categoria.setId(id);
		repository.save(categoria);
		return(categoria);
	}
	
	@DeleteMapping ("/categoria/delete/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}


}
