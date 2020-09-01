package com.mitocode.javaweb.springbootmybatis.tarjeta.application;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.TarjetaEstado;
import com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.repository.TarjetaMapper;
import com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.repository.TarjetaRepository;

@Service
public class TarjetaServiceImpl implements TarjetaService {

	@Autowired
	private TarjetaMapper tarjetaMapper;
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@Override
	public Tarjeta obtenerTarjeta(Integer id) {
//		return tarjetaMapper.findById(id);
		return tarjetaRepository.findById(id).orElse(null);
	}

	@Override
	public Integer registrarTarjeta(Tarjeta tarjeta) {
		
		Optional<Collection<Tarjeta>> tarjetasActivas = Optional.ofNullable(obtenerTarjetasDeCliente(tarjeta.getCliente().getId(),
				TarjetaEstado.ACTIVA));
		
		tarjetasActivas.ifPresent(lista -> lista.forEach(card -> inactivarTarjeta(card)));
		
		tarjeta.setEstado(TarjetaEstado.ACTIVA);
		
//		return tarjetaMapper.insert(tarjeta);
		return tarjetaRepository.save(tarjeta) != null ? 1 : 0;
	}
	
	@Override
	public Tarjeta registrarTarjeta2(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	}
	

	@Override
	public Collection<Tarjeta> obtenerTarjetasDeCliente(Integer idCliente, TarjetaEstado estado) {
//		return tarjetaMapper.findByIdClienteAndEstado(idCliente, estado.name());
		return tarjetaRepository.findByIdAndEstado(idCliente, estado);
	}
	
	@Override
	public Integer inactivarTarjeta(Tarjeta tarjeta) {
//		return tarjetaMapper.updateByEstado(tarjeta.getId(), TarjetaEstado.INACTIVA.name());
		tarjeta.setEstado(TarjetaEstado.INACTIVA);
		return tarjetaRepository.save(tarjeta) != null ? 1 : 0;
	}

	@Override
	public Integer eliminarTarjeta(Integer id) {
//		return tarjetaMapper.delete(id);
		tarjetaRepository.deleteById(id);
		return 1; 
	}

	@Override
	public Integer actualizarTarjeta(Tarjeta tarjeta) {
//		return tarjetaMapper.update(tarjeta);
		return tarjetaRepository.save(tarjeta) != null ? 1 : 0;
	}

	@Override
	public Collection<Tarjeta> obtenerTarjetas() {
		return (Collection<Tarjeta>) tarjetaRepository.findAll();
	}

}
