package com.mitocode.javaweb.springbootmybatis.tarjeta.application;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

public interface TarjetaService {

	public Tarjeta obtenerTarjeta(Integer id);

	public Integer inactivarTarjeta(Tarjeta tarjeta);
	
	public Integer registrarTarjeta(Tarjeta tarjeta);

	public Integer eliminarTarjeta(Integer idTarjeta);

	public Integer actualizarTarjeta(Tarjeta tarjeta);
	
}
