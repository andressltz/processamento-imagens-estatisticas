package com.iceberg.utils;

import java.text.DecimalFormat;

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
		int centerTopPointX = 0;
		int centerTopPointY = 0;
		int leftTopPoint = 0;
		int leftPointX = imagem.getLargura();
		int centerLeftPointY = 0;
		int topLeftPointY = 0;

		for (int y = 1; y < imagem.getAltura(); y++) {
			for (int x = 1; x < imagem.getLargura(); x++) {

				int rgb = imagem.getBufferedImage().getRGB(x, y);
				if (rgb == whiteColor) {
					if (centerTopPointX == 0 && leftTopPoint != 0) {
						centerTopPointX = (((x - 1) - leftTopPoint) / 2) + leftTopPoint;
						centerTopPointY = y;
					}
					if (x == leftPointX && centerLeftPointY == 0 && topLeftPointY != 0) {
						centerLeftPointY = (((y - 1) - topLeftPointY) / 2) + topLeftPointY;
					}
				} else {
					if (centerTopPointX == 0) {
						leftTopPoint = x;
					} else {
						if (x < leftPointX) {
							leftPointX = x;
							topLeftPointY = y;
						}
					}
				}

			}
		}

		int raio = centerLeftPointY - centerTopPointY;
		double perimetro = (2 * raio) * Math.PI;
		double area = Math.PI * (Math.pow(raio, 2));

		perimetro = Double.valueOf(new DecimalFormat("#.##").format(perimetro));
		area = Double.valueOf(new DecimalFormat("#.##").format(area));
		JOptionPane.showMessageDialog(null, "O perímetro do círculo é " + perimetro + " pixels e a área é " + area + " pixels. Seu raio é de " + raio + " pixels.");
	}

}
