package br.com.game.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.game.excessao.ExplosaoException;

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
	
	// Testar alterar marcacao
	@Test
	void testeValorPadraAtributoMarcacao() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlterarMarcacaoDuasVezes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	// testar abrir		
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	// Nesse teste queremos acertar na excessao por isso usamos o assertThrows
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		// Nessa lambda temos a class que acertaremos e atraves de q metodo
		assertThrows(ExplosaoException.class, ()->{
			campo.abrir();
		});
	}
	
	// vizinhanca segura
	@Test
	void testeVizinhancaSegura() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
}