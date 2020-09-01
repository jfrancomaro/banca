package com.mitocode.javaweb.springbootmybatis.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mitocode.javaweb.springbootmybatis.cliente.application.ClienteService;
import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private HttpSession session;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	enum ROLES {
		ADMIN, USER;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String username = ((User) authentication.getPrincipal()).getUsername();
		
		Cliente cliente = clienteService.obtenerCliente(username);
		
		if(cliente != null) {
			session.setAttribute("clienteLogin", cliente);
		}
		
		String url = "/home";
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			String authorityName = authority.getAuthority(); 
			if(ROLES.ADMIN == ROLES.valueOf(authorityName)) {
				url = "/home";
				break;
			} else if(ROLES.USER == ROLES.valueOf(authorityName)) {
				url = "/cliente/resumen";
				break;
			}
		} 
		
		redirectStrategy.sendRedirect(request, response, url);
	}
	
}
