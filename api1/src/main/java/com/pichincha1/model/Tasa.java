package com.pichincha1.model;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasa")
@EntityListeners(AuditingEntityListener.class)
public class Tasa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull 
	private String moneda1;
	
	@NonNull 
	private String moneda2;
	
	//@Column(name="tipo_cambio") 
	@NonNull 
	private double tipoCambio;
	
	@CreatedBy
	protected String createdBy;

	@CreatedDate
	protected String createdDate;

	@LastModifiedBy
	protected String lastModifiedBy;

	@LastModifiedDate
	protected String lastModifiedDate;
	
/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoneda1() {
		return moneda1;
	}

	public void setMoneda1(String moneda1) {
		this.moneda1 = moneda1;
	}

	public String getMoneda2() {
		return moneda2;
	}

	public void setMoneda2(String moneda2) {
		this.moneda2 = moneda2;
	}

	public double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
*/
	public Tasa(String moneda1, String moneda2, double tipoCambio) {
		super();
		this.moneda1 = moneda1;
		this.moneda2 = moneda2;
		this.tipoCambio = tipoCambio;
	}
	
	
	
}
