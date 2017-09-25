package com.iceberg.janelas.histograma;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

import com.iceberg.janelas.Janela;
import com.iceberg.sistema.Imagem;

public class JanelaTonalidadeMenor100 extends Janela {
	private static final long serialVersionUID = 1L;
	
	public JanelaTonalidadeMenor100( Imagem imagem ){
		if( imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY ) {
			imagem = Imagem.getCopiaEscalaCinza( imagem );
			
		}
		
		int linha = imagem.getLargura() / 2;
		int coluna = imagem.getAltura();
		
		Long qtPixelsMenor100 = 0L;
		
		for( int x = 0; x < linha; x++ ){
			for( int y = 0; y < coluna; y++ ){
				if( imagem.getTomCinza( x, y ) > 0 && imagem.getTomCinza( x, y ) < 100 ){
					qtPixelsMenor100++;
					
				}
				
			}
			
		}
		
		JOptionPane.showMessageDialog( null, "Quantidade total de pixels com valores menor do que 100: " + qtPixelsMenor100 );
		
	}
	
}
