package com.microservicios.personas.models.service;

import java.util.List;

import com.microservicios.personas.models.entity.Persona;

public interface IPersonaService {
	
	List<Persona> findAll();
	Persona findById(Long id);
	Persona createPersona(Persona persona);
	Persona updatePersona(Persona persona);
	Persona deletePersona(Long id);
}
