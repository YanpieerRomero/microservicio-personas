package com.microservicios.personas.models.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.microservicios.personas.models.entity.Persona;
import com.microservicios.personas.models.service.IPersonaService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar(){
		List<Persona> personas = new ArrayList<>();
		personas = personaService.findAll();
		if(personas.isEmpty()){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(personas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> detalle(@PathVariable Long id) {
		Persona personaDb = personaService.findById(id);
		if(personaDb == null ) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(personaDb);
	}

	@PostMapping
	public ResponseEntity<Persona> crearPersona(@Valid @RequestBody Persona persona, BindingResult result) {

		// si hay errores con las validaciones
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		Persona personaCreate = personaService.createPersona(persona);

		return ResponseEntity.status(HttpStatus.CREATED).body(personaCreate);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
		persona.setId(id);
		Persona personaDb = personaService.updatePersona(persona);
		if(personaDb == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(personaDb);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Persona> eliminarPersona(@PathVariable Long id) {
		Persona personaDelete = personaService.deletePersona(id);
		if(personaDelete == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(personaDelete);
	}

	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream()
				.map(err -> {
					Map<String, String> error = new HashMap<>();
					// getField(): campo donde se genera el error
					// DefaultMessage(): mensaje del error
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());

		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("errors")
				.messages(errors).build();

		// convierto ErrorMessage en un JSON
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try{
			jsonString = mapper.writeValueAsString(errorMessage);   // convierto
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
		}
		return jsonString;
	}
}
