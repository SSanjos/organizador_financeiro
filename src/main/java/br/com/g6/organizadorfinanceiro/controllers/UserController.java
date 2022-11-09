package br.com.g6.organizadorfinanceiro.controllers;

import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> DeleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
        return new ResponseEntity<Long>(userId, HttpStatus.OK);


    }

}
