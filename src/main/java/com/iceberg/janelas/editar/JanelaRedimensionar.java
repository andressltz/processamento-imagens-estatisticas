package com.iceberg.janelas.editar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iceberg.janelas.JanelaPrincipal;

public class JanelaRedimensionar extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JanelaPrincipal jp;
	
	public JanelaRedimensionar(JanelaPrincipal jp) {
		this.jp = jp;
		montaTela();
	}
	
	private void montaTela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JTextField txtLargura = new JTextField("1");
		JTextField txtAltura = new JTextField("1");
		
		add(new JLabel("Valores devem ser maiores que 0"));
		
		add(new JLabel("Escala X:"));
		add(txtLargura);
		add(new JLabel("Escala Y:"));
		add(txtAltura);
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jp.trocaImagem(jp.getImagem().redimensionar(Double.parseDouble(txtLargura.getText()), Double.parseDouble(txtAltura.getText())));
				
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
