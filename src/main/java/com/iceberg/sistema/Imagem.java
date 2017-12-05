package com.iceberg.sistema;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Imagem {
	private BufferedImage bufferedImage;
	private Integer media;
	private Integer moda;
	private Integer mediana;
	private Integer variancia;
	private int[] vetorOrdenado;
	
	public Imagem(File arquivo) {
		carregarImagem(arquivo);
		
	}
	
	public Imagem(BufferedImage bi){
		this.bufferedImage = bi;
		
	}

	public void carregarImagem(File arquivo){
		try {
			this.bufferedImage = ImageIO.read(arquivo);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao abrir imagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public BufferedImage getBufferedImage(){
		return this.bufferedImage;
		
	}
	
	public void calcularMedia() {
		Integer soma = 0;
		Integer coluna, linha;
		Integer altura = getAltura();
		Integer largura = getLargura();
		
		for(coluna = 0; coluna < largura; coluna++){
			for(linha = 0; linha < altura; linha++){
				soma += getTomCinza(coluna, linha);
				
			}
			
		}
		
		media = soma / (largura * altura);
		
	}
	
	public void calcularModa(){
		ordenarVetor();
		int moda = vetorOrdenado[0];
		int qtdModa = 0;
		int qtdAtual = 1;
		
		for( int i = 1; i <= vetorOrdenado.length; i++ ) {
			if( i < vetorOrdenado.length && vetorOrdenado[i - 1] == vetorOrdenado[i] ) {
				qtdAtual++;
				
			} else {
				if(qtdAtual > qtdModa) {
					moda = vetorOrdenado[i - 1];
					qtdModa = qtdAtual;
					
				}
				
				qtdAtual = 1;
				
			}
			
		}
		
		this.moda = moda;
		
	}
	
	public void calcularMediana(){
		ordenarVetor();
		mediana = vetorOrdenado[ Math.round( vetorOrdenado.length / 2 ) ];
		
	}
	
	public void calcularVariancia(){
		int soma = 0;
		int diff;

		int altura = getAltura();
		int largura = getLargura();
		
		for( int coluna = 0; coluna < largura; coluna++ ) {
			for( int linha = 0; linha < altura; linha++ ) {
				diff = getTomCinza( coluna, linha ) - getMedia();
				soma += diff * diff;
				
			}
			
		}
		
		variancia = soma / (largura * altura);
		
	}
	
	public Integer getLargura() {
		return this.bufferedImage.getWidth();
		
	}

	public Integer getAltura() {
		return this.bufferedImage.getHeight();
		
	}
	
	public Integer getMedia() {
		if(media == null){
			calcularMedia();
		}
		
		return media;
		
	}

	public Integer getModa() {
		if(moda == null){
			calcularModa();
		}
		
		
		return moda;
		
	}

	public Integer getMediana() {
		if(mediana == null){
			calcularMediana();
		}
		
		return mediana;
		
	}

	public Integer getVariancia() {
		if(variancia == null){
			calcularVariancia();
		}
		
		return variancia;
		
	}
	
	public int getTomCinza( int x, int y ) {
		return ( bufferedImage.getRGB( x, y ) >> 16 ) & 0xFF;
		
	}
	
	public void setTomCinza( int x, int y, int tomCinza ) {
		bufferedImage.setRGB( x, y, tomCinza * 0x00010101 );
		
	}

	private void ordenarVetor(){
		if(vetorOrdenado != null){
			return;
		}
		
		Integer altura = getAltura();
		Integer largura = getLargura();
		
		vetorOrdenado = new int[altura * largura];
		
		for(int coluna = 0; coluna < largura; coluna++){
			for(int linha = 0; linha < altura; linha++){
				vetorOrdenado[coluna * altura + linha] = getTomCinza(coluna, linha);
				
			}
			
		}
		
		Arrays.sort(vetorOrdenado);
		
	}	
	
	public static double[] criaVetorImagem(Imagem imagem){
		double[] vetor = new double[imagem.getLargura() * imagem.getAltura()];
		
		for(int coluna = 0; coluna < imagem.getLargura(); coluna++){
			for(int linha = 0; linha < imagem.getAltura(); linha++) {
				vetor[coluna * imagem.getAltura() + linha] = imagem.getTomCinza(linha, coluna);
				
			}
			
		}
		
		return vetor;
		
	}
	
	public static Imagem getCopiaEscalaCinza(Imagem imagem) {
		return new Imagem(getCopiaEscalaCinza(imagem.getBufferedImage()));
		
	}
	
	public static BufferedImage getCopiaEscalaCinza(BufferedImage bufferedImage) {
		BufferedImage imagemCinza = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = imagemCinza.getGraphics();
		g.drawImage(bufferedImage, 0, 0, null);
		g.dispose();
		
		return imagemCinza;
		
	}
	
	public void converteParaTonsDeCinza() {
		if(bufferedImage.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			bufferedImage = getCopiaEscalaCinza(bufferedImage);
			
		}	
		
	}

	public BufferedImage espelhamentoVertical() {
		int altura = bufferedImage.getHeight();
		
		double[][] matrizMult = new double[][] {
				{ 1, 0, 0 },
				{ 0, -1, 0 },
				{ 0, altura - 1, 1}
			};
		
		return this.bufferedImage = multiplicaMatriz(matrizMult);
		
	}

	public BufferedImage espelhamentoHorizontal() {
		int largura = bufferedImage.getWidth();
		
		double[][] matrizMult = new double[][] {
				{ -1, 0, 0 },
				{ 0, 1, 0 },
				{ largura - 1, 0, 1}
			};
		
		return this.bufferedImage = multiplicaMatriz(matrizMult);
		
	}

	public BufferedImage redimensionar( double scalaX, double scalaY ) {
		int largura = ( int ) ( scalaX * bufferedImage.getWidth() );
		int altura = ( int ) ( scalaY * bufferedImage.getHeight() );
		
		BufferedImage resultado = new BufferedImage( largura, altura, bufferedImage.getType() != BufferedImage.TYPE_CUSTOM ? bufferedImage.getType() : BufferedImage.TYPE_INT_ARGB );

		for( int xDest = 0; xDest < largura; xDest++ ) {
			int x = ( int )( xDest / scalaX );
			
			for( int yDest = 0; yDest < altura; yDest++ ) {
				int y = ( int )( yDest / scalaY );
				
				resultado.setRGB( xDest, yDest, bufferedImage.getRGB( x, y ) );
				
			}
			
		}
		
		return this.bufferedImage = resultado;
		
	}
	
	public BufferedImage transladar( int x, int y ){
		double[][] matrizMult = {
				{1,0,x},
				{0,1,y},
				{0,0,1}
				
		};
		
		return this.bufferedImage = multiplicaMatriz( matrizMult );
		
	}
	
	public BufferedImage rotacionar(int angulo) {
		if(angulo == 0 || angulo == 360) {
			return bufferedImage;
		}
		
		int largura = bufferedImage.getWidth();
		int altura = bufferedImage.getHeight();
		int deslocX = altura - 1;
		int deslocY = largura - 1;
		
		if( angulo == 90 ) {
			largura = bufferedImage.getHeight();
			altura = bufferedImage.getWidth();
			deslocX = 0;
			deslocY = altura - 1;
			
		} else if( angulo == 180 ) {
			deslocX = largura - 1;
			deslocY = altura - 1;
			
		} else if( angulo == 270 ) {
			largura = bufferedImage.getHeight();
			altura = bufferedImage.getWidth();
			deslocX = largura - 1;
			deslocY = 0;
		}

		double[][] matrizMult = new double[][] { 
				{ Math.cos( Math.toRadians( angulo ) ), -Math.sin( Math.toRadians( angulo ) ), 0 },
				{ Math.sin( Math.toRadians( angulo ) ), Math.cos( Math.toRadians( angulo ) ), 0 },
				{ deslocX, deslocY, 1 }
				
			};
		
		return multiplicaMatriz( largura, altura, matrizMult );
		
	}
	
	public BufferedImage multiplicaMatriz(double[][] matrizMult) {
		return multiplicaMatriz(bufferedImage.getWidth(), bufferedImage.getHeight(), matrizMult);
	}
	
	public BufferedImage multiplicaMatriz(int larguraImg, int alturaImg, double[][] matrizMult) {
		int posx = (int) ((matrizMult[0][2]<0) ? matrizMult[0][2]*-1 : matrizMult[0][2]);
		int posy = (int) ((matrizMult[1][2]<0) ? matrizMult[1][2]*-1 : matrizMult[1][2]);
		int largura = posx + larguraImg;
		int altura = posy + alturaImg;
		
		BufferedImage resultado = new BufferedImage(largura, altura, bufferedImage.getType() != BufferedImage.TYPE_CUSTOM ? bufferedImage.getType() : BufferedImage.TYPE_INT_ARGB);
		int z = 1;
		
		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {

				int xDest = (int) Math.round((x * matrizMult[0][0]) + (y * matrizMult[1][0]) + (z * matrizMult[2][0]));
				int yDest = (int) Math.round((x * matrizMult[0][1]) + (y * matrizMult[1][1]) + (z * matrizMult[2][1]));
				
				
				if(matrizMult[0][2] > 0)
					xDest += matrizMult[0][2];
				if(matrizMult[1][2] < 0)
					yDest += matrizMult[1][2]*-1;
				
				resultado.setRGB(xDest, yDest, bufferedImage.getRGB(x, y));
			}
		}
		
		return this.bufferedImage = resultado;
	}
	
	public Imagem getCopia(){
		int largura = bufferedImage.getWidth();
		int altura = bufferedImage.getHeight();
		
		BufferedImage resultado = new BufferedImage(largura, altura, bufferedImage.getType() != BufferedImage.TYPE_CUSTOM ? bufferedImage.getType() : BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				resultado.setRGB(x, y, bufferedImage.getRGB(x, y));
			}
		}
		
		return new Imagem(resultado);
	}
	
	public int verificaRGB(int valor){
		int tmp = 0;
		
		if(valor > 255){
			tmp = 255;
		} else if(valor < 0){
			tmp = 0;
		}
		
		return tmp;
	}
	
	public void setBrilho(int x, int y, int brilho){
		setBrilho(x,y,brilho,1);
	}
	
	public void setBrilho(int x, int y, int brilho, float contraste){
		Color color = new Color(bufferedImage.getRGB(x, y));
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		r = Math.round((contraste * r) + brilho);
		if(r > 255){
			r = 255;
		}else if(r < 0){
			r = 0;
		}
		
		g = Math.round((contraste * g) + brilho);
		if(g > 255){
			g = 255;
		}else if(g < 0){
			g = 0;
		}
		
		b = Math.round((contraste * b) + brilho);
		if(b > 255){
			b = 255;
		}else if(b < 0){
			b = 0;
		}
		
		bufferedImage.setRGB(x, y, new Color(r,g,b).getRGB());
		
	}
	
	public void setContraste(int x, int y, float contraste){
		setBrilho(x, y, 0, contraste);
	}
	
	public int[][] getPixelsTonsCinza(int xInicial, int yInicial, int xFinal, int yFinal){
		return getPixelsTonsCinza(xInicial, yInicial, xFinal, yFinal, 1);
	}
	
	public int[][] getPixelsTonsCinza(int xInicial, int yInicial, int xFinal, int yFinal, int valorMascara){
		int largura = xFinal - xInicial;
		int altura = yFinal - yInicial;
		
		int[][] matriz = new int[largura][altura];
		
		for(int x = xInicial, mX = 0; ((x < xFinal) && (mX < largura)); x++){
			
			for(int y = yInicial, mY = 0; ((y < yFinal) && (mY < altura)); y++){
					
					matriz[mX][mY] = getTomCinza(x, y) * valorMascara;
					
					mY++;
			}
			
			mX++;
		}
		
		return matriz;
	}
	
	public BufferedImage convolucao( int[][] mascara ) {
		int larguraMascara = mascara.length;
		int alturaMascara = mascara[ 0 ].length;
		
		for( int x = 0, mMascaraX = 0; ( ( x < getLargura() - larguraMascara ) && ( mMascaraX < larguraMascara ) ); x++ ) {
			for( int y = 0, mMascaraY = 0; ( ( y < getAltura() - alturaMascara ) && ( mMascaraY < alturaMascara ) ); y++ ) {
				int[][] matriz = getPixelsTonsCinza( x, y, x + larguraMascara, y + alturaMascara, mascara[ mMascaraX ][ mMascaraY ] );
				
				setTomCinza( x + 1, y + 1, Math.round( getMediaVetor( matriz ) ) );
				
			}
			
		}
		
		return this.bufferedImage;
		
	}

	public void imprimeVetor( int[][] matriz ) {
		for( int x = 0; x < matriz.length; x++ ) {
			for( int y = 0; y < matriz[ 0 ].length; y++ ) {
				System.out.print( matriz[ x ][ y ] + "\t" );
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
	}
	
	public float getMediaVetor( int[][] matriz ) {
		float media = 0;
		int soma = 0;
		
		for( int x = 0; x < matriz.length; x++ ) {
			for( int y = 0; y < matriz[ 0 ].length; y++ ) {
				soma += matriz[ x ][ y ];
				
			}
			
		}
		
		media = soma / ( matriz.length * matriz[ 0 ].length );
		
		return media;
		
	}
	
	public BufferedImage limiarizacao( int limiar ) {
		for( int x = 0; x < getLargura(); x++ ) {
			for( int y = 0; y < getAltura(); y++ ) {
				int pixelCinza = getTomCinza( x, y );
				System.out.println( "Tom cinza: " + pixelCinza );
				
				if( limiar >= pixelCinza ) {
					setTomCinza( x, y, 255 );
					
				} else if( limiar <= pixelCinza ) {
					setTomCinza( x, y, 0 );
					
				}
				
			}	
			
		}
		
		return this.bufferedImage;
		
	}
	
	public BufferedImage deteccaoDeBordas( int[][] xKernel, int[][] yKernel, int threshold ) {
		int width = getLargura();
		int height = getAltura();
		int widthM1 = width - 1;
		int heightM1 = height - 1;
		int i, j;
		double v, gx, gy, g = 0;
		
		for( int y = 1; y < heightM1; y++ ) {
			for( int x = 1; x < widthM1; x++ ) {
				gx = gy = 0;
				for( i = 0; i < 3; i++ ) {
					for( j = 0; j < 3; j++ ) {
						v = getTomCinza( x + ( i - 1 ), y + ( j - 1 ) );
						gx += v * xKernel[ i ][ j ];
						gy += v * yKernel[ i ][ j ];
						
					}	
					
				}
				
				g = Math.sqrt( Math.pow( gx, 2 ) + Math.pow( gy, 2 ) );
				
				int p = 0;
				
				if( g > threshold ) {
					p = 255;
				}
				
				setTomCinza( x, y, p );
				
			}
			
		}
		
		return this.bufferedImage;
		
	}

}
