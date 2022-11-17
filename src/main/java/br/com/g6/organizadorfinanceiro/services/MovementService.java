package br.com.g6.organizadorfinanceiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.g6.organizadorfinanceiro.dto.MovementDto;
import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.models.UserBalanceResponse;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
import br.com.g6.organizadorfinanceiro.security.servicesUser.UserDetailsServiceImpl;


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

    //~~método para os filtros~~
    public List<Movement> findByFilter(MovementDto filter) {
        try {
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {
            	FilterMovement filterMovement = new FilterMovement();
            	filter.setIdUsuario(user.get().getId());
            	return movementRepository.findAll(filterMovement.toSpecification(filter));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro na consulta" + e.getMessage());
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

            	MovementDto filter = new MovementDto();
            	filter.setTypeMovement("receita");
            	List<Movement> receitas = findByFilter(filter);
                            
            	double totalReceita = 0;
            	UserBalanceResponse response = new UserBalanceResponse();
              
            	if(receitas.isEmpty()) {
            	  response.setReceitas(totalReceita);
            	} 
              
            	for (Movement movement : receitas) {
            		totalReceita += movement.getValueMovement();
            	}
	            response.setReceitas(totalReceita);          
	            
	            filter.setTypeMovement("despesa");
	            List<Movement> despesas = findByFilter(filter);
	            
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