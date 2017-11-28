package com.iceberg.utils;

import java.awt.Color;

import com.iceberg.sistema.Imagem;

public class ExtracaoCaracteristicas {

	public static void calcularAreaPerimetroQuadrado(Imagem imagem) {
		for (int i = 1; i < imagem.getLargura(); i++) {
			for (int j = 1; j < imagem.getAltura(); j++) {
				int rgb = imagem.getBufferedImage().getRGB(i, j);
				Color color = new Color(rgb);
				if (rgb == -1) { // branco coloca espaço
					System.out.print("  ");
				} else {
					System.out.print(color.getBlue() + " ");
					// -16777216
				}
			}
			System.out.println("");
		}

	}

}
