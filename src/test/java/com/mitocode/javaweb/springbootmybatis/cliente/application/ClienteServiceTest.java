package com.mitocode.javaweb.springbootmybatis.cliente.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@SpringBootTest
@ContextConfiguration
@Transactional
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
		int b = 8;
		
		assertTrue(a + 5 == b , "Error en la suma");
	}
	
	@Test
	@DisplayName("Evaluar la lista de clientes obtenidas de la BD")
	public void listarClientes() {
		Optional<Collection<Cliente>> lista = Optional.of(clienteService.obtenerClientes());
		
		lista.ifPresent(cliente -> LOG.debug(cliente.toString()));
		
	}
	
	@Test
//	@Commit
	public void registrarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombres("Jorge Nicolas");
		cliente.setDocumento("88888889");
		cliente.setFecha_nacimiento(LocalDate.of(1995, 05, 18));
		cliente.setClave(passwordEncoder.encode("123456"));
		
		int filas = clienteService.registrarCliente(cliente);
		
		assertTrue(filas > 0, "No se registro el cliente");
	}
	
	@Test
	public void obtenerCliente() {
		Cliente cliente = clienteService.obtenerCliente(8);
		
		LOG.info("obtenerCliente():"  + cliente.toString());
		
		assertNotNull(cliente, "No se pudo obtener cliente ID=8");
	}
	
}
