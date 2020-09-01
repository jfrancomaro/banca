package com.mitocode.javaweb.springbootmybatis.cliente.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;
import com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.ClienteMapper;

@SpringBootTest
public class ClienteServiceTestMock {

	private ClienteService clienteService;
	
	@Mock
	private ClienteMapper clienteMapper;
		
	// metodo que se ejecuta al instanciar la clase  test
	@BeforeAll
	public void init() {
		
	}
	
	// metodo que se ejecuta antes de cada prueba
	@BeforeEach
	public void setUp() {
		
		clienteService = new ClienteServiceImpl(clienteMapper);
		
		when(clienteMapper.findAll()).thenReturn(
				Arrays.asList(new Cliente(1, "Kima", "11223344",LocalDate.of(2004, 04, 07),"123456"),
							  new Cliente(2, "Cristina", "33445566", LocalDate.of(1978, 8, 19), "123456")
				));
		
	}
	
	@Test
	@DisplayName("Validad que la lista sean los documentos 11223344, 33445566")
	public void listarClienteMock() {
		
		Collection<Cliente> list = clienteService.obtenerClientes();
		
		assertEquals(Arrays.asList("11223344","33445566"), 
				list.stream().map(cliente -> cliente.getDocumento()).collect(Collectors.toList()));
	}
	
	// metodo que se ejcuta despues de cada prueba
	@AfterEach
	public void tearDown() {

	}

	//se ejecuta antes de destruir la clase de prueba
	@AfterAll
	public void done() {
		
		
	}
	
	
	
}
