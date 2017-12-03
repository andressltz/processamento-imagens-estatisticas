package com.iceberg.services;

import java.awt.image.BufferedImage;

import com.iceberg.model.Imagem;

public class EfeitosService {

	public static Imagem brilho(Imagem imagem, int brilho) {
		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				imagem.setBrilho(coluna, linha, brilho);
			}
		}

		return imagem;
	}

	public static Imagem contraste(Imagem imagem, float contraste) {
		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				imagem.setContraste(coluna, linha, contraste);
			}
		}

		return imagem;
	}

	public static Imagem maioresMediaBranco(Imagem imagem) {
		imagem.converteParaTonsDeCinza();
		int tom;

		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom >= imagem.getMedia() ? 255 : tom);
			}
		}

		return imagem;
	}

	public static Imagem maioresModaPreto(Imagem imagem) {
		imagem.converteParaTonsDeCinza();
		int tom;

		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom >= imagem.getModa() ? 0 : tom);
			}
		}

		return imagem;
	}

	public static Imagem medianaQuadrante3(Imagem imagem) {
		imagem.converteParaTonsDeCinza();
		int tom;

		int linha = (imagem.getAltura() / 2);

		for (int coluna = 0; coluna < (imagem.getLargura() / 2); coluna++) {
			for (int x = linha; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom >= imagem.getMediana() ? 140 : tom);

			}

		}

		return imagem;

	}

	public static Imagem menoresMedia255(Imagem imagem) {
		imagem.converteParaTonsDeCinza();
		int tom;

		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom < imagem.getMedia() ? 255 : tom);
			}
		}

		return imagem;
	}

	public static Imagem maioresMediana0MenoresMedia255(Imagem imagem) {
		imagem.converteParaTonsDeCinza();

		int tom;
		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				if (imagem.getTomCinza(coluna, linha) > imagem.getMediana())
					tom = 0;
				else if (imagem.getTomCinza(coluna, linha) < imagem.getMedia())
					tom = 255;

				imagem.setTomCinza(coluna, linha, tom);
			}
		}

		return imagem;
	}

	public static Imagem converteParaTonsDeCinza(Imagem imagem) {
		if (imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY) {
			return Imagem.getCopiaEscalaCinza(imagem);
		}

		return imagem;
	}

	// a) Valores maiores ou iguais a média do quadrante 2 recebem branco.<br>
	public static BufferedImage mediaQuadrante2Branco(Imagem imagem) {
		Integer quadrante2[][] = EstatisticasService.calculaQuadrante2(imagem);
		int media = EstatisticasService.calculaMedia(imagem);

		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				int tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom >= imagem.getMediana() ? 220 : tom);
			}
		}

		// TODO fazer for e se for igual ou maior recebe branco
		// TODO tranformar essa matriz em buffer e retornar
		return null;
	}


	public static BufferedImage maioresMedianaQuadrante3Recebe220(Imagem imagem) {
		imagem.converteParaTonsDeCinza();
		int tom;

		for (int coluna = 0; coluna < imagem.getLargura(); coluna++) {
			for (int linha = 0; linha < imagem.getAltura(); linha++) {
				tom = imagem.getTomCinza(coluna, linha);
				imagem.setTomCinza(coluna, linha, tom >= imagem.getMediana() ? 220 : tom);
			}
		}

		return imagem.getBufferedImage();
	}


}
