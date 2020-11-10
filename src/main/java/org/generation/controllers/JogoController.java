package org.generation.controllers;

import java.util.List;

import org.generation.models.JogoModel;
import org.generation.repositories.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogoController {

	@Autowired
	private JogoRepository repository;
	
	@GetMapping("/jogos")
	public ResponseEntity<List<JogoModel>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/jogos/id/{id}")
	public ResponseEntity<JogoModel> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/jogos/nome/{nome}")
	public ResponseEntity<List<JogoModel>> GetByNome(@PathVariable String nome){
	return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));	
	}
	
	@PostMapping("/jogos/post")
	public ResponseEntity<JogoModel> post (@RequestBody JogoModel jogos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogos));
	}
	
//	@PostMapping("/jogos/post")
//	public JogoModel criar (@RequestBody JogoModel jogos) {
//		repository.save(jogos);
//		return jogos;
//	}
	
	 @PutMapping("/jogos/put/{id}")
	public JogoModel atualizar (@PathVariable Long id, @RequestBody JogoModel jogos) {
		jogos.setId(id);
		repository.save(jogos);
		return(jogos);
	}
	
	@DeleteMapping("/jogos/delete/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
