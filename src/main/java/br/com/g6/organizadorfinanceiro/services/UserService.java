package br.com.g6.organizadorfinanceiro.services;


import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired(required = true)
    private UserRepository userRepository;

    public List<User> findAll(){
        try {
            UserService userRepository = null;
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
