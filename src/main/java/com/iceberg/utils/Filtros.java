package com.iceberg.utils;

import java.awt.image.BufferedImage;

import com.iceberg.model.Imagem;

public class Filtros {

	public static BufferedImage aplicaFiltroPrewitt(Imagem imagem) {

		return imagem.getBufferedImage();
	}

	public static BufferedImage aplicaFiltroGaussiano(Imagem imagem) {
		Imagem novaImagem = imagem.getCopia();
		int larguraMenor = imagem.getBufferedImage().getWidth() - 1;
		int alturaMenor = imagem.getBufferedImage().getHeight() - 1;
		int[][] matriz = criaMatrizGaussiano();

		for (int i = 1; i < alturaMenor; i++) {
			for (int j = 1; j < larguraMenor; j++) {

				double z = 0;
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						// TODO não sei como pegar a cor, se é um destes e se é isso mesmo que precisa pro gaus
						int corRGB = imagem.getBufferedImage().getRGB(j + (x - 1), i + (y - 1));
						int corCinza = imagem.getTomCinza(j + (x - 1), i + (y - 1));

						int r = (int) ((corRGB & 0x00FF0000) >>> 16);

						// Color color = imagem.GetPixel(j + (x - 1), i + (y - 1));
						// z += color.R * mascara[x, y];
						// z += corCinza * matriz[x][y];
						z += r * matriz[x][y];
						// z = 0;
					}
				}

				// TODO fazer
				// byte novoValor = Convert.ToByte( z / 16);
				// novaImagem.SetPixel(j, i, novoValor, novoValor, novoValor, 255);

				Double novaValorDouble = z / 16;
				byte novoValor = novaValorDouble.byteValue();
				novaImagem.getBufferedImage().setRGB(j, i, novoValor);
			}

		}

		return novaImagem.getBufferedImage();
	}

	private static int[][] criaMatrizGaussiano() {
		int[][] matrizBase = {
				{ 1, 2, 1 },
				{ 2, 4, 2 },
				{ 1, 2, 1 }
		};
		return matrizBase;
	}

}
