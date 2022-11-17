package br.com.g6.organizadorfinanceiro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import br.com.g6.organizadorfinanceiro.security.servicesUser.UserDetailsServiceImpl;
import br.com.g6.organizadorfinanceiro.security.servicesUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import br.com.g6.organizadorfinanceiro.dto.MovementDto;
import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.models.Movement;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



public class FilterMovement implements IFilterMovement {





	@Autowired
	private UserService userService;


    public Specification<Movement> toSpecification(MovementDto filter) {
    	return (root, query, criteriaBuilder) -> {


			Optional<User> userLogged = userService.getCurrentUser();

			if(!userLogged.isPresent())
			{
				throw new RuntimeException("Usuario nao autenticado");
			}

    	    List<Predicate> predicates = new ArrayList<Predicate>();
    	    
    	    TypeMovement tm = TypeMovement.get(filter.getTypeMovement());
			predicates.add(criteriaBuilder.equal(root.get("user"), userLogged.get().getId()));
    	    if(tm != null) {
    	    	predicates.add(criteriaBuilder.equal(root.get("typeMovement"), tm));
    	    }

    	    if(filter.getPeriodoDe() != null && filter.getPeriodoAte() == null) {
    	    	predicates.add(criteriaBuilder.greaterThan(root.get("dueDate"), filter.getPeriodoDe()));
    	    } else if(filter.getPeriodoAte() != null && filter.getPeriodoDe() == null) {
    	    	predicates.add(criteriaBuilder.lessThan(root.get("dueDate"), filter.getPeriodoAte()));
    	    } else if(filter.getPeriodoDe() != null && filter.getPeriodoAte() != null) {
    	    	predicates.add(criteriaBuilder.between(root.get("dueDate"), filter.getPeriodoDe(), filter.getPeriodoAte()));
    	    }
    	    if(filter.getValueMovementIni() != null && filter.getValueMovementEnd() == null) {
    	    	predicates.add(criteriaBuilder.greaterThan(root.get("valueMovement"), filter.getValueMovementIni()));
    	    } else if(filter.getValueMovementEnd() != null && filter.getValueMovementIni() == null) {
    	    	predicates.add(criteriaBuilder.lessThan(root.get("valueMovement"), filter.getValueMovementEnd()));
    	    } else if(filter.getValueMovementIni() != null && filter.getValueMovementEnd() != null) {
    	    	predicates.add(criteriaBuilder.between(root.get("valueMovement"), filter.getValueMovementIni(), filter.getValueMovementEnd()));
    	    }
    	    if(filter.getDescription() !=null) {
    	    	predicates.add(criteriaBuilder.like(root.get("descriptionMovement"), "%" + filter.getDescription() + "%"));
    	    }
    	    if(filter.getWasPaid() != null) {
    	    	predicates.add(criteriaBuilder.equal(root.get("wasPaid"), filter.getWasPaid()));
    	    }
    		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    	};
    	
    }

}