package com.rs.redsinergia.model;

import java.util.Date;

public class ReporteTransaccionesPorUsuarioHome {
	private int cuentaOrigen;
	private Date fecha;
	private double monto;
	
	public ReporteTransaccionesPorUsuarioHome(int cuentaOrigen, Date fecha, double monto ) {
		this.cuentaOrigen = cuentaOrigen;
		this.fecha = fecha;
		this.monto = monto;
	}
	
	public int getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(int cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
}
