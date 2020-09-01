package com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mitocode.javaweb.springbootmybatis.cliente.application.ClienteService;
import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Service
public class ClienteSecurityService implements UserDetailsService{

	@Autowired
	private ClienteService clienteService;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteService.obtenerCliente(username);
		Collection<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(cliente.getRol()));
		
		UserDetails userDetails = new User(cliente.getDocumento(), cliente.getClave(), roles);
		
		return userDetails;
	}
	
}
