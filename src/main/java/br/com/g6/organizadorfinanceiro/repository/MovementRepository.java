package br.com.g6.organizadorfinanceiro.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.User;
@Repository

public interface MovementRepository extends JpaRepository<Movement, Long>, JpaSpecificationExecutor<Movement> {

    Optional<Movement> findById(Long id);

    @Override
    void deleteById(Long aLong);

    List<Movement> findByUser(User user);
}