package br.com.g6.organizadorfinanceiro.service;


import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.model.UserLogin;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired(required = true)
    private  UserRepository userRepository;


    public User userRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordEncoder = encoder.encode(user.getUserPassword());
        //pegou a senha, agora vai fazer a criptografia

        user.setUserPassword(passwordEncoder);
        return userRepository.save(user);
    }

    public Optional<UserLogin> logar(Optional<UserLogin> userLogin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByUserEmail(userLogin.get().getUserName());

        if(user.isPresent()){
            if(encoder.matches(userLogin.get().getPassword(), user.get().getUserPassword())){
                String auth = userLogin.get().getUserName() + ":" + userLogin.get().getPassword();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                userLogin.get().setToken(authHeader);
                userLogin.get().setName(user.get().getUserName());

                return userLogin;
            }
        }

        return null;
    }

    public List<User> findAll(){
        try {
            return userRepository.findAll();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Usuário não encontrado" + e.getMessage());
        }
    }

    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Erro ao salvar o usuário" + e.getMessage());
        }

    }
    public void deleteById(Long idUser){
        try {
            userRepository.deleteById(idUser);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Usuário não encontrado " + idUser + ": " + e.getMessage());
        }

    }


}
