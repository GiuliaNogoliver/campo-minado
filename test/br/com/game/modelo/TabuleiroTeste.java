package br.com.game.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabuleiroTeste {

	Tabuleiro tabuleiro;
	
	@BeforeEach
	void testeIniciarTabuleiro() {
		tabuleiro = new Tabuleiro(3, 3, 2);
	}

}
