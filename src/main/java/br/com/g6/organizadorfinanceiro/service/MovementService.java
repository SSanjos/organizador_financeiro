package br.com.g6.organizadorfinanceiro.service;

import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MovementService {
	@Autowired(required = true)
    private MovementRepository movementRepository;

    public List<Movement> findAll(){
        try {
            return movementRepository.findAll();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Movimento não encontrado" + e.getMessage());
        }
    }
    
    public Movement createdMovement(Movement movement) {

      return movementRepository.save(movement);
    }
    
    public void deleteById(Long idMovement){
        try {
        	movementRepository.deleteById(idMovement);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Usuário não encontrado " + idMovement + ": " + e.getMessage());
        }

    }
}
