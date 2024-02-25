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

	@Test
	void testeMesmoCampo() {
		Campo vizinho = new Campo(3, 3);
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
		assertThrows(ExplosaoException.class, () -> {
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

	@Test
	void testGetLinha() {
		Campo campo = new Campo(3, 4);
		assertEquals(3, campo.getLinha());
	}

	@Test
	void testGetColuna() {
		Campo campo = new Campo(3, 4);
		assertEquals(4, campo.getColuna());
	}

	@Test
	void testObjetivoAlcancadoDesvendado() {
		Campo campo1 = new Campo(0, 0);
		campo1.abrir();
		assertTrue(campo1.objetivoAlcancado());
	}

	@Test
	void testObjetivoAlcancadoProtegido() {
		Campo campo = new Campo(0, 0);
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}

	/*
	 * @Test void testObjetivoAlcancadoNaoAlcancado() { Campo campo1 = new Campo(0,
	 * 0); campo1.minar(); campo1.abrir(); campo1.alternarMarcacao();
	 * 
	 * assertThrows(ExplosaoException.class, ()->{ campo1.abrir() }); }
	 */

	@Test
	void testeVizinhacaMinada() {
		Campo campo1 = new Campo(3, 2);
		Campo campo2 = new Campo(2, 3);

		campo.adicionarVizinho(campo1);
		campo.adicionarVizinho(campo2);

		campo1.minar();
		campo2.minar();

		assertEquals(2, campo.minasNaVizinhanca());

	}

	@Test
	void testeReiniciar() {
		Campo campo1 = new Campo(1, 1);
		campo1.alternarMarcacao();

		Campo campo2 = new Campo(1, 2);
		campo2.minar();

		Campo campo3 = new Campo(1, 3);
		campo3.abrir();

		campo1.reiniciar();
		campo2.reiniciar();
		campo3.reiniciar();

		assertFalse(campo1.isAberto());
		assertFalse(campo1.isMinado());
		assertFalse(campo1.isMarcado());

		assertFalse(campo2.isAberto());
		assertFalse(campo2.isMinado());
		assertFalse(campo2.isMarcado());

		assertFalse(campo3.isAberto());
		assertFalse(campo3.isMinado());
		assertFalse(campo3.isMarcado());
	}
	
	@Test
	void testeToStringMarcado(){
		Campo campo1 = new Campo(1, 1);
		campo1.alternarMarcacao();
		assertEquals("x", campo1.toString());
	}
	
	@Test
	void testeToStringAbertoMinado() {
		Campo campo1 = new Campo(1, 1);
		campo1.minar();
		// Nao da para testar diretamente
		assertThrows(ExplosaoException.class, () -> {
			campo1.abrir();
            assertEquals("*", campo1.toString());
        });
	}
	
	@Test
	void testeToStringMinasNaVizinhanca() {
		Campo campo1 = new Campo(1, 1);
		Campo campo2 = new Campo(1, 2);
		
		campo1.adicionarVizinho(campo2);
		campo2.adicionarVizinho(campo1);		
		
		campo2.minar();
		campo1.abrir();
		
		assertEquals("1", campo1.toString());
		
	}
	
	@Test
	void testeToStringAberto() {
		campo.abrir();
		assertEquals(" ", campo.toString());
	}
	
	@Test 
	void testeToString(){
		campo.toString();
		assertEquals("?", campo.toString());
	}
}