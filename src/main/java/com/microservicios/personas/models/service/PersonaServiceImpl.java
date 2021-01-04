package com.microservicios.personas.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.personas.models.dao.PersonaDao;
import com.microservicios.personas.models.entity.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private PersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		return  personaDao.findById(id).orElse(null);
	}

	@Override
	public Persona createPersona(Persona persona) {
		persona.setEstado("CREADO");
		persona.setCreateAt(new Date());
		return personaDao.save(persona);
	}

	@Override
	public Persona updatePersona(Persona persona) {
		Persona personaDb = findById(persona.getId());
		if (personaDb == null) {
			return null;
		}
		personaDb.setDireccion(persona.getDireccion());
		personaDb.setTelefono(persona.getTelefono());

		return personaDao.save(personaDb);
	}

	@Override
	public Persona deletePersona(Long id) {
		Persona personaDb = findById(id);
		if (personaDb == null) {
			return null;
		}
		personaDb.setEstado("ELIMINADO");

		return personaDao.save(personaDb);

	}


}
