package br.com.g6.organizadorfinanceiro.models;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "Movement")
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter //??
//	@Column(name = "idMovement", nullable = false)//permite alterar nome da coluna
	private Long idMovement;

//	@NotNull
//	@Getter
//	@Setter
//	@Enumerated(EnumType.STRING)
//	private TypeMovement typeMovement;

	
	@NotNull
	@Getter
	@Setter
	private double valueMovement;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date dateMovement;
	
	@NotNull
	@Getter
	@Setter
	@Size(min = 1, max = 500)
	private String descriptionMovement;


	@Getter
	@Setter
	private int seqParcel;
	//retirar??

	@ManyToOne
	@JsonIgnoreProperties ("movementList")
	@JoinColumn ( name = "id")
	@Getter
	@Setter
	private User user;


	@NotNull
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private TypeMovement typeMovement;


}

