package br.com.g6.organizadorfinanceiro.model;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Movement")
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter //??
//	@Column(name = "idMovement", nullable = false)//permite alterar nome da coluna
	private Long idMovement;

	@NotNull
	@Getter
	@Setter
	private TypeMovement typeMovement;
	
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
	@Size(min = 1, max = 500)
	private String descriptionMovement;



	private int seqParcel;
	//retirar??

	@OneToOne
//	@NotNull
	private User user;
	//not null - QUAL DADO? SERIA O USER ID?
	//um movimento tem um usuario, está dentro da classe

	@OneToOne
	private OutstandingPayments outstandingPayments;


	public String getDescriptionMovement() {
		return descriptionMovement;
	}

	public void setDescriptionMovement(String descriptionMovement) {
		this.descriptionMovement = descriptionMovement;
	}

	public int getSeqParcel() {
		return seqParcel;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OutstandingPayments getOutstandingPayments() {
		return outstandingPayments;
	}

	public void setOutstandingPayments(OutstandingPayments outstandingPayments) {
		this.outstandingPayments = outstandingPayments;
	}

}