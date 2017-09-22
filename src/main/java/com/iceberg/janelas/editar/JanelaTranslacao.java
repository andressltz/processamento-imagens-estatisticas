package com.iceberg.janelas.editar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iceberg.janelas.JanelaPrincipal;

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
		
		JTextField txtX = new JTextField("0", 5);
		JTextField txtY = new JTextField("0", 5);
		
		add(new JLabel("X: "));
		add(txtX);
		add(new JLabel("Y: "));
		add(txtY);
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trataNull(txtX.getText());
				trataNull(txtY.getText());
				jp.trocaImagem(jp.getImagem().transladar(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())));
				jp.fechaTelaConfig();
			}

			private void trataNull(String text) {
				if (null == text || text.trim().isEmpty()) {
					text = "0";
				}

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
