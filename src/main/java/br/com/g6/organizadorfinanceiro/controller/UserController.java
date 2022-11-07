package br.com.g6.organizadorfinanceiro.controller;

import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.model.UserLogin;
import br.com.g6.organizadorfinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/login")
    public ResponseEntity<UserLogin> authentication(@RequestBody Optional<UserLogin> userLogin) {
        return userService.logar(userLogin)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .build());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> post(@RequestBody User user) {
        try {
            User savedUser = userService.userRegistration(user);


            return new ResponseEntity<User>(savedUser, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}