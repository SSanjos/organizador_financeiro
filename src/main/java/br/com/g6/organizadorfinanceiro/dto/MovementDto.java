package br.com.g6.organizadorfinanceiro.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovementDto {
	
	private String typeMovement;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date periodoDe;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date periodoAte;
	
	private String description;
	private Long idUsuario;
	private Double valueMovementIni;
	private Double valueMovementEnd;
	private Boolean wasPaid;
	
	public String getTypeMovement() {
		return typeMovement;
	}
	public void setTypeMovement(String typeMovement) {
		this.typeMovement = typeMovement;
	}
	public Date getPeriodoDe() {
		return periodoDe;
	}
	public void setPeriodoDe(Date periodoDe) {
		this.periodoDe = periodoDe;
	}
	public Date getPeriodoAte() {
		return periodoAte;
	}
	public void setPeriodoAte(Date periodoAte) {
		this.periodoAte = periodoAte;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Double getValueMovementIni() {
		return valueMovementIni;
	}
	public void setValueMovementIni(Double valueMovementIni) {
		this.valueMovementIni = valueMovementIni;
	}
	public Double getValueMovementEnd() {
		return valueMovementEnd;
	}
	public void setValueMovementEnd(Double valueMovementEnd) {
		this.valueMovementEnd = valueMovementEnd;
	}
	public Boolean getWasPaid() {
		return wasPaid;
	}
	public void setWasPaid(Boolean wasPaid) {
		this.wasPaid = wasPaid;
	}
}