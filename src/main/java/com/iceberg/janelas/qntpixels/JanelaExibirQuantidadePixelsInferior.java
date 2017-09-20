package com.iceberg.janelas.qntpixels;

import java.awt.Label;
import java.awt.image.BufferedImage;

import com.iceberg.janelas.Janela;
import com.iceberg.sistema.Imagem;

public class JanelaExibirQuantidadePixelsInferior extends Janela {

	private static final long serialVersionUID = 1L;

	private int altura, largura;

	/**
	 * g) Informe quantos pixels com tonalidade superior a 150 tem na metade
	 * inferior da imagem.
	 */
	public JanelaExibirQuantidadePixelsInferior(Imagem imagem) {
		int quantidade = calculaMetadeInferior(imagem);

		getContentPane().removeAll(); // Impede que os graficos se sobreponham 
		getContentPane().add(new Label("Na metade inferior da imagem existem " + quantidade + " pixels com tonalidade superior a 150"));

		MontaJanela(500, 150);
	}

	private int calculaMetadeInferior(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			imagem = Imagem.getCopiaEscalaCinza(imagem);

			largura = imagem.getLargura();
			altura = (imagem.getAltura() / 2) + 1;

			Integer metadeInferior[][] = new Integer[largura][altura];
			int tonalidade;
			int count = 0;

			for (int x = 0; x < largura; x++) {
				for (int y = 0; y < altura; y++) {
					tonalidade = imagem.getTomCinza(x, y);
					metadeInferior[x][y] = tonalidade;
					if (tonalidade > 150) {
						count++;
					}
				}
			}
			return count;
		}

		return 0;
	}





}
