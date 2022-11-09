package br.com.g6.organizadorfinanceiro.controllers;


import br.com.g6.organizadorfinanceiro.models.Movement;
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




    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Movement>> getAll() {
        try {
            return ResponseEntity.ok(movementService.findAll());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{typeMovement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Movement>> findByTypeMovement(@PathVariable("typeMovement") String typeMovement){
        try {
            return ResponseEntity.ok(movementService.findByTypeMovement(typeMovement));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Movement> post( @RequestBody Movement createMovement) {
        return ResponseEntity.status(HttpStatus.CREATED)
                //STATUS DE QUE FOI CRIADO
                .body(movementService.save(createMovement));
    }




}
