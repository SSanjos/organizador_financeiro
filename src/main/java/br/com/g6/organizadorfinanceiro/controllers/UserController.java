package br.com.g6.organizadorfinanceiro.controllers;

import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll(){
        try {
            return ResponseEntity.ok(userService.findAll());
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Long> DeleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);


    }




}
