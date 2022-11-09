package br.com.g6.organizadorfinanceiro.service;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovementService {
    @Autowired(required = true)
    private MovementRepository movementRepository;
    @Autowired(required = true)
    private UserRepository userRepository;



    public List<Movement> findAll() {
        try {
            return (List<Movement>) movementRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Movimento não encontrado" + e.getMessage());
        }
    }

    public Movement createdMovement(Movement movement) {

        return movementRepository.save(movement);
    }

    public List<Movement> getByUserEmail(String email) {

       Optional<User> usuario = userRepository.findByUserEmail(email);
       if(usuario.isPresent())
       {
           return movementRepository.findByUserId(usuario.get().getIdUser());
       }

        return null;
    }

    public List<Movement> findByUserId(Long idUser){
        try {
            return (List<Movement>) movementRepository.findByUserId(idUser);
        } catch (Exception e) {
            throw new RuntimeException("Nao há movimentos" + e.getMessage());
        }

    }
    
    public List<Movement> findByTypeMovement(String type){
        try {
        	TypeMovement typeMovement = TypeMovement.get(type);
        	if(typeMovement == null) {
        		throw new RuntimeException("Tipo de movimento inválido");
        	}
            return (List<Movement>) movementRepository.findByTypeMovement(typeMovement);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

}
