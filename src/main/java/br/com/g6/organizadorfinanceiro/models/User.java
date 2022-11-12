package br.com.g6.organizadorfinanceiro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @NotBlank
  @Size(max = 20)
  @Getter
  @Setter
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @Getter
  @Setter
  private String email;

  @NotBlank
  @Size(max = 120)
  @Getter
  @Setter
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Getter
  @Setter
  private Set<Role> roles = new HashSet<>();


  @Getter
  @Setter
  @JsonIgnoreProperties("user")
  @OneToMany ( mappedBy = "user")// relação user e movement - foreign key, mapeada dentro do atributo user
  private List<Movement> movementList = new ArrayList<>();
  //a lista tem que ser instanciada para cada novo usuário, para não dar erro "NullPointsException"

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }


  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
