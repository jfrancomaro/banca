package com.mitocode.javaweb.springbootmybatis;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitocode.javaweb.springbootmybatis.cliente.application.ClienteService;
import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Controller
public class InicioController {

	@Autowired
	private ClienteService clienteService;
	
//	@RequestMapping(value = { "/" , "/login" , "/inicio" },	method = RequestMethod.GET)
////	@ResponseBody
//	public String inicio() {
//		return "login";
//	}
//	
//	@GetMapping("/home")
//	public String home() {
//		return "home";
//	}
//	
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(ModelMap model, @RequestParam String username, @RequestParam String password, HttpSession session) {
//		
//		String result = "";
//		
//		Optional<Cliente> cliente = Optional.ofNullable(clienteService.validarClave(username, password));
//		
//		if (cliente.isPresent()) {
//			result = "home";
//			session.setAttribute("clienteLogin", cliente.get());
//		}else {
//			result = "login";
//			model.put("message", "Usuario no existe y/o Clave inválida");
//		}
//		
//		return result;
//	}
	
	
}
