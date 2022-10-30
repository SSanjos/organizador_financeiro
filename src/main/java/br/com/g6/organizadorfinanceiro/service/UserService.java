package br.com.g6.organizadorfinanceiro.service;


import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepository;




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
