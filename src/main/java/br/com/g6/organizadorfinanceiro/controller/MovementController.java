package br.com.g6.organizadorfinanceiro.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import br.com.g6.organizadorfinanceiro.model.UserLogin;
import org.apache.commons.codec.binary.Base64;
import br.com.g6.organizadorfinanceiro.applicationSecurity.BasicSecurityConfig;
import br.com.g6.organizadorfinanceiro.controller.dto.CreateMovement;
import br.com.g6.organizadorfinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.service.MovementService;


@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

    @Autowired(required = true)
    private MovementService movementService;
    private BasicSecurityConfig basicSecurityConfig;
   
    @GetMapping
	public ResponseEntity<List<Movement>> getAll(){
		try {
			return ResponseEntity.ok(movementService.findAll());
		}
		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}//mudar o get all e findall para específico para o id

    @GetMapping("/user/{idUser}") // ver sacola, quando chegar uma requsição com o id
    public List<Movement> findByUserId(@PathVariable("idUser") Long idUser){
        return movementService.findByUserId(idUser);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Movement>> getAllMovementByUser(@RequestHeader("Authorization") String userToken) {
        // code that uses the language variable
        //DecodeToken
        byte[] tokerByte = Base64.decodeBase64(userToken.replaceAll("Basic ",""));
        String arrayToken = new String(tokerByte,Charset.forName("US-ASCII"));
        String emailToken = arrayToken.split(":",0)[0];
        return new ResponseEntity<List<Movement>>(movementService.getByUserEmail(emailToken), HttpStatus.OK);
    }




    @PostMapping("/create")
    public ResponseEntity<Movement> createMovement(@RequestBody CreateMovement createMovement){
        try {
            Movement movement = new Movement();
            movement.setTypeMovement(createMovement.getTypeMovement());
            movement.setDateMovement(createMovement.getDateMovement());
            movement.setValueMovement(createMovement.getValueMovement());
            movement.setDescriptionMovement(createMovement.getDescriptionMovement());

          Movement savedMovement = movementService.createdMovement(movement);

            return new ResponseEntity<Movement>(savedMovement, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}


