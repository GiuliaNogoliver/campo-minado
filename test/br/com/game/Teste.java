package br.com.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void testeUm() {
		int a = 1 + 2;
		assertEquals(3, a);
	}
	
	@Test
	void testeDois() {
		String name = "Giulia";
		assertEquals("Giulia", name);
	}
}
