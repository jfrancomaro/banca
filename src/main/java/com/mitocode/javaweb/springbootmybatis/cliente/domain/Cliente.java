package com.mitocode.javaweb.springbootmybatis.cliente.domain;

import java.time.LocalDate;
import java.util.Collection;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Cliente {

	@NonNull
	private Integer id;
	@NonNull
	private String nombres;
	@NonNull
	private String documento;
	@NonNull
	private LocalDate fecha_nacimiento;
	@NonNull
	private String clave;
	
	private String rol;
	
	
	private Collection<Tarjeta> tarjetas;
}
