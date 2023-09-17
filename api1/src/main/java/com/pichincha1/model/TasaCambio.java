package com.pichincha1.model;


public class TasaCambio {

	private String monedaO;
	private String monedaD;
	private double conversion;
	private double monto;
	private double calculado;
	public String getMonedaO() {
		return monedaO;
	}
	public void setMonedaO(String monedaO) {
		this.monedaO = monedaO;
	}
	public String getMonedaD() {
		return monedaD;
	}
	public void setMonedaD(String monedaD) {
		this.monedaD = monedaD;
	}
	public double getConversion() {
		return conversion;
	}
	public void setConversion(double conversion) {
		this.conversion = conversion;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getCalculado() {
		return calculado;
	}
	public void setCalculado(double calculado) {
		this.calculado = calculado;
	}
	public TasaCambio(String monedaO, String monedaD, double conversion, double monto, double calculado) {
		super();
		this.monedaO = monedaO;
		this.monedaD = monedaD;
		this.conversion = conversion;
		this.monto = monto;
		this.calculado = calculado;
	}
	public TasaCambio() {
		super();
	}

	
	
	
	
}