package br.com.g6.organizadorfinanceiro.models;

public class UserBalanceResponse {
	
	private Double receitas;
	private Double despesas;
	private Double saldo;
		
	
	public UserBalanceResponse() {
		
	}



	public Double getReceitas() {
		return receitas;
	}



	public void setReceitas(Double receitas) {
		this.receitas = receitas;
	}



	public Double getDespesas() {
		return despesas;
	}



	public void setDespesas(Double despesas) {
		this.despesas = despesas;
	}



	public Double getSaldo() {
		return saldo;
	}



	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	

}
