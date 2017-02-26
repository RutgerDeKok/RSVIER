package test;

import static org.junit.Assert.*;

import org.junit.Test;

import gebruiker.GebruikerValidator;

public class GebruikerValidatorTest {

	@Test
	public void testCheckPostcode() {
	
		String postcodeGoed = " 1111 aB";
		
		boolean resultaat = GebruikerValidator.checkPostcode(postcodeGoed);
		assertTrue(resultaat);
		
		String postcodeSlecht = " 1111 caB";
		
		boolean resultaat2 = GebruikerValidator.checkPostcode(postcodeSlecht);
		assertFalse(resultaat2);
		
		
	}

}
