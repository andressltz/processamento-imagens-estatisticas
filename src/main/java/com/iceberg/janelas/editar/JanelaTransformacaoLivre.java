package com.iceberg.janelas.editar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iceberg.view.JanelaPrincipal;

public class JanelaTransformacaoLivre extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JanelaPrincipal jp;
	
	public JanelaTransformacaoLivre(JanelaPrincipal jp) {
		this.jp = jp;
		montaTela();
	}
	
	public void montaTela(){
		setLayout(new GridLayout(0,1,0,0));
		
		JPanel matriz = new JPanel();
		matriz.setLayout(new GridLayout(3,3,2,2));
		
		JTextField txtValue1 = new JTextField();
		JTextField txtValue2 = new JTextField();
		JTextField txtValue3 = new JTextField();
		
		JTextField txtValue4 = new JTextField();
		JTextField txtValue5 = new JTextField();
		JTextField txtValue6 = new JTextField();
		
		JTextField txtValue7 = new JTextField();
		JTextField txtValue8 = new JTextField();
		JTextField txtValue9 = new JTextField();
		
		
		txtValue1.setText("1");
		txtValue2.setText("0");
		txtValue3.setText("90");
		
		txtValue4.setText("0");
		txtValue5.setText("1");
		txtValue6.setText("45");
		
		txtValue7.setText("0");
		txtValue8.setText("0");
		txtValue9.setText("1");
		
		matriz.add(txtValue1);
		matriz.add(txtValue2);
		matriz.add(txtValue3);
		
		matriz.add(txtValue4);
		matriz.add(txtValue5);
		matriz.add(txtValue6);
		
		matriz.add(txtValue7);
		matriz.add(txtValue8);
		matriz.add(txtValue9);
		
		
		add(matriz);
		
		JPanel panel = new JPanel();
		add(panel);
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double[][] matrizMult = {
						{Double.parseDouble(txtValue1.getText()),Double.parseDouble(txtValue2.getText()),Double.parseDouble(txtValue3.getText())},
						{Double.parseDouble(txtValue4.getText()),Double.parseDouble(txtValue5.getText()),Double.parseDouble(txtValue6.getText())},
						{Double.parseDouble(txtValue7.getText()),Double.parseDouble(txtValue8.getText()),Double.parseDouble(txtValue9.getText())}
				};
								
				jp.trocaImagem(jp.getImagem().multiplicaMatriz(matrizMult));
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
