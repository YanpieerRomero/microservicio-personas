package com.microservicios.personas.models.dao;

import com.microservicios.personas.models.entity.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, Long>{

}
