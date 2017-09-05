package com.iceberg.janelas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaTranslacao extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JanelaPrincipal jp;
	
	public JanelaTranslacao(JanelaPrincipal jp) {
		this.jp = jp;
		montaTela();
	}
	
	public void montaTela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JTextField txtX = new JTextField(5);
		JTextField txtY = new JTextField(5);
		
		add(new JLabel("X: "));
		add(txtX);
		add(new JLabel("Y: "));
		add(txtY);
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jp.trocaImagem(jp.getImagem().transladar(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())));
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
