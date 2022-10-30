package br.com.g6.organizadorfinanceiro.model;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Movement")
public class Movement {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMovement;

	public TypeMovement getTypeMovement() {
		return typeMovement;
	}

	public void setTypeMovement(TypeMovement typeMovement) {
		this.typeMovement = typeMovement;
	}

	@NotNull
	private TypeMovement typeMovement;
	
	@NotNull
	private double valueMovement;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMovement;
	
	@NotNull
	@Size(min = 1, max = 500)
	private String descriptionMovement;
	
	private int seqParcel;

	@ManyToOne
	private User user;

	@OneToOne
	private OutstandingPayments outstandingPayments;

	public Long getIdMovement() {
		return idMovement;
	}

	public void setIdMovement(Long idMovement) {
		this.idMovement = idMovement;
	}



	public double getValueMovement() {
		return valueMovement;
	}

	public void setValueMovement(double valueMovement) {
		this.valueMovement = valueMovement;
	}

	public Date getDateMovement() {
		return dateMovement;
	}

	public void setDateMovement(Date dateMovement) {
		this.dateMovement = dateMovement;
	}

	public String getDescriptionMovement() {
		return descriptionMovement;
	}

	public void setDescriptionMovement(String descriptionMovement) {
		this.descriptionMovement = descriptionMovement;
	}

	public int getSeqParcel() {
		return seqParcel;
	}

	public void setSeqParcel(int seqParcel) {
		this.seqParcel = seqParcel;
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