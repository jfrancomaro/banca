package com.mitocode.javaweb.springbootmybatis.cliente.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;
import com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.repository.ClienteMapper;
import com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteMapper clienteMapper;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(ClienteMapper clienteMapper) {
		this.clienteMapper = clienteMapper;
	}
	
	@Override
	public Collection<Cliente> obtenerClientes() {
//		return clienteMapper.findAll();
		return (Collection<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Integer registrarCliente(Cliente cliente) {
//		return clienteMapper.insert(cliente);
		return clienteRepository.save(cliente) != null ? 1 : 0;
	}

	@Override
	public Cliente obtenerCliente(Integer id) {
//		return clienteMapper.findById(id);
		return clienteRepository.findById(id).orElse(null);
	}
	
	@Override
	public Cliente obtenerCliente(String documento) {
//		return clienteMapper.findByDocumento(documento);
		return clienteRepository.findByDocumento(documento);
	}

	@Override
	public Cliente validarClave(String documento, String clave) {
//		return clienteMapper.findByDocumentoAndClave(documento, clave);
		return clienteRepository.findByDocumentoAndClave(documento, clave);
	}

}
