package com.pichincha1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pichincha1.filters.JwtAuthenticationFilter;
import com.pichincha1.filters.JwtAuthorizationFilter;
import com.pichincha1.jwt.JwtUtils;
import com.pichincha1.service.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;
	
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/hello").permitAll();
                    auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**","/configuration/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
	/*
	 * @Bean UserDetailsService userDetailsService(){ InMemoryUserDetailsManager
	 * manager = new InMemoryUserDetailsManager();
	 * manager.createUser(User.withUsername("santiago") .password("1234") .roles()
	 * .build());
	 * 
	 * return manager; }
	 * 
	 */

  @Bean
  PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
 }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
      return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder)
              .and().build();
  }
	 	
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity	
				.authorizeHttpRequests(auth -> {
					auth.anyRequest().authenticated();
				})
				.formLogin()
					.successHandler(succesHandler())
					.permitAll()
				.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.invalidSessionUrl("/login")
					.maximumSessions(1)
					.expiredUrl("/login")
					.sessionRegistry(sessionRegistry())
				.and()
				.sessionFixation()
					.migrateSession()
				.and()
				.build();
	}
	
	@Bean
	public SessionRegistry sessionRegistry () {
		return new SessionRegistryImpl();
	}
	
	public AuthenticationSuccessHandler succesHandler() {
		return ((request, response, authentication)->{
			response.sendRedirect("/tasa/session");
		});
	}*/
	
}
