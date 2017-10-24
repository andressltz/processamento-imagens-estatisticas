package com.iceberg.janelas.estatisticas;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.iceberg.janelas.Janela;
import com.iceberg.sistema.Imagem;

public class JanelaModaQuadrante_4 extends Janela {

	private static final long serialVersionUID = 1L;

	private int altura, largura;
	private int[] vetorOrdenado;

	/**
	 * d) Calcule a moda das tonalidades de cinza do quadrante 4.
	 */
	public JanelaModaQuadrante_4(Imagem imagem) {
		calculaQuadrante4(imagem);
		Integer moda = calculaModa(imagem);

		getContentPane().removeAll(); // Impede que os graficos se sobreponham 

		JOptionPane.showMessageDialog(null, "Moda das tonalidades de cinza do Quandrante 4 é " + moda);
	}

	private Integer calculaModa(Imagem imagem) {
		/*
		 * Para encontrar o valor da moda Ã© necessÃ¡rio percorrer o vetor
		 * ordenado, procurando por valores iguais dentro do vetor. O nÃºmero
		 * destas ocorrÃªncias Ã© a moda
		 */

		ordenarVetor(imagem);
		int moda = vetorOrdenado[0];
		int qtdModa = 0;
		int qtdAtual = 1;

		for (int i = 1; i <= vetorOrdenado.length; i++) {
			if (i < vetorOrdenado.length && vetorOrdenado[i - 1] == vetorOrdenado[i]) { // Identifica uma moda
				qtdAtual++;

			} else {
				if (qtdAtual > qtdModa) {
					moda = vetorOrdenado[i - 1];
					qtdModa = qtdAtual;

				}

				qtdAtual = 1;

			}

		}

		return moda;
	}

	private Integer[][] calculaQuadrante4(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			imagem = Imagem.getCopiaEscalaCinza(imagem);

			largura = (imagem.getLargura() / 2) + 1;
			altura = (imagem.getAltura() / 2) + 1;

			Integer quadrante4[][] = new Integer[largura][altura];

			for (int x = 0; x < largura; x++) {
				for (int y = 0; y < altura; y++) {
					quadrante4[x][y] = imagem.getTomCinza(x, y);
				}
			}
			return quadrante4;
		}

		return null;
	}

	/*
	 * Um vetor recebe valores de uma matriz bi-dimensional, assim ordenando em
	 * ordem crescente
	 */
	private void ordenarVetor(Imagem imagem) {
		if (vetorOrdenado != null) {
			return;
		}

		vetorOrdenado = new int[altura * largura];

		for (int coluna = 0; coluna < largura; coluna++) {
			for (int linha = 0; linha < altura; linha++) {
				vetorOrdenado[coluna * altura + linha] = imagem.getTomCinza(coluna, linha);

			}

		}

		Arrays.sort(vetorOrdenado);

	}

}
