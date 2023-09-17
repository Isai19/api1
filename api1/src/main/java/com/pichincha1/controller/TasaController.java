package com.pichincha1.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.session.SessionInformation;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pichincha1.model.Tasa;
import com.pichincha1.model.TasaCambio;
import com.pichincha1.repo.TasaRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/tasa")
//@SecurityRequirement(name = "javainuseapi")
public class TasaController {
	

	@Autowired
	private RestTemplate restTemplate;
/*
	@Autowired	
	private TasaRepo tasaRepo;*/

	@Operation(summary = "This method is used to get the clients.")
	@GetMapping("/list")
	public ResponseEntity<List<Tasa>> tasas(@RequestHeader(value="Authorization") String authorizationHeader) throws Exception  {
		try {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<Tasa> entity = new HttpEntity<>(headers);
		
        ResponseEntity<List<Tasa>> response = restTemplate.exchange("http://localhost:8090/tasa/list", HttpMethod.GET,
        		entity, new ParameterizedTypeReference<List<Tasa>>() {
                });

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
		}catch(Exception e) {
			
			System.out.print(e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/insert")
	public Tasa insertar(@RequestBody Tasa tasa, @RequestHeader(value="Authorization") String authorizationHeader) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<Tasa> entity = new HttpEntity<>(tasa, headers);
		
        ResponseEntity<Tasa> response = restTemplate.exchange("http://localhost:8090/tasa/insert", HttpMethod.POST,
        		entity, new ParameterizedTypeReference<Tasa>() {
                });

        return response.getBody();

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Tasa> modificar(@PathVariable String id, @RequestBody Tasa tasa, @RequestHeader(value="Authorization") String authorizationHeader) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<Tasa> request = new HttpEntity<>(tasa, headers);
		
        ResponseEntity<Tasa> response = restTemplate.exchange("http://localhost:8090/tasa/update/"+id, HttpMethod.PUT, request,
                Tasa.class);
        return response;	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map> eliminar(@PathVariable Integer id, @RequestHeader(value="Authorization") String authorizationHeader){
		//tasaRepo.deleteById(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<Tasa> entity = new HttpEntity<>(headers);
		
        ResponseEntity<Map> response = restTemplate.exchange("http://localhost:8090/tasa/delete/"+id, HttpMethod.DELETE,
                entity, Map.class);
        return response;		
	}
	
	@GetMapping("/buscarId/{id}")
	public Tasa findById(@PathVariable Integer id, @RequestHeader(value="Authorization") String authorizationHeader ){
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<TasaCambio> entity = new HttpEntity<>(headers);
		
        ResponseEntity<Tasa> response = restTemplate.exchange("http://localhost:8090/tasa/buscarId/"+id, HttpMethod.GET,
        		entity, new ParameterizedTypeReference<Tasa>() {
                });
		
		return response.getBody();
	}
	
	@GetMapping("/tasacambio/{monedaO}/{monedaD}/{monto}")
	public TasaCambio TasaChangue(@PathVariable String monedaO, @PathVariable String monedaD, @PathVariable Integer monto, @RequestHeader(value="Authorization") String authorizationHeader ){
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(authorizationHeader.substring(7));
		HttpEntity<TasaCambio> entity = new HttpEntity<>(headers);
		
        ResponseEntity<TasaCambio> response = restTemplate.exchange("http://localhost:8090/tasa/tasacambio/"+monedaO+"/"+monedaD+"/"+monto, HttpMethod.GET,
        		entity, new ParameterizedTypeReference<TasaCambio>() {
                });
		
		return response.getBody();
	}

	/*
	@GetMapping("/session")
	public ResponseEntity<?> getDetailsSession(){
		
		String sessionId = "";
		User userObject = null;
		
		List<Object> sessions = sessionRegistry.getAllPrincipals();
		
		for(Object session : sessions){
			if(session instanceof User) {
				userObject = (User) session;
			}
			
			List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
			
			for(SessionInformation sessionInformation : sessionInformations) {
				sessionId = sessionInformation.getSessionId();
			}
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("response", "helloworld");
		response.put("sessionId", sessionId);
		response.put("sessionUser", userObject);
		
		return ResponseEntity.ok(response);
		
	}*/

}
