package uk.co.jemos.podam.test.dto.docs.example;

import java.io.Serializable;

public class Persona implements Serializable{

	private static final long serialVersionUID = -5750571414898160980L;
	
	private String nombre;
	private Integer edad;
	
	public Persona(String nombre, Integer edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	
	
}
