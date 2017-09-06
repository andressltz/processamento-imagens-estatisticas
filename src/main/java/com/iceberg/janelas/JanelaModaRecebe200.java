package com.iceberg.janelas;

import java.awt.Label;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import com.iceberg.sistema.Imagem;

public class JanelaModaRecebe200 extends Janela {
	private static final long serialVersionUID = 1L;
	private int altura, largura;
	private int[] vetorOrdenado;
	private Integer moda = 0;

	public JanelaModaRecebe200( Imagem imagem ) {
		calculaQuadrante4( imagem );
		moda = calculaModa( imagem );
		
		getContentPane().removeAll();
		getContentPane().add( new Label( "Moda das tonalidades de cinza do Quandrante 4 é " + moda ) );
		
		sobreescreveModa( imagem );

		MontaJanela( 500, 150 );
		
	}

	private Integer[][] sobreescreveModa( Imagem imagem ) {
		if( imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY ) {
			imagem = Imagem.getCopiaEscalaCinza( imagem );

			largura = ( imagem.getLargura() / 2 ) + 1;
			altura = ( imagem.getAltura() / 2 ) + 1;

			Integer quadrante4[][] = new Integer[ largura ][ altura ];

			for( int x = 0; x < largura; x++ ) {
				for( int y = 0; y < altura; y++ ) {
					if( imagem.getTomCinza( x, y ) >= moda ){
						quadrante4[ x ][ y ] = 200;
						
					} else {
						quadrante4[ x ][ y ] = imagem.getTomCinza( x, y );
						
					}
					
				}
				
			}
			
			return quadrante4;
			
		}

		return null;
		
	}

	private Integer calculaModa( Imagem imagem ) {
		ordenarVetor( imagem );
		int moda = vetorOrdenado[ 0 ];
		int qtdModa = 0;
		int qtdAtual = 1;

		for( int i = 1; i <= vetorOrdenado.length; i++ ) {
			if ( i < vetorOrdenado.length && vetorOrdenado[ i - 1 ] == vetorOrdenado[ i ] ) {
				qtdAtual++;

			} else {
				if ( qtdAtual > qtdModa ) {
					moda = vetorOrdenado[ i - 1 ];
					qtdModa = qtdAtual;

				}

				qtdAtual = 1;

			}

		}

		return moda;
		
	}

	private Integer[][] calculaQuadrante4(Imagem imagem) {
		if( imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY ) {
			imagem = Imagem.getCopiaEscalaCinza( imagem );

			largura = ( imagem.getLargura() / 2 ) + 1;
			altura = ( imagem.getAltura() / 2 ) + 1;

			Integer quadrante4[][] = new Integer[ largura ][ altura ];

			for( int x = 0; x < largura; x++ ) {
				for( int y = 0; y < altura; y++ ) {
					quadrante4[ x ][ y ] = imagem.getTomCinza( x, y );
					
				}
				
			}
			
			return quadrante4;
			
		}

		return null;
		
	}

	private void ordenarVetor( Imagem imagem ) {
		if( vetorOrdenado != null ) {
			return;
			
		}

		vetorOrdenado = new int[ altura * largura ];

		for( int coluna = 0; coluna < largura; coluna++ ) {
			for( int linha = 0; linha < altura; linha++ ) {
				vetorOrdenado[ coluna * altura + linha ] = imagem.getTomCinza( coluna, linha );

			}

		}

		Arrays.sort( vetorOrdenado );

	}
	
}
