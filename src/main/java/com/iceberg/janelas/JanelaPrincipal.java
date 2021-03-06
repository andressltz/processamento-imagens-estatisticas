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
import com.iceberg.janelas.editar.JanelaTransformacaoLivre;
import com.iceberg.janelas.editar.JanelaTranslacao;
import com.iceberg.janelas.estatisticas.JanelaExibirQuantidadePixelsInferior;
import com.iceberg.janelas.estatisticas.JanelaMediaQuadrante_2;
import com.iceberg.janelas.estatisticas.JanelaModaQuadrante_4;
import com.iceberg.janelas.estatisticas.JanelaVarianciaQuadrante_12;
import com.iceberg.janelas.filtros.JanelaBordas;
import com.iceberg.janelas.filtros.JanelaFiltroLivre;
import com.iceberg.janelas.filtros.JanelaLimiarizacao;
import com.iceberg.janelas.histograma.JanelaHistograma;
import com.iceberg.janelas.histograma.JanelaHistogramaQuadrante_1;
import com.iceberg.janelas.histograma.JanelaTonalidadeMenor100;
import com.iceberg.sistema.Arquivo;
import com.iceberg.sistema.Imagem;
import com.iceberg.utils.Efeitos;
import com.iceberg.utils.ExtracaoCaracteristicas;

public class JanelaPrincipal extends Janela {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBarPrincipal;

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
		pnlConfig.setVisible(false);
		getContentPane().add(pnlConfig, BorderLayout.WEST);

