package br.com.g6.organizadorfinanceiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import br.com.g6.organizadorfinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	

	@Autowired
	private UserService userService;



	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		try {
			return ResponseEntity.ok(userService.findAll());

		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	@DeleteMapping(value = "{userId}")
	public ResponseEntity<Long> DeleteUser(@PathVariable Long userId){
		userService.deleteById(userId);
		return new ResponseEntity<Long>(userId, HttpStatus.OK);


	}


	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User savedUser = userService.saveUser(user);

//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedStudent.getId()).toUri();

			return new ResponseEntity<User>(savedUser, HttpStatus.OK);

		}
		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}


	}

}
