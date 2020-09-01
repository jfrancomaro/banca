package com.mitocode.javaweb.springbootmybatis.cliente.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;
import com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.ClienteMapper;

@Service
public class ClienteServiceImpl implements ClienteService{

	private ClienteMapper clienteMapper;

	@Autowired
	public ClienteServiceImpl(ClienteMapper clienteMapper) {
		this.clienteMapper = clienteMapper;
	}
		
	@Override
	public Collection<Cliente> obtenerClientes() {
		return clienteMapper.findAll();
	}

	@Override
	public Integer registrarCliente(Cliente cliente) {
		return clienteMapper.insert(cliente);
	}

	@Override
	public Cliente obtenerCliente(Integer id) {
		return clienteMapper.findById(id);
	}

	@Override
	public Cliente validarClave(String documento, String clave) {
		return clienteMapper.findByDocumentoAndClave(documento, clave);
	}

	@Override
	public Cliente obtenerCliente(String documento) {
		return clienteMapper.findByDocumento(documento);
	}
	
}
