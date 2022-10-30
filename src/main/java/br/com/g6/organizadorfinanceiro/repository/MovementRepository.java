package br.com.g6.organizadorfinanceiro.repository;

import br.com.g6.organizadorfinanceiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.g6.organizadorfinanceiro.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {


}
