package br.com.g6.organizadorfinanceiro.repository;


import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface MovementRepository extends JpaRepository<Movement, Long> {


//   List<Movement> findAll (Movement movement);
    Optional<Movement> findById (Long id);

    @Override
    void deleteById(Long aLong);


    List<Movement> findByUser (User user);

    @Query(value = "SELECT * FROM Movement u WHERE  u.type_movement = ?1 and u.id = ?2", nativeQuery = true)
    List<Movement> findByTypeMovement(String typeMovement, Long userId);





}
