package com.mitocode.javaweb.springbootmybatis.tarjeta.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

@Transactional
@SpringBootTest
public class TarjetaServiceTest {

	
	private static final Logger LOG = LoggerFactory.getLogger(TarjetaServiceTest.class);
	
	@Autowired
	private TarjetaService tarjetaService;
		
	@Test
	public void consultarTarjeta() {
	
		Tarjeta tarjeta = tarjetaService.obtenerTarjeta(28);
		
		LOG.info("consultarTarjeta(): " + tarjeta.toString());
		
		assertNotNull(tarjeta, "No se pudo obtener la tarjeta con ID = 28");
		
	}
	
}
