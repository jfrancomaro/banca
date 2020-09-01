package com.mitocode.javaweb.springbootmybatis.cliente.application;

import java.util.Collection;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

public interface ClienteService {

	public Collection<Cliente> obtenerClientes();
	
	public Integer registrarCliente(Cliente cliente);
	
	public Cliente obtenerCliente(Integer idCliente);
	
	public Cliente obtenerCliente(String documento);
	
	public Cliente validarClave(String documento, String clave);
	
}
