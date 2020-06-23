package org.mairie.comores.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource  dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	  /*  auth.inMemoryAuthentication()
	    .withUser("admin").password("{noop}1234").roles("ADMIN","USER");
	    auth.inMemoryAuthentication()
	    .withUser("user").password("{noop}1234").roles("USER");*/
		
	 	auth.jdbcAuthentication()        // ici je dois indiquer la meme source de base de donnée que l'application
								         // pour ce faire il faut declarer un objet dataSource au debut
		.dataSource(dataSource)  
										// il faut indiquer au spring security 
										// quelle requete doit executer pour prendre l'utilisateur
		.usersByUsernameQuery("select username as principal, password as credentials, active from users where username = ?")
		                                // lorsqu'il aura identifier l'utilisateur il doit connaitre aussi son role
		                                // donc il doit executer la requete suivante pour avoir ses roles
		.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username = ?")
		
		.rolePrefix("ROLE_")           // on va indiquer à spring securité le prefixe qu'il faut ajouter 
		
		.passwordEncoder( new MessageDigestPasswordEncoder("MD5")); // on va lui indique d'utlisateur Encoder car les s de passes son codés*/ 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/employes","/consultationEmployes").hasRole("USER");
		http.formLogin();
		http.authorizeRequests().antMatchers("/saveEmployes","/suppressionEmploye").hasRole("ADMIN");
		// ici veut dire que si il n'ya pas le droit d'acceder il va sur l'action /403
		http.exceptionHandling().accessDeniedPage("/403"); 	 
	}

}
