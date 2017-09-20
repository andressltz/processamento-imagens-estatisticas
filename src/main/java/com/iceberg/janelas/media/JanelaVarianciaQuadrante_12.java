package com.iceberg.janelas.media;

import java.awt.Label;
import java.awt.image.BufferedImage;

import com.iceberg.janelas.Janela;
import com.iceberg.sistema.Imagem;

public class JanelaVarianciaQuadrante_12 extends Janela {

	private static final long serialVersionUID = 1L;

	private int altura, largura;

	/**
	 * e) Calcule a variância das tonalidades de cinza dos quadrantes 1 e 2.
	 */
	public JanelaVarianciaQuadrante_12(Imagem imagem) {
		calculaQuadrante1(imagem);
		Integer varianciaQ1 = calcularVariancia(imagem);
		calculaQuadrante2(imagem);
		Integer varianciaQ2 = calcularVariancia(imagem);

		getContentPane().removeAll(); // Impede que os graficos se sobreponham 
		getContentPane().add(new Label("Variância das tonalidades de cinza do Quandrante 1 é " + varianciaQ1 + " e do Quadrante 2 é " + varianciaQ2));

		MontaJanela(500, 150);
	}

	private Integer[][] calculaQuadrante1(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			imagem = Imagem.getCopiaEscalaCinza(imagem);

			largura = imagem.getLargura() / 2;
			altura = imagem.getAltura() / 2;

			Integer quadrante1[][] = new Integer[largura][altura];

			for (int x = 0; x < largura; x++) {
				for (int y = 0; y < altura; y++) {
					quadrante1[x][y] = imagem.getTomCinza(x, y);
				}
			}
			return quadrante1;
		}

		return null;
	}

	private Integer[][] calculaQuadrante2(Imagem imagem) {
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

	private Integer calcularVariancia(Imagem imagem) {
		/*
		 * Para calcular a variÃ¢ncia, Ã© necessÃ¡rio realizar um somatÃ³rio da
		 * tonalidade dos pixels em escala de cinza, subtraida pelo valor da
		 * mÃ©dia. Com o valor encontrado, Ã© potencializado na base 2. Este
		 * somatÃ³rio Ã© dividido pela altura e largura da imagem
		 */

		int soma = 0;
		int diff;
		int variancia;

		for (int coluna = 0; coluna < largura; coluna++) {
			for (int linha = 0; linha < altura; linha++) {
				diff = imagem.getTomCinza(coluna, linha) - calculaMedia(imagem);
				soma += diff * diff;
			}

		}

		variancia = soma / (largura * altura);
		return variancia;
	}

	private Integer calculaMedia(Imagem imagem) {
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

}
