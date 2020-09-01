package com.mitocode.javaweb.springbootmybatis.tarjeta.domain;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {

	
	private Integer id;
	private Cliente cliente;
	@NotBlank(message = "Ingresa Numero de Tarjeta")
	private String numeroTarjeta;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Ingresa la Fecha de Vencimiento")
	@Future(message = "La fecha de Vencimiento debe ser mayor que la actual")
	private LocalDate fechaVencimiento;
	private TarjetaEstado estado;
	
}
