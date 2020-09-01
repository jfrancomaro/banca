package com.mitocode.javaweb.springbootmybatis.tarjeta.application;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.TarjetaEstado;
import com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.TarjetaMapper;

@Service
public class TarjetaServiceImpl implements TarjetaService {

	@Autowired
	private TarjetaMapper tarjetaMapper;

	@Override
	public Tarjeta obtenerTarjeta(Integer id) {
		return tarjetaMapper.findById(id);
	}

	@Override
	public Integer registrarTarjeta(Tarjeta tarjeta) {

		Optional<Collection<Tarjeta>> tarjetasActivas = Optional.ofNullable(tarjetaMapper.findByIdClienteAndEstado(tarjeta.getCliente().getId(),
				TarjetaEstado.ACTIVA.name()));

		tarjetasActivas.ifPresent( lista -> lista.forEach(card -> inactivarTarjeta(card)));
		
		tarjeta.setEstado(TarjetaEstado.ACTIVA);

		return tarjetaMapper.insert(tarjeta);
	}

	@Override
	public Integer inactivarTarjeta(Tarjeta tarjeta) {
		return tarjetaMapper.updateByEstado(tarjeta.getId(), TarjetaEstado.INACTIVA.name());
	}

	@Override
	public Integer eliminarTarjeta(Integer id) {
		return tarjetaMapper.delete(id);
		
	}

	@Override
	public Integer actualizarTarjeta(Tarjeta tarjeta) {
		return tarjetaMapper.update(tarjeta);
	}

}
