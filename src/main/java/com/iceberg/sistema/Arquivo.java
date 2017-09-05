package com.iceberg.sistema;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class Arquivo {

	private File arquivo;
	private boolean salvo;
	
	/*
	 * Construtor
	 * */
	public Arquivo() {}
	
	/*
	 * Abre caixa de diálogo abrir/salvar arquivo
	 * */
	private JFileChooser caixaDeDialogo(String mode){
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(FILTRO_IMAGENS);
		
		if(mode == "open" || mode == "abrir"){
			fileChooser.setDialogTitle("Abrir");
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				return fileChooser;
		}else if(mode == "save" || mode == "salvar"){
			fileChooser.setSelectedFile(arquivo);
			fileChooser.setDialogTitle("Salvar");
			if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
				return fileChooser;
		}
		
		return null;
	}

	public File abrir(){
		JFileChooser cd = caixaDeDialogo("abrir");
		return arquivo = cd.getSelectedFile();
	}
	
	public void salvarComo(Imagem imagem){
		
		try {
			
			JFileChooser fc = caixaDeDialogo("salvar");
			File arquivoDestino = fc.getSelectedFile();
			
			if(arquivoDestino.exists() && JOptionPane.showConfirmDialog(null, "Deseja sobreescrever o arquivo?", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
				return;
			}
			
			ImageIO.write(imagem.getBufferedImage(), getFormatoArquivo(arquivoDestino.getName()), arquivoDestino);
			
			this.arquivo = arquivoDestino;
			setSalvo(true);
			
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Falha ao salvar arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void salvar(Imagem imagem){
		
		if(!isSalvo())
			salvarComo(imagem);
		
		try {
			ImageIO.write(imagem.getBufferedImage(), getFormatoArquivo(arquivo.getName()), arquivo);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao salvar arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	public boolean isSalvo(){
		return this.salvo;
	}
	
	private void setSalvo(boolean salvo){
		this.salvo = salvo;
	}

	private String getFormatoArquivo(String nomeArquivo){
		return nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
	}

	/*
	 * Define os tipos de arquivos que são aceitos pelo programa
	 * */
	private static FileFilter FILTRO_IMAGENS = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			return file.isDirectory() ||
	        		file.getName().toLowerCase().endsWith(".bmp") ||
	        		file.getName().toLowerCase().endsWith(".jpg") ||
	        		file.getName().toLowerCase().endsWith(".png");
		}
		
		@Override
	    public String getDescription() {
			return "Arquivos de imagem (BMP, JPG ou PNG)";
	    }
	};
}
