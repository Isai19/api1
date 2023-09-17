package com.pichincha1;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.pichincha1.model.ERole;
import com.pichincha1.model.RoleEntity;
import com.pichincha1.model.UserEntity;
import com.pichincha1.repo.UserRepo;
import com.pichincha1.service.AuditorAwareImpl;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@EnableJpaAuditing(auditorAwareRef = "auditor")
@SpringBootApplication
//@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class PichinchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PichinchaApplication.class, args);
	}
	
    @Bean
    public AuditorAware<String> auditor() {
        return new AuditorAwareImpl();
    }
    
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);
		return new RestTemplate(factory);
	}    

/*    
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepo userRepo;

	@Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("santiago@mail.com")
					.username("santiago")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("anyi@mail.com")
					.username("anyi")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("andrea@mail.com")
					.username("andrea")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.INVITED.name()))
							.build()))
					.build();

			userRepo.save(userEntity);
			userRepo.save(userEntity2);
			userRepo.save(userEntity3);
		};
	}
*/

}
