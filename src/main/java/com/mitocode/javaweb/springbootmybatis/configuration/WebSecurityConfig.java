package com.mitocode.javaweb.springbootmybatis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.security.ClienteSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ClienteSecurityService clienteSecurityService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(clienteSecurityService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/img/**", "/js/**", "/vendors/**").permitAll()
				.antMatchers("/cliente/**").hasAuthority("USER")
				.antMatchers("/api/**").permitAll()
				.antMatchers("/api-docs/**").permitAll()
			.anyRequest().authenticated()
			.and()
				.csrf().disable().headers().frameOptions().disable()
			.and()
				.exceptionHandling().accessDeniedPage("/home")
			.and()
			.formLogin()
				.loginPage("/login")
//				.defaultSuccessUrl("/home", true)
				.successHandler(authenticationSuccessHandler)
					.permitAll()
			.and()
			.logout()
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.addLogoutHandler(
					(request, response, authentication) -> {
						request.getSession().removeAttribute("clienteLogin");
					})
				.permitAll();
	}

}
