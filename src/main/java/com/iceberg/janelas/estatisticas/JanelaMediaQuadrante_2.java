package com.iceberg.janelas.estatisticas;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.iceberg.janelas.Janela;
import com.iceberg.sistema.Imagem;

public class JanelaMediaQuadrante_2 extends Janela {

	private static final long serialVersionUID = 1L;

	private static int altura;

	private static int largura;

	/**
	 * b) Calcule a média das tonalidades de cinza do quadrante 2.
	 */
	public JanelaMediaQuadrante_2(Imagem imagem) {
		Integer media = calculaMediaQuadrante2(imagem);

		getContentPane().removeAll(); // Impede que os graficos se sobreponham 
		JOptionPane.showMessageDialog(null, "Média das tonalidades de cinza do Quandrante 2 é " + media);
	}

	public static int calculaMediaQuadrante2(Imagem imagem) {
		calculaQuadrante2(imagem);
		Integer media = calculaMedia(imagem);
		return media;
	}

	private static Integer calculaMedia(Imagem imagem) {
		Integer soma = 0;
		Integer coluna, linha;
		Integer media = 0;

		for (coluna = 0; coluna < largura; coluna++) {
			for (linha = 0; linha < altura; linha++) {
				soma += imagem.getTomCinza(coluna, linha);
			}

		}

		media = soma / (largura * altura);
		return media;
	}

	private static Integer[][] calculaQuadrante2(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			imagem = Imagem.getCopiaEscalaCinza(imagem);

			largura = (imagem.getLargura() / 2) + 1;
			altura = imagem.getAltura() / 2;

			Integer quadrante2[][] = new Integer[largura][altura];

			for (int x = 0; x < largura; x++) {
				for (int y = 0; y < altura; y++) {
					quadrante2[x][y] = imagem.getTomCinza(x, y);
				}
			}
			return quadrante2;
		}

		return null;
	}

}
