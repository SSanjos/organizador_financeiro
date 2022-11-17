package br.com.g6.organizadorfinanceiro.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.g6.organizadorfinanceiro.dto.MovementDto;
import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.models.Movement;

public class FilterMovement {
	
    public Specification<Movement> toSpecification(MovementDto filter) {
    	return (root, query, criteriaBuilder) -> {
    	    List<Predicate> predicates = new ArrayList<Predicate>();
    	    
    	    TypeMovement tm = TypeMovement.get(filter.getTypeMovement());
    	    if(tm != null) {
    	    	predicates.add(criteriaBuilder.equal(root.get("typeMovement"), tm));
    	    }
    	    if(filter.getIdUsuario() != null) {
    	    	root.fetch("user");
    	    	predicates.add(criteriaBuilder.equal(root.get("user").get("id"), filter.getIdUsuario()));
    	    }
    	    if(filter.getIdMovement() != null) {
    	    	predicates.add(criteriaBuilder.equal(root.get("idMovement"), filter.getIdMovement()));
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