package com.iceberg.janelas.filtros;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.iceberg.model.Imagem;
import com.iceberg.services.EfeitosService;
import com.iceberg.view.JanelaPrincipal;


public class JanelaLimiarizacao extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JanelaPrincipal jp;

	float min, max, range;
    final static int SLIDER_MIN = 0;
    final static int SLIDER_MAX = 255;
    final static float SLIDER_RANGE = SLIDER_MAX - SLIDER_MIN;
    
    protected Imagem tmpImagem;
    protected int sliderValue;
    
	public JanelaLimiarizacao(JanelaPrincipal jp) {
		this.jp = jp;
		montaJanela();
	}

	public void montaJanela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JSlider sliderBrilho = new JSlider(SLIDER_MIN, SLIDER_MAX);
		sliderBrilho.addChangeListener(new ChangeListener(){
					
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				
				tmpImagem = jp.getImagem().getCopia();
				sliderValue = source.getValue();
				
				jp.trocaImagem(tmpImagem.limiarizacao(sliderValue), false);
			}
			
		});
		add(new JLabel("Limiar: "));
		add(sliderBrilho);
			
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				tmpImagem = jp.getImagem().getCopia();
				jp.trocaImagem(EfeitosService.brilho(tmpImagem, sliderValue).getBufferedImage());
				jp.fechaTelaConfig();
			}
		});
		panel.add(btnAplicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jp.restauraImagem();
				jp.fechaTelaConfig();
			}
		});
		panel.add(btnCancelar);
	}
	
	
	
}
