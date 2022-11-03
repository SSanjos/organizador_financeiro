package br.com.g6.organizadorfinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.service.MovementService;

@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

    @Autowired(required = true)
    private MovementService movementService;
   
    @GetMapping
	public ResponseEntity<List<Movement>> getAll(){
		try {
			return ResponseEntity.ok(movementService.findAll());
		}
		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        try {
          Movement savedMovement = movementService.createdMovement(movement);



            return new ResponseEntity<Movement>(savedMovement, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}


