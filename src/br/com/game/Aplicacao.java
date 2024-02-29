package br.com.game;

import br.com.game.modelo.Tabuleiro;
import br.com.game.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		//  definindo a qnt de linhas, colunas e minas
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
		
		System.out.println(tabuleiro);
		
	}
}
