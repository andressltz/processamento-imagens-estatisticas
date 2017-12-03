package com.iceberg.janelas.editar;

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

public class JanelaContraste extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JanelaPrincipal jp;

	float min, max, range;
    final static int SLIDER_MIN = 0;
    final static int SLIDER_MAX = 10;
    final static float SLIDER_RANGE = SLIDER_MAX - SLIDER_MIN;
    
    protected Imagem tmpImagem;
    protected int sliderValue;
    
	public JanelaContraste(JanelaPrincipal jp) {
		this.jp = jp;
		montaJanela();
	}

	public void montaJanela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JSlider sliderContraste = new JSlider(SLIDER_MIN, SLIDER_MAX);
		sliderContraste.setValue(1);
		sliderContraste.addChangeListener(new ChangeListener(){
					
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				
				tmpImagem = jp.getImagem().getCopia();
				sliderValue = source.getValue();
				jp.trocaImagem(EfeitosService.contraste(tmpImagem, sliderValue).getBufferedImage(), false);
			}
			
		});
		add(new JLabel("Contraste: "));
		add(sliderContraste);
			
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				tmpImagem = jp.getImagem().getCopia();
				jp.trocaImagem(EfeitosService.contraste(tmpImagem, sliderValue).getBufferedImage());
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
