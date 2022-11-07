package br.com.g6.organizadorfinanceiro.repository;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Repository;

import br.com.g6.organizadorfinanceiro.model.Movement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query(value = "SELECT * FROM Movement u WHERE u.user_id_user = ?1",
      nativeQuery = true)

      List<Movement> findByUserId (Long userId);



//    ~~~Precisa validar: Query para filtrar por despesa ou receita~~~~
//    @Query( value = "SELECT * FROM Movement u WHERE u.type_movement = 1",
//            nativeQuery = true)
//            Movement findByTypeMovement(TypeMovement typeMovement);


}
