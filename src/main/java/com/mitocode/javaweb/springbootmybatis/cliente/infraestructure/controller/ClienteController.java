package com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitocode.javaweb.springbootmybatis.cliente.application.ClienteService;
import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService; 
	
	@ModelAttribute("modulo")
	public String modulo() {
		return "clientes";
	}
	
	@GetMapping("/resumen")
	public String resumenCliente(HttpSession session, ModelMap model) {
		Cliente cliente = (Cliente) session.getAttribute("clienteLogin");
		
		cliente = clienteService.obtenerCliente(cliente.getId());
		
		model.put("cliente", cliente);
		
		return "cliente/resumen";
	}
	
}
