package br.com.g6.organizadorfinanceiro.controller;

import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.model.UserLogin;
import br.com.g6.organizadorfinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserLoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLogin> authentication(@RequestBody Optional<UserLogin> userLogin){
        return userService.logar(userLogin)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .build());
    }

    @PostMapping("/registration")
    public ResponseEntity<User> post(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.userRegistration(user));
    }
}
