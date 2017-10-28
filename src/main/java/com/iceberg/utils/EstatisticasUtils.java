package com.iceberg.utils;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.iceberg.sistema.Imagem;

public class EstatisticasUtils {

	private static int altura;

	private static int largura;

	private static int[] vetorOrdenado;

	public static int calculaMediaQuadrante2(Imagem imagem) {
		calculaQuadrante2(imagem);
		return calculaMedia(imagem);
	}

	public static Integer calculaModaQuadrante4(Imagem imagem) {
		calculaQuadrante4(imagem);

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

	public static Integer calculaMedianQuadrante3(Imagem imagem) {
		calculaQuadrante3(imagem);
		ordenarVetor(imagem);
		int mediana = vetorOrdenado[Math.round(vetorOrdenado.length / 2)];
		return mediana;
	}

	/*
	 * Um vetor recebe valores de uma matriz bi-dimensional, assim ordenando em ordem crescente
	 */
	private static void ordenarVetor(Imagem imagem) {
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

	public static Integer calcularVarianciaQ1(Imagem imagem) {
		calculaQuadrante1(imagem);
		return calcularVariancia(imagem);
	}

	public static Integer calcularVarianciaQ2(Imagem imagem) {
		calculaQuadrante2(imagem);
		return calcularVariancia(imagem);
	}

	public static Integer calcularVariancia(Imagem imagem) {
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

	private static Integer[][] calculaQuadrante1(Imagem imagem) {
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

	private static void calculaQuadrante3(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			imagem = Imagem.getCopiaEscalaCinza(imagem);

			largura = imagem.getLargura() / 2;
			altura = (imagem.getAltura() / 2) + 1;

			Integer quadrante3[][] = new Integer[largura][altura];

			for (int x = 0; x < largura; x++) {
				for (int y = 0; y < altura; y++) {
					quadrante3[x][y] = imagem.getTomCinza(x, y);
				}
			}
		}
	}

	private static void calculaQuadrante4(Imagem imagem) {
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
		}
	}

}
