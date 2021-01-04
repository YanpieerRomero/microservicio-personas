package com.microservicios.personas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor // constructor con todos los atributos
@NoArgsConstructor  // constructor vacio
@Builder            // construir nuevas instancias de la clase con el metodo builder()
public class Persona{

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre no puede ser vacio")
	private String nombre;

	@NotEmpty(message = "El apellido no puede ser vacio")
	private String apellido;

	@NotEmpty(message = "la dirección no puede ser vacia")
	private String direccion;
	private String telefono;
	private String estado;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

}
