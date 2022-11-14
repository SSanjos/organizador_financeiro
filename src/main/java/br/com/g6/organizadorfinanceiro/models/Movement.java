package br.com.g6.organizadorfinanceiro.models;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "Movement")
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

//	@Column(name = "idMovement", nullable = false)//permite alterar nome da coluna
	private Long idMovement;

	@NotNull

	private double valueMovement;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)

	private Date dueDate;
	//~~alteração do nome para data do vencimento~~
	
	@NotNull

	@Size(min = 1, max = 500)
	private String descriptionMovement;



	private int seqParcel;
	//~~ver como implementar~~

	@ManyToOne
	@JsonIgnoreProperties ("movementList")
	@JoinColumn ( name = "id")

	private User user;


	@NotNull

	@Enumerated(EnumType.STRING)
	private TypeMovement typeMovement;

	@NotNull

	private Boolean wasPaid;

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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public TypeMovement getTypeMovement() {
		return typeMovement;
	}

	public void setTypeMovement(TypeMovement typeMovement) {
		this.typeMovement = typeMovement;
	}

	public Boolean getWasPaid() {
		return wasPaid;
	}

	public void setWasPaid(Boolean wasPaid) {
		this.wasPaid = wasPaid;
	}
	//default: false




}

