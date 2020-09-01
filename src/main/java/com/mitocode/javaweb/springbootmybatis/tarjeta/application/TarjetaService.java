package com.mitocode.javaweb.springbootmybatis.tarjeta.application;

import java.util.Collection;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.TarjetaEstado;

public interface TarjetaService {

	public Tarjeta obtenerTarjeta(Integer id);

	public Integer registrarTarjeta(Tarjeta tarjeta);
	
	public Integer inactivarTarjeta(Tarjeta tarjeta);

	public Integer eliminarTarjeta(Integer id);

	public Integer actualizarTarjeta(Tarjeta tarjeta);
	
	public Collection<Tarjeta> obtenerTarjetasDeCliente(Integer idCliente, TarjetaEstado estado);

	public Collection<Tarjeta> obtenerTarjetas();

	Tarjeta registrarTarjeta2(Tarjeta tarjeta);
	
}
