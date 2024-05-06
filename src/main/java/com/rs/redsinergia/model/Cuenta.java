package com.rs.redsinergia.model;

public class Cuenta {
	private int idUsuario ;
    private int numeroCuentaOrigen;
    private int saldo;
    
	
	public Cuenta(int idUsuario, int numeroCuentaOrigen, int saldo) {
		super();
		this.idUsuario = idUsuario;
		this.numeroCuentaOrigen = numeroCuentaOrigen;
		this.saldo = saldo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}
	public void setNumeroCuentaOrigen(int numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
    
    
}
