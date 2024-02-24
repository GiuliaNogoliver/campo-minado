package br.com.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.game.modelo.Campo;

class CampoTeste {

	Campo campo;
	
	// Antes de todo teste instancie
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoDistanciaEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}

	@Test
	void testeVizinhoDistanciaDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistanciaEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistanciaEmbaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean result = campo.adicionarVizinho(vizinho);
		assertTrue(result);
	}
	
	// Se nao for vizinho deve retornar false
	@Test
	void testeNaoVizinhoDistancia3() {
		Campo vizinho = new Campo(1, 1);
		boolean result = campo.adicionarVizinho(vizinho);
		assertFalse(result);
	}
}