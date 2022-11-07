package br.com.g6.organizadorfinanceiro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
//	@Column(name = "idUser", nullable = false)
	private Long idUser;

	@NotNull
	@Size(min = 1, max = 100)
	@Getter
	@Setter
	private String userName;

	@NotNull
	@Size(min = 1, max = 45)
	@Getter
	@Setter
	private String userLastName;

	@NotNull
	@Size(min = 1, max = 45)
	@Getter
	@Setter
	private String userEmail;

	@NotNull
	@Size(min = 1, max = 100)
	@Getter
	@Setter
	private String userPassword;


    //não expor como get and set


}


