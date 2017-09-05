package com.iceberg.janelas;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import com.iceberg.sistema.Imagem;

public class JanelaHistogramaQuadrante_1 extends Janela {
	private static final long serialVersionUID = 1L;

	public JanelaHistogramaQuadrante_1() {}
	
	public JanelaHistogramaQuadrante_1( Imagem imagem ) {
		if( imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY ) {
			imagem = Imagem.getCopiaEscalaCinza( imagem );
			
			int linha = imagem.getLargura() / 2;
			int coluna = imagem.getAltura() / 2;
			
			Integer quadrante1[][] = new Integer[ linha ][ coluna ];
			
			for( int x = 0; x < linha; x++ ){
				for( int y = 0; y < coluna; y++ ){
					quadrante1[ x ][ y ] = imagem.getTomCinza( x, y );
					
				}
				
			}
			
			double[] vetor = new double[ linha * coluna ];
			
			int count = 0;
			
			for( int x = 0; x < linha; x++ ){
				for( int y = 0; y < coluna; y++ ){
					vetor[ count ] = (double) quadrante1[ x ][ y ];
					count++;
					
				}
				
			}
			
			// Definindo dados para colocar no Histograma
			HistogramDataset dataset = new HistogramDataset();
			dataset.addSeries("Quantidade de pixels", vetor, 256, 0, 256);
			
			JFreeChart histograma = ChartFactory.createHistogram("Histograma", "Tonalidade P&B", "Quantidade de Pixels", dataset, PlotOrientation.VERTICAL, false, true, true);
			
			getContentPane().removeAll();	// Impede que os graficos se sobreponham 
			getContentPane().add(new ChartPanel(histograma), BorderLayout.CENTER);
			
			MontaJanela(500, 500);
		
		}
		
	}
	
}
