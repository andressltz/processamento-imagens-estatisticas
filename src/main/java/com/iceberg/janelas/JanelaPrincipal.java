package com.iceberg.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.iceberg.janelas.editar.JanelaBrilho;
import com.iceberg.janelas.editar.JanelaContraste;
import com.iceberg.janelas.editar.JanelaRedimensionar;
import com.iceberg.janelas.editar.JanelaRotacao;
import com.iceberg.janelas.editar.JanelaTransformacaoLivre;
import com.iceberg.janelas.editar.JanelaTranslacao;
import com.iceberg.janelas.filtros.JanelaBordas;
import com.iceberg.janelas.filtros.JanelaFiltroGaussiano;
import com.iceberg.janelas.filtros.JanelaFiltroLivre;
import com.iceberg.janelas.filtros.JanelaLimiarizacao;
import com.iceberg.janelas.histograma.JanelaHistograma;
import com.iceberg.janelas.histograma.JanelaHistogramaQuadrante_1;
import com.iceberg.janelas.histograma.JanelaTonalidadeMenor100;
import com.iceberg.janelas.media.JanelaMediaQuadrante_2;
import com.iceberg.janelas.media.JanelaModaQuadrante_4;
import com.iceberg.janelas.media.JanelaVarianciaQuadrante_12;
import com.iceberg.janelas.qntpixels.JanelaExibirQuantidadePixelsInferior;
import com.iceberg.sistema.Arquivo;
import com.iceberg.sistema.Imagem;
import com.iceberg.utils.Efeitos;

public class JanelaPrincipal extends Janela{
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBarPrincipal;
	
	// Menu Arquivo
	private JMenu menuArquivo;
	private JMenuItem submenuAbrir;
	private JMenuItem submenuSalvar;
	private JMenuItem submenuSalvarcomo;
	private JMenuItem submenuInformacoes;
	private JMenuItem submenuSair;
	
	// Menu Editar
	private JMenu menuEditar;
	private JMenuItem submenuBrilho;
	private JMenuItem submenuContraste;
	private JMenuItem submenuRotacionar;
	private JMenuItem submenuRedimensionar;
	private JMenu submenuEspelhar;
	private JMenuItem submenuVertical;
	private JMenuItem submenuHorizontal;
	private JMenuItem submenuTransladar;
	private JMenuItem submenuTransfLivre;
	private JMenuItem submenuRestOriginal;
	
	// Menu Efeitos
	private JMenu menuFiltros;
	private JMenu menuFiltrosDiversos;
	private JMenuItem menuFiltro_10;
	private JMenuItem menuFiltro_20;
	private JMenuItem menuFiltro_30;
	private JMenuItem menuFiltro_35;
	private JMenuItem menuFiltro_40;
	private JMenuItem menuFiltro_50;
	private JMenuItem menuFiltro_60;
	private JMenuItem menuFiltro_70;
	private JMenuItem menuFiltro_80;
	private JMenuItem menuFiltro_90;
	private JMenuItem menuFiltro_110;
	private JMenuItem menuFiltro_120;
	private JMenuItem menuFiltro_130;
	private JMenuItem menuFiltro_131;
	private JMenuItem submenuFiltroGaussiano;
	
	// Menu Histograma
	private JMenu menuHistograma;
	private JMenu menuHistogramaQuadrante_1;
	private JMenuItem menuExibirHistograma;
	private JMenuItem menuExibirHistogramaQuadrante1;

	// Menu Médias
	private JMenu menuMedias;
	private JMenuItem menuExibirMediaQuadrante_2;
	private JMenuItem menuExibirModaQuadrante_4;
	private JMenuItem menuExibirVarianciaQuadrante_12;
	
	// Menu quantidadePixels
	private JMenu menuQuantidadePixels;
	private JMenuItem menuExibirQtdPixelsInferior;

	// Painel lateral
	private JPanel pnlConfig;
	private JLabel lblImagem;
	private Arquivo arquivo;
	private Imagem imagem;
	
