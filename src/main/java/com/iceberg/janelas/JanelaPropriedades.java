package com.iceberg.janelas;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.iceberg.sistema.Arquivo;
import com.iceberg.sistema.Imagem;

public class JanelaPropriedades extends Janela{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblInformacoes;
	
	public JanelaPropriedades(Arquivo arquivo, Imagem imagem) {
		
		String[] colunas = {"Propriedade", "Descrição"};
		Object[][] dados = {
				{"Diretório", arquivo.getArquivo().getAbsolutePath()},
				{"Tamanho",   arquivo.getArquivo().length()},
				{"Largura",   imagem.getLargura() + "px"},
				{"Altura",    imagem.getAltura() + "px"},
				{"Média",     imagem.getMedia()},
				{"Moda",      imagem.getModa()},
				{"Mediana",   imagem.getMediana()},
				{"Variância", imagem.getVariancia()}
				
		};

		tblInformacoes = new JTable(dados, colunas);
		
		JScrollPane jsp = new JScrollPane(tblInformacoes);
		
		tblInformacoes.setFillsViewportHeight(true);
		tblInformacoes.setEnabled(false);		
		
		getContentPane().add(jsp);
		
		MontaJanela(600, 300);
	}

}
