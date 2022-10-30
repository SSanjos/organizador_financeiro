package br.com.g6.organizadorfinanceiro.controller;

import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.service.MovementService;
import br.com.g6.organizadorfinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

    @Autowired(required = true)
    private MovementService movementService;

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


