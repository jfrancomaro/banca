package com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;
import com.mitocode.javaweb.springbootmybatis.tarjeta.application.TarjetaService;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {

	@Autowired
	private TarjetaService tarjetaService;

	@ModelAttribute("modulo")
	public String modulo() {
		return "tarjetas";
	}

	@GetMapping("/nuevo")
	public String nuevaTarjeta(ModelMap model) {
		model.put("opcionMenu", "tarjeta-nuevo");
		model.put("tarjetaNueva", new Tarjeta());

		return "tarjeta/tarjeta-nueva";
	}

	@PostMapping("/registrar")
	public String registrarTarjeta(
			@Valid
			@ModelAttribute("tarjetaNueva")
			Tarjeta tarjeta, 
			BindingResult bindingResult, HttpSession session,
			ModelMap model) {
		String resultPage = "";

		if (bindingResult.hasErrors()) {
			model.put("error", "Completa los campos obligatorios");
			resultPage = "tarjeta/tarjeta-nueva";
		} else {
			Cliente cliente = (Cliente) session.getAttribute("clienteLogin");
			tarjeta.setCliente(cliente);

			int resultado = tarjetaService.registrarTarjeta(tarjeta);

			if (resultado > 0) {
				resultPage = "redirect:/cliente/resumen";
			} else {
				model.put("error", "No se registro la tarjeta");
				resultPage = "tarjeta/tarjeta-nueva";
			}
		}

		return resultPage;
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminarTarjeta(@PathVariable(value = "id") Integer idTarjeta) {
		Integer row = tarjetaService.eliminarTarjeta(idTarjeta);
		ResponseEntity<EliminarResponse> response = null;

		if (row > 0) {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"),
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"),
					HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@GetMapping("/editar/{id}")
	public String editarTarjeta(@PathVariable(value = "id") Integer idTarjeta, ModelMap model) {
		Tarjeta tarjeta = tarjetaService.obtenerTarjeta(idTarjeta);
		model.put("tarjetaEditar", tarjeta);

		return "tarjeta/tarjeta-editar";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizarTarjeta(
			@Valid
			@ModelAttribute("tarjetaEditar")
			Tarjeta tarjeta, 
			BindingResult bindingResult, ModelMap model) {
		String resultPage = "";

		if (bindingResult.hasErrors()) {
			resultPage = "tarjeta/tarjeta-editar";
		} else {
			int resultado = tarjetaService.actualizarTarjeta(tarjeta);

			if (resultado > 0) {
				resultPage = "redirect:/cliente/resumen";
			} else {
				model.put("error", "No se registro la tarjeta");
				resultPage = "tarjeta/tarjeta-nueva";
			}
		}

		return resultPage;
	}

	@Data
	@AllArgsConstructor
	class EliminarResponse {
		private String message;
	}

}
