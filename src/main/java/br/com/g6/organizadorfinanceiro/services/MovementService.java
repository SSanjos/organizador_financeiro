package br.com.g6.organizadorfinanceiro.services;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.models.UserBalanceResponse;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
import br.com.g6.organizadorfinanceiro.security.servicesUser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@RequiredArgsConstructor
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;


    //~~método para retornar qual usuário está logado ~~
    private Optional<User> getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userLogged = userRepository.findByUsername(userDetails.getUsername());

        return userLogged;
    }

    //~~~~Tratamento de erro - Movimento não encontrado~~~~
    public List<Movement> findAll() {
        try {
           Optional<User> user = getCurrentUser();

          return (movementRepository.findByUser(user.get()));
        } catch (Exception e) {
            throw new RuntimeException("Movimento não encontrado" + e.getMessage());
        }
    }


//~~método para salvar movimentações~~
    public Movement save( Movement createMovement) {
        try {
            Optional<User> user = getCurrentUser();
            if(user.isPresent())
            {
                createMovement.setUser(user.get());
                return (movementRepository.save(createMovement));
            }
            return null;
            //createMovement.setUser(userDetails );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar movimento" + e.getMessage());
        }
    }


//~~método para buscar movimentações pelo tipo~~
    public List<Movement> findByTypeMovement(String typeMovement) {
        try {
            TypeMovement type = TypeMovement.get(typeMovement);
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {
                return (movementRepository.findByTypeMovement(type.toString(),user.get().getId()));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro na consulta de tipo de movimento" + e.getMessage());
        }
    }
    
    
//~~deletar movimento pelo id~~
    public void deleteById(Long idMovement){
        try {
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {

                movementRepository.deleteById(idMovement);
            }
        }catch (Exception e)
        {
            throw new RuntimeException("Movimento não encontrado " + idMovement + ": " + e.getMessage());
        }

    }    
 
  //~~método para pegar o saldo~~
    public UserBalanceResponse getBalance() {
    	try {
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {

              List<Movement> receitas = findByTypeMovement("receita");
                            
              double totalReceita = 0;
              UserBalanceResponse response = new UserBalanceResponse();
              
              if(receitas.isEmpty()) {
            	  response.setReceitas(totalReceita);
              } 
              
              for (Movement movement : receitas) {
				totalReceita += movement.getValueMovement();
			}
              response.setReceitas(totalReceita);          
                          
            
            List<Movement> despesas = findByTypeMovement("despesa");
            
            double totalDespesa = 0;          
            
            if(despesas.isEmpty()) {
          	  response.setDespesas(totalDespesa);
            } 
            
            for (Movement movement : despesas) {
				totalDespesa += movement.getValueMovement();
			}
            response.setDespesas(totalDespesa);
            
                        response.setSaldo(totalReceita- totalDespesa);
            
                        return response;
            } else throw new RuntimeException();
                      
            
        }catch (Exception e)
        {
            throw new RuntimeException("Usuário não encontrado");
        }
    	  	
    }

}
