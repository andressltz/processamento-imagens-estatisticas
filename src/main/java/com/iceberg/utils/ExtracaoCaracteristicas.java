package com.iceberg.utils;

import javax.swing.JOptionPane;

import com.iceberg.sistema.Imagem;

public class ExtracaoCaracteristicas {

	public static void calcularAreaPerimetroQuadrado(Imagem imagem) {
		final int whiteColor = -1;
		int firstPoint = 0;
		int lastPoint = 0;

		for (int i = 1; i < imagem.getLargura(); i++) {
			for (int j = 1; j < imagem.getAltura(); j++) {
				int rgb = imagem.getBufferedImage().getRGB(i, j);
				if (rgb == whiteColor) {
					if (firstPoint != 0) {
						lastPoint = j;
					}
				} else {
					if (firstPoint == 0) {
						firstPoint = j;
					}
				}

				if (firstPoint != 0 && lastPoint != 0) {
					break;
				}
			}
		}

		int lateral = lastPoint - firstPoint;
		int perimetro = lateral + lateral + lateral + lateral;
		int area = lateral * lateral;
		JOptionPane.showMessageDialog(null, "O perímetro do quadrado é " + perimetro + " pixels e a área é " + area + " pixels. Sua largura é de " + lateral + " pixels.");
	}

}
