package com.iceberg.janelas.editar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.iceberg.janelas.JanelaPrincipal;

public class JanelaRotacao extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JanelaPrincipal jp;
	
	public JanelaRotacao(JanelaPrincipal jp) {
		this.jp = jp;
		montaTela();
	}
	
	public void montaTela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JCheckBox antiHorario = new JCheckBox();
		JSpinner angulo = new JSpinner(new SpinnerNumberModel(0,0,360,90));
		
		angulo.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				jp.trocaImagem(jp.getImagem().rotacionar((int) angulo.getValue(),  antiHorario.isSelected()), false);
			}
			
		});
		add(new JLabel("Ângulo:"));
		add(angulo);
		
		add(new JLabel("Anti-Horário:"));
		add(antiHorario);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnAplicar = new JButton("Aplicar");
		JButton btnCancelar = new JButton("Cancelar");
		
		btnAplicar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jp.trocaImagem(jp.getImagem().rotacionar((int) angulo.getValue(), antiHorario.isSelected()));
				jp.fechaTelaConfig();
			}
			
		});
		panel.add(btnAplicar);
		
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				jp.restauraImagem();
				jp.fechaTelaConfig();
			}
			
		});
		panel.add(btnCancelar);
	}

}
