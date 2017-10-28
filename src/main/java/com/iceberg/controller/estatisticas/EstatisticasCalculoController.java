package com.iceberg.controller.estatisticas;

import javax.swing.JOptionPane;

import com.iceberg.sistema.Imagem;
import com.iceberg.utils.EstatisticasUtils;

public class EstatisticasCalculoController {

	/**
	 * b) Calcule a média das tonalidades de cinza do quadrante 2.
	 */
	public static void calculaMediaQuadrante2(Imagem imagem) {
		Integer media = EstatisticasUtils.calculaMediaQuadrante2(imagem);
		JOptionPane.showMessageDialog(null, "Média das tonalidades de cinza do Quandrante 2 é " + media);
	}

	/**
	 * c) Calcule a mediana do quadrante 3
	 */
	public static void calculaMedianaQuadrante3(Imagem imagem) {
		Integer mediana = EstatisticasUtils.calculaMedianQuadrante3(imagem);
		JOptionPane.showMessageDialog(null, "Mediana das tonalidades de cinza do Quandrante 3 é " + mediana);
	}

	/**
	 * d) Calcule a moda das tonalidades de cinza do quadrante 4.
	 */
	public static void calculaModaQuadrante4(Imagem imagem) {
		Integer moda = EstatisticasUtils.calculaModaQuadrante4(imagem);
		JOptionPane.showMessageDialog(null, "Moda das tonalidades de cinza do Quandrante 4 é " + moda);
	}

	/**
	 * e) Calcule a variância das tonalidades de cinza dos quadrantes 1 e 2.
	 */
	public static void calculaVarianciaQuadrante12(Imagem imagem) {
		Integer varianciaQ1 = EstatisticasUtils.calcularVarianciaQ1(imagem);
		Integer varianciaQ2 = EstatisticasUtils.calcularVarianciaQ2(imagem);

		JOptionPane.showMessageDialog(null, "Variância das tonalidades de cinza do Quandrante 1 é " + varianciaQ1 + " e do Quadrante 2 é " + varianciaQ2);
	}

	/**
	 * g) Informe quantos pixels com tonalidade superior a 150 tem na metade inferior da imagem.
	 */
	public static void exibirQuantidadePixelsInferior(Imagem imagem) {
		int quantidade = EstatisticasUtils.calculaMetadeInferior(imagem);

		JOptionPane.showMessageDialog(null, "Na metade inferior da imagem existem " + quantidade + " pixels com tonalidade superior a 150");
	}

	public static void exibirQuantidadePixelsSuperior(Imagem imagem) {
		int quantidade = EstatisticasUtils.calculaMetadeSuperior(imagem);

		JOptionPane.showMessageDialog(null, "Na metade superior da imagem existem " + quantidade + " pixels com tonalidade superior a 150");

	}

}