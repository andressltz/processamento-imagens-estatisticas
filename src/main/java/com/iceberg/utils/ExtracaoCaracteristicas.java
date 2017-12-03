package com.iceberg.utils;

import javax.swing.JOptionPane;

import com.iceberg.sistema.Imagem;

public class ExtracaoCaracteristicas {

	public static void calcularAreaPerimetroQuadrado(Imagem imagem) {
		final int whiteColor = -1;
		int firstPoint = 0;
		int lastPoint = 0;

		for (int y = 1; y < imagem.getAltura(); y++) {
			for (int x = 1; x < imagem.getLargura(); x++) {
				int rgb = imagem.getBufferedImage().getRGB(x, y);
				if (rgb == whiteColor) {
					if (firstPoint != 0) {
						lastPoint = x;
						break;
					}
				} else {
					if (firstPoint == 0) {
						firstPoint = x;
					}
				}
			}

			if (firstPoint != 0 && lastPoint != 0) {
				break;
			}
		}

		int lateral = lastPoint - firstPoint;
		int perimetro = lateral + lateral + lateral + lateral;
		int area = lateral * lateral;
		JOptionPane.showMessageDialog(null, "O perímetro do quadrado é " + perimetro + " pixels e a área é " + area + " pixels. Sua largura é de " + lateral + " pixels.");
	}

	public static void calcularAreaPerimetroCirculoMaior(Imagem imagem) {
		final int whiteColor = -1;
		int firstPoint = 0;
		int lastPoint = 0;

		for (int y = 1; y < imagem.getAltura(); y++) {
			for (int x = 1; x < imagem.getLargura(); x++) {
				int rgb = imagem.getBufferedImage().getRGB(x, y);
				if (rgb == whiteColor) {
					if (firstPoint != 0) {
						lastPoint = x;
						break;
					}
				} else {
					if (firstPoint == 0) {
						firstPoint = x;
					}
				}
			}

			if (firstPoint != 0 && lastPoint != 0) {
				break;
			}
		}

		int lateral = lastPoint - firstPoint;
		int perimetro = lateral + lateral + lateral + lateral;
		int area = lateral * lateral;
		JOptionPane.showMessageDialog(null, "O perímetro do quadrado é " + perimetro + " pixels e a área é " + area + " pixels. Sua largura é de " + lateral + " pixels.");
	}

}
