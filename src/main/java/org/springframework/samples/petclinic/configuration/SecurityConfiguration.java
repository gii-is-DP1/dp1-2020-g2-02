package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				/*BIBLIONET*/
			
				.antMatchers("/bibliotecarios/**").hasAnyAuthority("admin")
				.antMatchers("/miembros/**").hasAnyAuthority("bibliotecario", "admin")
				.antMatchers("/libros").permitAll()
				.antMatchers("/autores/**").permitAll()
				.antMatchers("/editoriales/**").permitAll()
				.antMatchers("/prestamos").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/save").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/new").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/conceder/{prestamoId}").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/finalizar/{prestamoId}").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/rechazar/{prestamoId}").hasAnyAuthority("bibliotecario")
				.antMatchers("/prestamos/misprestamos").hasAnyAuthority("miembro")
				.antMatchers("/novedades").permitAll()
				.antMatchers("/inicio").authenticated()
				.antMatchers("/novedades/**").hasAnyAuthority("bibliotecario")
				.antMatchers("/libros/new").hasAnyAuthority("bibliotecario","admin")
				.antMatchers("/libros/save").hasAnyAuthority("bibliotecario","admin")
				.antMatchers("/libros/reservar/**").hasAnyAuthority("miembro")
				.antMatchers("/ejemplares/**").hasAnyAuthority("bibliotecario","admin")
				.antMatchers("/proveedores/**").hasAnyAuthority("bibliotecario","admin")
				.antMatchers("/encargos/**").hasAnyAuthority("admin", "bibliotecario")
				.antMatchers("/generos/**").hasAnyAuthority("admin", "bibliotecario")
				.antMatchers("/puntuacion/**").hasAnyAuthority("miembro")
				.antMatchers("/sugerencias").authenticated()
				.antMatchers("/sugerencias/**").hasAnyAuthority("miembro")
				.antMatchers("/estadisticas").hasAnyAuthority("admin", "bibliotecario")
				.antMatchers("/v3/**").permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				/*PETCLINIC*/
				.antMatchers("/users/new").permitAll()
				.antMatchers("/owners/**").hasAnyAuthority("owner", "admin")			
				.antMatchers("/vets/**").authenticated()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
                http.formLogin().defaultSuccessUrl("/inicio", true);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
	
}


