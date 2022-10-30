package br.com.g6.organizadorfinanceiro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OutstandingPayments")
public class OutstandingPayments {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOutstandingPayments;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	public Long getIdOutstandingPayments() {
		return idOutstandingPayments;
	}

	public void setIdOutstandingPayments(Long idOutstandingPayments) {
		this.idOutstandingPayments = idOutstandingPayments;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
