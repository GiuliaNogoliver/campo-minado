package br.com.game.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	private final int linha;
	private final int coluna;

	private boolean aberto;
	private boolean minado;
	private boolean marcado;

	private List<Campo> vizinhos = new ArrayList<>();
	// aqui em onservadores da para usar set para nao correr o risco
	// de ter uma duplicidade de observadores
	private List<CampoObservador> observadores = new ArrayList<>();

	// Pode criar tbm esse BiConsumer ao enves da interface
	// private List<BiConsumer<Campo, CampoEvento>> observador = new
	// ArrayList<BiConsumer<Campo,CampoEvento>>();

	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public void adicionarObservador(CampoObservador observador) {
		observadores.add(observador);
	}

	public void notificarObservador(CampoEvento evento) {
		observadores.stream()
				// this pq é esse proprio campo q estar usando o metodo
				.forEach(o -> o.eventoOcorreu(this, evento));
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	void alternarMarcacao() {
		// Eu so posso alterar a marcacao de um campo que esta fechado
		if (!aberto) {
			// se é falso vira verdadeiro se for verdadeiro vira falso
			marcado = !marcado;

			if (marcado) {
				notificarObservador(CampoEvento.MARCAR);
			} else {
				notificarObservador(CampoEvento.DESMARCAR);
			}
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			if (minado) {
				notificarObservador(CampoEvento.EXPLODIR);
				return true;
			}
			
			setAberto(true);
			
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {
			// O abrir retorna false pq vc pode nao conseguir abrir
			// ele por causa das regras e vc mesmo definiu
			return false;
		}
	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void minar() {
		minado = true;
	}

	// metodo de consulta p teste
	// um getter
	public boolean isMarcado() {
		return marcado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
		if(aberto) {
			notificarObservador(CampoEvento.ABRIR);
		}
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public boolean isMinado() {
		return minado;
	}
}
