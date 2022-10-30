package br.com.g6.organizadorfinanceiro.service;

import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class MovementService {
    @Autowired(required = true)
    private MovementRepository movementRepository;



   public Movement createdMovement(Movement movement) {

      return movementRepository.save(movement);
   }
}
