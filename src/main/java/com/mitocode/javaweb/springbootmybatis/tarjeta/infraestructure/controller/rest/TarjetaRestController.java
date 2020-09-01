package com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.javaweb.springbootmybatis.tarjeta.application.TarjetaService;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaRestController {

	@Autowired
	private TarjetaService tarjetaService;
	
	@GetMapping
	public Collection<Tarjeta> listarTarjetas() {
		return tarjetaService.obtenerTarjetas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarjeta> listarTarjeta(@PathVariable Integer id) {
		Tarjeta tarjeta = tarjetaService.obtenerTarjeta(id);
		
		if(tarjeta == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(tarjeta);
		}
	}
	
	@PostMapping
	public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjeta) {
		return ResponseEntity.ok(tarjetaService.registrarTarjeta2(tarjeta));
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> actualizarTarjeta(@PathVariable("id") Integer id, @RequestBody Tarjeta tarjeta) {
		tarjeta.setId(id);
		return ResponseEntity.ok(tarjetaService.actualizarTarjeta(tarjeta));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		if(tarjetaService.obtenerTarjeta(id) != null) {
			tarjetaService.eliminarTarjeta(id);
			return ResponseEntity.ok("Se eliminó");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
