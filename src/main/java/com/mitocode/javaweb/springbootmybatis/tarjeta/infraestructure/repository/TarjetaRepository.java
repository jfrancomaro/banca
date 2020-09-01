package com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.TarjetaEstado;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer> {

	Collection<Tarjeta> findByIdAndEstado(Integer idCliente, TarjetaEstado estado);

}
