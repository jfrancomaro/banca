package com.mitocode.javaweb.springbootmybatis.cliente.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Transactional
@ContextConfiguration
@SpringBootTest
public class ClienteServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(ClienteServiceTest.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Test
	@Disabled
	public void prueba() {
		int a = 5;
		int b = 3;
		assertTrue((a+b) == 8, "Error en la suma");
	}
	
	@Test
	@DisplayName("")
	public void listarClientes(){
		Optional<Collection<Cliente>> lista = Optional.of(clienteService.obtenerClientes()) ;
		
		lista.ifPresent(cliente -> LOG.info(cliente.toString()));
		
	}	
	
	@Test
	@Commit
	public void registrarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombres("Vicente Martinez");
		cliente.setDocumento("70121122");
		cliente.setClave(passwordEncoder.encode("123456"));
		
		Integer filas = clienteService.registrarCliente(cliente);
		
		assertTrue(filas > 0, "No se registro el cliente");
		
	}
	
	@Test
	public void datosCliente() {
		
		Cliente cliente = clienteService.obtenerCliente(1);
		
		LOG.info("obtener cliente(): " + cliente.toString());
		
		assertNotNull(cliente, "No se pudo encontrar el cliente con ID = 1");
		
	}
	
	
}
