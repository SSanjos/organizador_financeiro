package br.com.g6.organizadorfinanceiro.security.servicesUser;


import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired(required = true)
    private UserRepository userRepository;


    private Optional<User> getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userLogged = userRepository.findByUsername(userDetails.getUsername());

        return userLogged;
    }

    public List<User> findAll(){
        try {
            return userRepository.findAll();
//            UserService userRepository = null;
//            return userRepository.findAll();
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



    public void deleteById(Long id){
        try {
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {

                userRepository.deleteById(id);
            }
        }catch (Exception e)
        {
            throw new RuntimeException("Usuário " + id + ": " + e.getMessage());
        }

    }
}
