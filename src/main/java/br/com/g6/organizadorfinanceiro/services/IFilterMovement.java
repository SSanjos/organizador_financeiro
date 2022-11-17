package br.com.g6.organizadorfinanceiro.services;

import br.com.g6.organizadorfinanceiro.dto.MovementDto;
import br.com.g6.organizadorfinanceiro.models.Movement;
import org.springframework.data.jpa.domain.Specification;

public interface IFilterMovement {
    default Specification<Movement> toSpecification(MovementDto filter) {
        return null;
    }
}
