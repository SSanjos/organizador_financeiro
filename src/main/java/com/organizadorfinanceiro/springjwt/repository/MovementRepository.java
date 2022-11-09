package com.organizadorfinanceiro.springjwt.repository;


import com.organizadorfinanceiro.springjwt.models.Movement;
import com.organizadorfinanceiro.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface MovementRepository extends JpaRepository<Movement, Long> {


//   List<Movement> findAll (Movement movement);
   Optional<Movement> findById (Long id);
    List<Movement> findByUser (User user);

    @Query(value = "SELECT * FROM Movement u WHERE  u.type_movement = ?1 and u.id = ?2", nativeQuery = true)
    List<Movement> findByTypeMovement(String typeMovement, Long userId);


    List<Movement> findAllByDescriptionMovementContainingIgnoreCase(String descriptionMovement);




}



//
//    ver como implementer
//    @Query( value = "SELECT * FROM Movement u WHERE u.type_movement = 1",
//            nativeQuery = true)
//            Movement findByTypeMovementDespesa(TypeMovement typeMovement);
//


//    @Query( value = "SELECT * FROM Movement u WHERE u.type_movement = 0",
//            nativeQuery = true)
//            Movement findByTypeMovement(TypeMovement typeMovement);



