package com.mitocode.javaweb.springbootmybatis.cliente.domain;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "tarjetas")
@Entity
@Table(name = "cliente")
public class Cliente {

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private Integer id;
	
	@NonNull
	@Column
	private String nombres;
	
	@NonNull
	@Column
	private String documento;
	
	@NonNull
	@Column(name = "fecha_nacimiento")
	private LocalDate fecha_nacimiento;
	
	@NonNull
	@Column
	private String clave;

	@Column
	private String rol;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Collection<Tarjeta> tarjetas;
	
	@Transient
	private String camposAuxiliar;
	
}