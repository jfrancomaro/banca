package com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Cliente findByDocumento(String documento);

	Cliente findByDocumentoAndClave(String documento, String clave);
	
	@Modifying
	@Query("SELECT c FROM Cliente c WHERE c.documento  = ?1 AND c.clave = ?2")
	Cliente findByDocumentoAndClaveAndCampoAdicional(String documento, String clave);

}
