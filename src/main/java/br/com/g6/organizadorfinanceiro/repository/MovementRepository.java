package br.com.g6.organizadorfinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query(value = "SELECT * FROM Movement u WHERE u.user_id_user = ?1", nativeQuery = true)
    List<Movement> findByUserId (Long userId);

    List<Movement> findByTypeMovement(TypeMovement typeMovement);

}
