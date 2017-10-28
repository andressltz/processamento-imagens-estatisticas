package com.iceberg.janelas.histograma;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import com.iceberg.model.Imagem;
import com.iceberg.view.Janela;

public class JanelaHistograma extends Janela{
	private static final long serialVersionUID = 1L;

	public JanelaHistograma(){}
	
	public JanelaHistograma(Imagem imagem) {
		if(imagem.getBufferedImage().getType() != BufferedImage.TYPE_BYTE_GRAY){
			imagem = Imagem.getCopiaEscalaCinza(imagem);
			
		}
		
		double[] vetor = Imagem.criaVetorImagem(imagem);
		
		// Definindo dados para colocar no Histograma
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Quantidade de pixels", vetor, 256, 0, 256);
		
		JFreeChart histograma = ChartFactory.createHistogram("Histograma", "Tonalidade P&B", "Quantidade de Pixels", dataset, PlotOrientation.VERTICAL, false, true, true);
		
		getContentPane().removeAll();	// Impede que os gráficos se sobreponham 
		getContentPane().add(new ChartPanel(histograma), BorderLayout.CENTER);
		
		MontaJanela(500, 500);
		
	}
	
}
