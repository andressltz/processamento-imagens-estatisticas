package com.iceberg.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

public abstract class Janela extends JFrame{
	private static final long serialVersionUID = 1L;
	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Janela() {}
	
	public void MontaJanela(int largura, int altura, boolean exitOnClose){
		setSize(new Dimension(largura,altura));
		if(exitOnClose){
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		centralizarJanela(this);
		setVisible(true);
		
	}
	
	public void MontaJanela(int largura, int altura){
		MontaJanela(largura, altura, false);
		
	}
	
	public static void centralizarJanela(Window frame) {
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    
	}

}
