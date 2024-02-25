package br.com.game.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campo = new ArrayList<Campo>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				campo.add(new Campo(linha, coluna));
			}
		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campo) {
			for (Campo c2 : campo) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {
			minasArmadas = campo.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campo.size());
			campo.get(aleatorio).minar();
		} while (minasArmadas < minas);
	}

	public boolean objetivoAlcancado() {
		return campo.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campo.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
}