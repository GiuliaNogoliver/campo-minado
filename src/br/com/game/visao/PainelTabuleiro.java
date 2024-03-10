package br.com.game.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.game.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

		tabuleiro.adicionarObservador(e -> {

			SwingUtilities.invokeLater(() -> {
				if (e.booleanValue() == true) {
					JOptionPane.showMessageDialog(this, "Ganhou");
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu");
				}
				tabuleiro.reiniciar();
			});
		});
	}
}
