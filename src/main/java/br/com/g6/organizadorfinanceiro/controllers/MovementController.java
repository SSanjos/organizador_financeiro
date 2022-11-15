package br.com.g6.organizadorfinanceiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.UserBalanceResponse;
import br.com.g6.organizadorfinanceiro.services.MovementService;

@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

	@Autowired
	private MovementService movementService;

//~pega todos os movimentos do usuário logado ~ //
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Movement>> getAll() {
		try {
			return ResponseEntity.ok(movementService.findAll());
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// ~exibe o saldo resultante  das movimentações do usuário ~ //
	@GetMapping("/balance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserBalanceResponse> getBalance() {
		return ResponseEntity.ok(movementService.getBalance());
	}

	// ~filtra os movimentos por despesa/receita ~ //
	@GetMapping("/{typeMovement}")
//    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Movement>> findByTypeMovement(@PathVariable("typeMovement") String typeMovement) {
		try {
			return ResponseEntity.ok(movementService.findByTypeMovement(typeMovement));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// ~cria uma nova movimentação ~ //
	@PostMapping("/save")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Movement> post(@RequestBody Movement createMovement) {
		return ResponseEntity.status(HttpStatus.CREATED)
				// STATUS DE QUE FOI CRIADO
				.body(movementService.save(createMovement));
	}

	// ~atualizando o movimentofiltra os movimentos por despesa/receita ~ //
	@PutMapping("/change")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Movement> put(@RequestBody Movement movement) {
		return ResponseEntity.status(HttpStatus.OK).body(movementService.save(movement));

	}

	@DeleteMapping("/{idMovement}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Long> DeleteMovement(@PathVariable Long idMovement) {
		movementService.deleteById(idMovement);
		return new ResponseEntity<Long>(idMovement, HttpStatus.OK);
	}

}