	public JanelaPrincipal() {
		carregarMenu();
		setJMenuBar(menuBarPrincipal);
		
		// Onde a imagem será exibida
		lblImagem = new JLabel();
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(new JScrollPane(lblImagem), BorderLayout.CENTER);
		
		// Painel lateral
		pnlConfig = new JPanel();
		pnlConfig.setBorder(new TitledBorder(new LineBorder(new Color(25, 25, 112)), "Configurações", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
		pnlConfig.setVisible( false );
		getContentPane().add(pnlConfig, BorderLayout.WEST);
		
		MontaJanela(1536, 896, true);
		
	}
	
	public void carregarMenu(){
		menuBarPrincipal = new JMenuBar();
	
		menuArquivo = new JMenu("Arquivo");
		menuBarPrincipal.add(menuArquivo);
		
		submenuAbrir = new JMenuItem("Abrir");
		submenuAbrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					arquivo = new Arquivo();
					imagem = new Imagem(arquivo.abrir());
					setTitle(arquivo.getArquivo().getName());
					carregaImagem(imagem);
					
				} catch (Exception e2) {
					System.out.println("Arquivo: " + e2.getMessage());
					
				}
				
			}
			
		});
		
		/*
		 * Menu Arquivo
		 */
		menuArquivo.add(submenuAbrir);
		menuArquivo.addSeparator();
		submenuSalvar = new JMenuItem("Salvar");
		submenuSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arquivo.salvar(imagem);
				
			}
			
		});
		
		menuArquivo.add(submenuSalvar);
		submenuSalvarcomo = new JMenuItem("Salvar como...");
		submenuSalvarcomo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arquivo.salvarComo(imagem);
				
			}
			
		});
		
		menuArquivo.add(submenuSalvarcomo);
		menuArquivo.addSeparator();
		submenuInformacoes = new JMenuItem("Informações");
		submenuInformacoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JanelaPropriedades(arquivo, imagem);
				
			}
			
		});
		
		menuArquivo.add(submenuInformacoes);
		submenuSair = new JMenuItem("Sair");
		submenuSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		
				
			}
			
		});
		
		menuArquivo.add(submenuSair);

		/*
		 * Menu Editar
		 */
		menuEditar = new JMenu("Editar");
		menuBarPrincipal.add(menuEditar);
		submenuBrilho = new JMenuItem("Brilho");
		submenuBrilho.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adicionaTelaConfig("Brilho", new JanelaBrilho(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuBrilho);
		submenuContraste = new JMenuItem("Contraste");
		submenuContraste.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adicionaTelaConfig("Contraste", new JanelaContraste(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuContraste);
		submenuRotacionar = new JMenuItem("Rotacionar");
		submenuRotacionar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				adicionaTelaConfig("Rotação", new JanelaRotacao(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuRotacionar);
		submenuTransladar = new JMenuItem("Transladar");
		submenuTransladar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				adicionaTelaConfig("Translação", new JanelaTranslacao(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuTransladar);
		submenuRedimensionar = new JMenuItem("Redimensionar");
		submenuRedimensionar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Redimensionar", new JanelaRedimensionar(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuRedimensionar);
		submenuEspelhar = new JMenu("Espelhar");
		menuEditar.add(submenuEspelhar);
		submenuVertical = new JMenuItem("Vertical");
		submenuVertical.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(imagem.espelhamentoVertical());
				
			}
			
		});
		
		submenuEspelhar.add(submenuVertical);
		submenuHorizontal = new JMenuItem("Horizontal");
		submenuHorizontal.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(imagem.espelhamentoHorizontal());
				
			}
			
		});
		
		submenuEspelhar.add(submenuHorizontal);
		submenuTransfLivre = new JMenuItem("Transformação Livre");
		submenuTransfLivre.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Transformação Livre", new JanelaTransformacaoLivre(JanelaPrincipal.this));
				
			}
			
		});
		
		menuEditar.add(submenuTransfLivre);
		menuEditar.addSeparator();
		submenuRestOriginal = new JMenuItem("Restaurar Original");
		submenuRestOriginal.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				restauraImagem();
				
			}
			
		});
		
		menuEditar.add(submenuRestOriginal);

		/*
		 * Menu Filtros
		 */
		menuFiltros = new JMenu("Filtros");
		menuBarPrincipal.add(menuFiltros);
		menuFiltrosDiversos = new JMenu("Diversos");
		menuFiltros.add(menuFiltrosDiversos);
		menuFiltro_10 = new JMenuItem("Valores maiores ou iguais a média de toda a imagem recebem branco");
		menuFiltro_10.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediaBranco(imagem).getBufferedImage());
				
			}
			
		});
		menuFiltrosDiversos.add(menuFiltro_10);
		
		menuFiltro_20 = new JMenuItem("Valores maiores ou iguais a moda de toda a imagem recebem preto");
		menuFiltro_20.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresModaPreto(imagem).getBufferedImage());
				
			}
			
		});
		menuFiltrosDiversos.add(menuFiltro_20);
		
		menuFiltro_30 = new JMenuItem("Valores maiores ou iguais a mediana de toda a imagem recebem 140");
		menuFiltro_30.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ) {
				trocaImagem( Efeitos.maioresMediana140( imagem ).getBufferedImage() );
				
			}
			
		});
		menuFiltrosDiversos.add( menuFiltro_30 );
		
		menuFiltro_35 = new JMenuItem("Valores da mediana do terceiro quadrante NEW");
		menuFiltro_35.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ) {
				trocaImagem( Efeitos.maioresMediana140( imagem ).getBufferedImage() );
				
			}
			
		});
		menuFiltrosDiversos.add( menuFiltro_35 );
		
		menuFiltro_40 = new JMenuItem( "Valores menores que a média de toda a imagem recebem 255" );
		menuFiltro_40.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ) {
				trocaImagem( Efeitos.medianaQuadrante3( imagem ).getBufferedImage() );
				
			}
			
		});
		menuFiltrosDiversos.add(menuFiltro_40);
		
		menuFiltro_50 = new JMenuItem("Valores maiores que a mediana de toda a imagem recebem 0 e menores que a média recebem 255");
		menuFiltro_50.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediana0MenoresMedia255(imagem).getBufferedImage());
				
			}
			
		});
		menuFiltrosDiversos.add(menuFiltro_50);
		
		menuFiltro_60 = new JMenuItem("Converter em tons de cinza");
		menuFiltro_60.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				trocaImagem(Efeitos.converteParaTonsDeCinza(imagem).getBufferedImage());
				
			}
			
		});
		menuFiltros.add(menuFiltro_60);
		
		menuFiltro_70 = new JMenuItem("Filtro Média");
		menuFiltro_70.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				int[][] media = {
						{1,1,1},
						{1,1,1},
						{1,1,1}
				};
				
				trocaImagem(imagem.convolucao(media));
				
			}
			
		});
		menuFiltros.add(menuFiltro_70);
		
		menuFiltro_80 = new JMenuItem("Filtro Gausiano");
		menuFiltro_80.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				int[][] gausiana = {
						{1,2,1},
						{2,4,2},
						{1,2,1}
				};
				
				trocaImagem(imagem.convolucao(gausiana));
				
			}
			
		});
		menuFiltros.add(menuFiltro_80);
		
		menuFiltro_90 = new JMenuItem("Filtro Limiar");
		menuFiltro_90.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {								
				adicionaTelaConfig("Filtro de Limiarização", new JanelaLimiarizacao(JanelaPrincipal.this));
				
			}
			
		});
		menuFiltros.add(menuFiltro_90);
		
		menuFiltro_110 = new JMenuItem("Filtro Livre");
		menuFiltro_110.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {								
				adicionaTelaConfig("Filtro Livre", new JanelaFiltroLivre(JanelaPrincipal.this));
				
			}
			
		});
		menuFiltros.add(menuFiltro_110);
		
		menuFiltro_120 = new JMenuItem("Detecção de Bordas");
		menuFiltro_120.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {								
				adicionaTelaConfig("Detecção de Bordas", new JanelaBordas(JanelaPrincipal.this));
				
			}
			
		});
		menuFiltros.add(menuFiltro_120);
		
		/*
		 * Menu Histograma
		 */
		menuHistograma = new JMenu("Histograma");
		menuBarPrincipal.add(menuHistograma);
		menuExibirHistograma = new JMenuItem("Exibir");
		menuExibirHistograma.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new JanelaHistograma(imagem);
				
			}
			
		});
		
		menuHistograma.add(menuExibirHistograma);
		menuFiltro_130 = new JMenuItem("Testeeee");
		menuFiltro_130.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {								
				adicionaTelaConfig("Testeeeeeeeeeeeeeeee", new JanelaBordas(JanelaPrincipal.this));
				
			}
			
		});
		menuFiltros.add( menuFiltro_130 );
		
		menuHistograma.add(menuExibirHistograma);
		menuFiltro_131 = new JMenuItem( "Valores Pixels" );
		menuFiltro_131.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ) {
				adicionaTelaConfig( "Menor 100", new JanelaTonalidadeMenor100( imagem ) );
				
			}
			
		});
		menuFiltros.add( menuFiltro_131 );
		
		/*
		 * Menu Histograma 2
		 */
		menuHistogramaQuadrante_1 = new JMenu( "Histograma Histograma 1" );
		menuBarPrincipal.add(menuHistogramaQuadrante_1);
		menuExibirHistogramaQuadrante1 = new JMenuItem( "Exibir" );
		menuExibirHistogramaQuadrante1.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				new JanelaHistogramaQuadrante_1( imagem );
				
			}
			
		});
		
		menuHistogramaQuadrante_1.add( menuExibirHistogramaQuadrante1 );

		/*
		 * Menu Médias
		 */
		menuMedias = new JMenu("Médias");
		menuBarPrincipal.add(menuMedias);

		menuExibirMediaQuadrante_2 = new JMenuItem("Exibir Média Quadrante 2");
		menuExibirMediaQuadrante_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaMediaQuadrante_2(imagem);
				}

			}
		});
		menuMedias.add(menuExibirMediaQuadrante_2);

		menuExibirModaQuadrante_4 = new JMenuItem("Exibir Moda Quadrante 4");
		menuExibirModaQuadrante_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaModaQuadrante_4(imagem);
				}
			}
		});
		menuMedias.add(menuExibirModaQuadrante_4);

		menuExibirVarianciaQuadrante_12 = new JMenuItem("Exibir Variância Quadrantes 1 e 2");
		menuExibirVarianciaQuadrante_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaVarianciaQuadrante_12(imagem);
				}
			}
		});
		menuMedias.add(menuExibirVarianciaQuadrante_12);

		/*
		 * Menu Quantidade de Pixels
		 */
		menuQuantidadePixels = new JMenu("Quantidade de Pixels");
		menuBarPrincipal.add(menuQuantidadePixels);

		menuExibirQtdPixelsInferior = new JMenuItem("Exibir Quantidade de pixels na metade inferior");
		menuExibirQtdPixelsInferior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaExibirQuantidadePixelsInferior(imagem);
				}
			}
		});
		menuQuantidadePixels.add(menuExibirQtdPixelsInferior);

		submenuFiltroGaussiano = new JMenuItem("Filtro Gaussiano");
		submenuFiltroGaussiano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					adicionaTelaConfig("Filtro Gaussiano", new JanelaFiltroGaussiano(JanelaPrincipal.this));
				}
			}
		});
		menuFiltros.add(submenuFiltroGaussiano);
			
	}
	
	public void carregaImagem(Imagem im){
		lblImagem.setIcon(new ImageIcon(im.getBufferedImage()));
		
	}
	
	public void trocaImagem(BufferedImage bi){
		trocaImagem(bi, true);
		
	}
	
	public void trocaImagem(BufferedImage bi, boolean salvar){	
		if(salvar == true){
			imagem = new Imagem(bi);
			
		}
		
		lblImagem.setIcon(new ImageIcon(bi));
		
	}
	
	public void restauraImagem(){
		imagem = new Imagem(arquivo.getArquivo());
		carregaImagem(imagem);
		
	}
	
	public void adicionaTelaConfig(String titulo, JPanel panel){
		fechaTelaConfig();
		((TitledBorder) pnlConfig.getBorder()).setTitle(titulo);
		pnlConfig.add(panel);
		pnlConfig.setVisible(true);
		
	}
	
	public void adicionaTelaConfig(String titulo, Object panel){
		fechaTelaConfig();
		((TitledBorder) pnlConfig.getBorder()).setTitle(titulo);
		pnlConfig.setVisible(true);
		
	}
	
	public void fechaTelaConfig(){
		pnlConfig.removeAll();
		pnlConfig.setVisible(false);
		
	}
	
	public Imagem getImagem(){
		return this.imagem;
		
	}

	private void abrirNovaImagem() {
		try {
			arquivo = new Arquivo();
			imagem = new Imagem(arquivo.abrir());
			setTitle(arquivo.getArquivo().getName());
			carregaImagem(imagem);

		} catch (Exception e2) {
			System.out.println("Arquivo: " + e2.getMessage());

		}
	}

}
