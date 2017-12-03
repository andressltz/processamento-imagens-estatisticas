package com.iceberg.controller.estatisticas;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.iceberg.model.Imagem;
import com.iceberg.services.EfeitosService;
import com.iceberg.services.EstatisticasService;

public class EstatisticasCalculoController {

	/**
	 * b) Calcule a média das tonalidades de cinza do quadrante 2.
	 */
	public static void calculaMediaQuadrante2(Imagem imagem) {
		Integer media = EstatisticasService.calculaMediaQuadrante2(imagem);
		JOptionPane.showMessageDialog(null, "Média das tonalidades de cinza do Quandrante 2 é " + media);
	}

	/**
	 * c) Calcule a mediana do quadrante 3
	 */
	public static void calculaMedianaQuadrante3(Imagem imagem) {
		Integer mediana = EstatisticasService.calculaMedianQuadrante3(imagem);
		JOptionPane.showMessageDialog(null, "Mediana das tonalidades de cinza do Quandrante 3 é " + mediana);
	}

	/**
	 * d) Calcule a moda das tonalidades de cinza do quadrante 4.
	 */
	public static void calculaModaQuadrante4(Imagem imagem) {
		Integer moda = EstatisticasService.calculaModaQuadrante4(imagem);
		JOptionPane.showMessageDialog(null, "Moda das tonalidades de cinza do Quandrante 4 é " + moda);
	}

	/**
	 * e) Calcule a variância das tonalidades de cinza dos quadrantes 1 e 2.
	 */
	public static void calculaVarianciaQuadrante12(Imagem imagem) {
		Integer varianciaQ1 = EstatisticasService.calcularVarianciaQ1(imagem);
		Integer varianciaQ2 = EstatisticasService.calcularVarianciaQ2(imagem);

		JOptionPane.showMessageDialog(null, "Variância das tonalidades de cinza do Quandrante 1 é " + varianciaQ1 + " e do Quadrante 2 é " + varianciaQ2);
	}

	/**
	 * f) Informe quantos pixels com tonalidade inferior a 100 tem na metade superior da imagem.
	 */
	public static void exibirQuantidadePixelsSuperior(Imagem imagem) {
		int quantidade = EstatisticasService.calculaMetadeSuperior(imagem);

		JOptionPane.showMessageDialog(null, "Na metade superior da imagem existem " + quantidade + " pixels com tonalidade inferior a 100");
	}

	/**
	 * g) Informe quantos pixels com tonalidade superior a 150 tem na metade inferior da imagem.
	 */
	public static void exibirQuantidadePixelsInferior(Imagem imagem) {
		int quantidade = EstatisticasService.calculaMetadeInferior(imagem);

		JOptionPane.showMessageDialog(null, "Na metade inferior da imagem existem " + quantidade + " pixels com tonalidade superior a 150");
	}

	/**
	 * a) Valores maiores ou iguais a média do quadrante 2 recebem branco
	 */
	public static BufferedImage mediaQuadrante2Branco(Imagem imagem) {
		return EfeitosService.mediaQuadrante2Branco(imagem);
	}

	/**
	 * c) Valores maiores ou iguais a mediana do quadrante 3 recebem 220.
	 */
	public static BufferedImage maioresMedianaQuadrante3Recebe220(Imagem imagem) {
		return EfeitosService.maioresMedianaQuadrante3Recebe220(imagem);
	}

}