		MontaJanela(1300, 800, true);
	}

	private void carregarMenu() {
		JMenu menuArquivo;
		JMenu menuEditar;
		JMenu menuFiltros;
		JMenu menuHistograma;
		JMenu menuEstatisticas;
		JMenu menuRotacionar;
		JMenu menuMorfologiaMatematica;
		JMenu menuExtracaoCaracteristicas;

		menuBarPrincipal = new JMenuBar();

		/*
		 * Menu Arquivo
		 */
		menuArquivo = new JMenu("Arquivo");
		menuBarPrincipal.add(menuArquivo);
		criaMenuArquivo(menuArquivo);

		/*
		 * Menu Editar
		 */
		menuEditar = new JMenu("Editar");
		menuBarPrincipal.add(menuEditar);
		criaMenuEditar(menuEditar);

		/*
		 * Menu Filtros
		 */
		menuFiltros = new JMenu("Filtros");
		menuBarPrincipal.add(menuFiltros);
		criaMenuFiltros(menuFiltros);

		/*
		 * Menu Histograma
		 */
		menuHistograma = new JMenu("Histograma");
		menuBarPrincipal.add(menuHistograma);
		criaMenuHistograma(menuHistograma);

		/*
		 * Menu Estatisticas
		 */
		menuEstatisticas = new JMenu("Estatísticas");
		menuBarPrincipal.add(menuEstatisticas);
		criaMenuEstatisticas(menuEstatisticas);

		/*
		 * Menu Rotacionar
		 */
		menuRotacionar = new JMenu("Rotacionar");
		menuBarPrincipal.add(menuRotacionar);
		criaMenuRotacionar(menuRotacionar);

		/*
		 * Menu Morfologia Matemática
		 */
		menuMorfologiaMatematica = new JMenu("Morfologia Matemática");
		menuBarPrincipal.add(menuMorfologiaMatematica);
		criaMenuMorfologiaMatematica(menuMorfologiaMatematica);

		/*
		 * Menu Extração de Características
		 */
		menuExtracaoCaracteristicas = new JMenu("Extração de Características");
		menuBarPrincipal.add(menuExtracaoCaracteristicas);
		criaMenuExtracaoCaracteristicas(menuExtracaoCaracteristicas);
	}

	private void criaMenuMorfologiaMatematica(JMenu menuMorfologiaMatematica) {
		// Menu Morfologia Matemática
		JMenuItem submenuMorfologiaMatematicaErosao;
		JMenuItem submenuMorfologiaMatematicaDilatacao;

		submenuMorfologiaMatematicaErosao = new JMenuItem("Erosão");
		submenuMorfologiaMatematicaErosao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					adicionaTelaConfig("Erosão", new JanelaFiltroLivre(JanelaPrincipal.this));
				}

			}
		});
		menuMorfologiaMatematica.add(submenuMorfologiaMatematicaErosao);

		submenuMorfologiaMatematicaDilatacao = new JMenuItem("Dilatação");
		submenuMorfologiaMatematicaDilatacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					adicionaTelaConfig("Dilatação", new JanelaFiltroLivre(JanelaPrincipal.this));
				}

			}
		});
		menuMorfologiaMatematica.add(submenuMorfologiaMatematicaDilatacao);
	}

	private void criaMenuRotacionar(JMenu menuRotacionar) {
		// Menu Rotacionar
		JMenuItem submenuRotacionarRestOriginal;
		JMenuItem submenuRotacionarEspelharHorizontal;
		JMenuItem submenuRotacionarEspelharVertical;
		JMenuItem submenuRotacionar180Graus;
		JMenuItem submenuRotacionar270Graus;
		JMenuItem submenuRotacionarTransladar;
		JMenuItem submenuRotacionarRedimensionar;

		// A) Original
		submenuRotacionarRestOriginal = new JMenuItem("Restaurar imagem original");
		submenuRotacionarRestOriginal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					restauraImagem();
			}
		});
		menuRotacionar.add(submenuRotacionarRestOriginal);

		// B) Espelhar Horizontal
		submenuRotacionarEspelharHorizontal = new JMenuItem("Espelhar Horizontalmente");
		submenuRotacionarEspelharHorizontal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					trocaImagem(imagem.espelhamentoHorizontal());
			}
		});
		menuRotacionar.add(submenuRotacionarEspelharHorizontal);

		// C) Espelhar Vertical
		submenuRotacionarEspelharVertical = new JMenuItem("Espelhar Verticalmente");
		submenuRotacionarEspelharVertical.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					restauraImagem();
					trocaImagem(imagem.espelhamentoVertical());
			}
		});
		menuRotacionar.add(submenuRotacionarEspelharVertical);

		// D) 180
		submenuRotacionar180Graus = new JMenuItem("Rotacionar 180 graus");
		submenuRotacionar180Graus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					restauraImagem();
					trocaImagem(imagem.rotacionar(180));
			}
		});
		menuRotacionar.add(submenuRotacionar180Graus);

		// E) 270
		submenuRotacionar270Graus = new JMenuItem("Rotacionar 270 graus");
		submenuRotacionar270Graus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					restauraImagem();
					trocaImagem(imagem.rotacionar(90));
			}
		});
		menuRotacionar.add(submenuRotacionar270Graus);

		// F) Transladar
		submenuRotacionarTransladar = new JMenuItem("Transladar");
		submenuRotacionarTransladar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
					adicionaTelaConfig("Translação", new JanelaTranslacao(JanelaPrincipal.this));
			}
		});
		menuRotacionar.add(submenuRotacionarTransladar);

		// G) Redimensionar
		submenuRotacionarRedimensionar = new JMenuItem("Redimensionar");
		submenuRotacionarRedimensionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
					adicionaTelaConfig("Redimensionar", new JanelaRedimensionar(JanelaPrincipal.this));
			}
		});
		menuRotacionar.add(submenuRotacionarRedimensionar);
	}

	private void criaMenuEstatisticas(JMenu menuEstatisticas) {
		// Menu Estatísticas
		JMenuItem submenuEstatisticasMediaQuadrante_2;
		// C mediana quadrante 3
		JMenuItem submenuEstatisticasModaQuadrante_4;
		JMenuItem submenuEstatisticasVarianciaQuadrante_12;
		JMenuItem submenuEstatisticasQtdPixelsInferior;

		submenuEstatisticasMediaQuadrante_2 = new JMenuItem("Exibir Média Quadrante 2");
		submenuEstatisticasMediaQuadrante_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					new JanelaMediaQuadrante_2(imagem);

			}
		});
		menuEstatisticas.add(submenuEstatisticasMediaQuadrante_2);

		submenuEstatisticasModaQuadrante_4 = new JMenuItem("Exibir Moda Quadrante 4");
		submenuEstatisticasModaQuadrante_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					new JanelaModaQuadrante_4(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasModaQuadrante_4);

		submenuEstatisticasVarianciaQuadrante_12 = new JMenuItem("Exibir Variância Quadrantes 1 e 2");
		submenuEstatisticasVarianciaQuadrante_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					new JanelaVarianciaQuadrante_12(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasVarianciaQuadrante_12);

		submenuEstatisticasQtdPixelsInferior = new JMenuItem("Exibir Quantidade de pixels na metade inferior");
		submenuEstatisticasQtdPixelsInferior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
					new JanelaExibirQuantidadePixelsInferior(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasQtdPixelsInferior);
	}

	private void criaMenuHistograma(JMenu menuHistograma) {
		// Menu Histograma
		JMenuItem submenuExibirHistograma;
		JMenuItem submenuExibirHistogramaQuadrante1;

		submenuExibirHistograma = new JMenuItem("Exibir Histograma");
		submenuExibirHistograma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				new JanelaHistograma(imagem);
			}
		});
		menuHistograma.add(submenuExibirHistograma);

		submenuExibirHistogramaQuadrante1 = new JMenuItem("Exibir Histograma Quadrante 1");
		submenuExibirHistogramaQuadrante1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				new JanelaHistogramaQuadrante_1(imagem);
			}
		});
		menuHistograma.add(submenuExibirHistogramaQuadrante1);
	}

	private void criaMenuFiltros(JMenu menuFiltros) {
		// Menu Filtros
		JMenu menuFiltrosDiversos;
		JMenuItem submenuFiltro_10;
		JMenuItem submenuFiltro_20;
		JMenuItem submenuFiltro_30;
		JMenuItem submenuFiltro_40;
		JMenuItem submenuFiltro_50;
		JMenuItem submenuFiltro_60;
		JMenuItem submenuFiltro_70;
		JMenuItem submenuFiltro_90;
		JMenuItem submenuFiltro_110;
		JMenuItem submenuFiltro_120;
		JMenuItem submenuFiltro_131;
		JMenuItem submenuFiltroGaussiano;

		menuFiltrosDiversos = new JMenu("Diversos");
		menuFiltros.add(menuFiltrosDiversos);
		submenuFiltro_10 = new JMenuItem("Valores maiores ou iguais a média de toda a imagem recebem branco");
		submenuFiltro_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.maioresMediaBranco(imagem).getBufferedImage());
			}

		});
		menuFiltrosDiversos.add(submenuFiltro_10);

		submenuFiltro_20 = new JMenuItem("Valores maiores ou iguais a moda de toda a imagem recebem preto");
		submenuFiltro_20.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.maioresModaPreto(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_20);

		submenuFiltro_30 = new JMenuItem("Valores maiores ou iguais a mediana de toda a imagem recebem 140");
		submenuFiltro_30.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.maioresMediana140(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_30);

		submenuFiltro_40 = new JMenuItem("Valores menores que a média de toda a imagem recebem 255");
		submenuFiltro_40.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.medianaQuadrante3(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_40);

		submenuFiltro_50 = new JMenuItem("Valores maiores que a mediana de toda a imagem recebem 0 e menores que a média recebem 255");
		submenuFiltro_50.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.maioresMediana0MenoresMedia255(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_50);

		submenuFiltro_131 = new JMenuItem("Valores Pixels");
		submenuFiltro_131.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				new JanelaTonalidadeMenor100(imagem);
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_131);

		submenuFiltro_60 = new JMenuItem("Converter em tons de cinza");
		submenuFiltro_60.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.converteParaTonsDeCinza(imagem).getBufferedImage());
			}
		});
		menuFiltros.add(submenuFiltro_60);

		submenuFiltro_70 = new JMenuItem("Filtro Média");
		submenuFiltro_70.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] media = {
						{ 1, 1, 1 },
						{ 1, 1, 1 },
						{ 1, 1, 1 }
				};
				validaImagemJaCarregada();
				trocaImagem(imagem.convolucao(media));
			}
		});
		menuFiltros.add(submenuFiltro_70);

		submenuFiltro_90 = new JMenuItem("Filtro Limiar");
		submenuFiltro_90.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Filtro de Limiarização", new JanelaLimiarizacao(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_90);

		submenuFiltro_110 = new JMenuItem("Filtro Livre");
		submenuFiltro_110.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Filtro Livre", new JanelaFiltroLivre(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_110);

		submenuFiltro_120 = new JMenuItem("Detecção de Bordas");
		submenuFiltro_120.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Detecção de Bordas", new JanelaBordas(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_120);

		submenuFiltroGaussiano = new JMenuItem("Filtro Gaussiano");
		submenuFiltroGaussiano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				int[][] gausiana = {
						{ 1, 2, 1 },
						{ 2, 4, 2 },
						{ 1, 2, 1 }
				};
				trocaImagem(imagem.convolucao(gausiana));
			}
		});
		menuFiltros.add(submenuFiltroGaussiano);
	}

	private void criaMenuEditar(JMenu menuEditar) {
		// Menu Editar
		JMenuItem submenuBrilho;
		JMenuItem submenuContraste;
		JMenuItem submenuTransfLivre;
		JMenuItem submenuRestOriginal;
		submenuBrilho = new JMenuItem("Brilho");
		submenuBrilho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Brilho", new JanelaBrilho(JanelaPrincipal.this));
			}
		});

		menuEditar.add(submenuBrilho);
		submenuContraste = new JMenuItem("Contraste");
		submenuContraste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Contraste", new JanelaContraste(JanelaPrincipal.this));
			}
		});

		menuEditar.add(submenuContraste);

		submenuTransfLivre = new JMenuItem("Transformação Livre");
		submenuTransfLivre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Transformação Livre", new JanelaTransformacaoLivre(JanelaPrincipal.this));
			}
		});

		menuEditar.add(submenuTransfLivre);
		menuEditar.addSeparator();
		submenuRestOriginal = new JMenuItem("Restaurar Original");
		submenuRestOriginal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				restauraImagem();
			}
		});
		menuEditar.add(submenuRestOriginal);
	}

	private void criaMenuArquivo(JMenu menuArquivo) {
		// Menu Arquivo
		JMenuItem submenuAbrir;
		JMenuItem submenuSalvar;
		JMenuItem submenuSalvarcomo;
		JMenuItem submenuInformacoes;
		JMenuItem submenuSair;

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

		menuArquivo.add(submenuAbrir);
		menuArquivo.addSeparator();

		submenuSalvar = new JMenuItem("Salvar");
		submenuSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				arquivo.salvar(imagem);
			}
		});

		menuArquivo.add(submenuSalvar);

		submenuSalvarcomo = new JMenuItem("Salvar como...");
		submenuSalvarcomo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				arquivo.salvarComo(imagem);
			}
		});

		menuArquivo.add(submenuSalvarcomo);
		menuArquivo.addSeparator();

		submenuInformacoes = new JMenuItem("Informações");
		submenuInformacoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
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
	}

	private void criaMenuExtracaoCaracteristicas(JMenu menuExtracaoCaracteristicas) {
		JMenuItem submenuExtracaoAreaQuadrado;
		JMenuItem submenuExtracaoAreaCirculoMaior;
		JMenuItem submenuExtracaoCircularidadeCirculos;

		submenuExtracaoAreaQuadrado = new JMenuItem("Calcular área e perímetro de um quadrado");
		submenuExtracaoAreaQuadrado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				ExtracaoCaracteristicas.calcularAreaPerimetroQuadrado(imagem);
			}
		});
		menuExtracaoCaracteristicas.add(submenuExtracaoAreaQuadrado);

		submenuExtracaoAreaCirculoMaior = new JMenuItem("Calcular área e perímetro do circulo maior");
		submenuExtracaoAreaCirculoMaior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				ExtracaoCaracteristicas.calcularAreaPerimetroCirculoMaior(imagem);
			}
		});
		menuExtracaoCaracteristicas.add(submenuExtracaoAreaCirculoMaior);

		submenuExtracaoCircularidadeCirculos = new JMenuItem("Calcular circularidade de todos os círculos");
		submenuExtracaoCircularidadeCirculos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				// TODO fazer
			}
		});
		menuExtracaoCaracteristicas.add(submenuExtracaoCircularidadeCirculos);
	}

	private void carregaImagem(Imagem im) {
		lblImagem.setIcon(new ImageIcon(im.getBufferedImage()));

	}

	public void trocaImagem(BufferedImage bi) {
		trocaImagem(bi, true);

	}

	public void trocaImagem(BufferedImage bi, boolean salvar) {
		if (salvar) {
			imagem = new Imagem(bi);
		}

		lblImagem.setIcon(new ImageIcon(bi));

	}

	public void restauraImagem() {
		imagem = new Imagem(arquivo.getArquivo());
		carregaImagem(imagem);

	}

	private void adicionaTelaConfig(String titulo, JPanel panel) {
		fechaTelaConfig();
		((TitledBorder) pnlConfig.getBorder()).setTitle(titulo);
		pnlConfig.add(panel);
		pnlConfig.setVisible(true);

	}

	private void adicionaTelaConfig(String titulo, Object panel) {
		fechaTelaConfig();
		((TitledBorder) pnlConfig.getBorder()).setTitle(titulo);
		pnlConfig.setVisible(true);

	}

	public void fechaTelaConfig() {
		pnlConfig.removeAll();
		pnlConfig.setVisible(false);

	}

	public Imagem getImagem() {
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

	private void validaImagemJaCarregada() {
		if (imagem == null) {
			abrirNovaImagem();
		}
	}

}
