package com.mitocode.javaweb.springbootmybatis.tarjeta.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjeta")
public class Tarjeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cliente cliente;
	
	@NotBlank(message = "Ingresa numero de Tarjeta")
	@Column(name = "numero_tarjeta", unique = true)
	@JsonProperty(value = "numero-tarjeta")
	private String numeroTarjeta;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Ingresa la fecha de vencimiento")
	@Future(message = "La fecha de vencimiento debe ser mayor a la fecha actual")
	@Column(name = "fecha_vencimiento")
	@JsonProperty(value = "fecha-vencimiento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaVencimiento;
	
	@Enumerated(EnumType.STRING)
	private TarjetaEstado estado;
	
}
