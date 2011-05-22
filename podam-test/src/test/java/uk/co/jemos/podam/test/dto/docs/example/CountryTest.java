package uk.co.jemos.podam.test.dto.docs.example;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class CountryTest {

	@Test
	public void testConstructores(){
		PodamFactory factory = new PodamFactoryImpl(); //This will use the default Random Data Provider Strategy
		
		Persona persona = factory.manufacturePojo(Persona.class);
		
		System.out.println(persona);
		

	}
	
}
