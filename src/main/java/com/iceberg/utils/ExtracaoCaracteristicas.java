package com.iceberg.utils;

import com.iceberg.sistema.Imagem;

public class ExtracaoCaracteristicas {

	public static void calcularAreaPerimetroQuadrado(Imagem imagem) {
		for (int i = 1; i < imagem.getLargura(); i++) {
			for (int j = 1; j < imagem.getAltura(); j++) {
				int rgb = imagem.getBufferedImage().getRGB(i, j);
				System.out.print(rgb + " ");
			}
			System.out.println("");
			// -1 é branco
		}

	}

}
