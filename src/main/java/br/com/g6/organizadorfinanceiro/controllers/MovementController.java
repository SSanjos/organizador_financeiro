package br.com.g6.organizadorfinanceiro.controllers;

import br.com.g6.organizadorfinanceiro.dto.MovementDto;
import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.UserBalanceResponse;
import br.com.g6.organizadorfinanceiro.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

	@Autowired
	private MovementService movementService;

//	~filtra os movimentos~ //
	@GetMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Movement>> findByFilter(@RequestBody MovementDto filter) {
		try {
			return ResponseEntity.ok(movementService.findByFilter(filter));
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

	// ~exibe o saldo resultante das movimentações do usuário ~ //
	@GetMapping("/balance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserBalanceResponse> getBalance() {
		return ResponseEntity.ok(movementService.getBalance());
	}

}