package br.com.g6.organizadorfinanceiro.repository;

import br.com.g6.organizadorfinanceiro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Override
  void deleteById(Long aLong);

//  public Optional<User> findByUserEmail(String email);
//  @Query(
//          value = "SELECT * FROM User u WHERE u.user_email = ?1 AND u.user_password = ?2",
//          nativeQuery = true)
//  User findByUserEmailAndUserPassword(String email, String password);
//}
}