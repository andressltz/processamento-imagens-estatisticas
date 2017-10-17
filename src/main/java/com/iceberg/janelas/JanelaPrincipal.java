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
import com.iceberg.janelas.filtros.JanelaBordas;
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
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					restauraImagem();
				}
			}
		});
		menuRotacionar.add(submenuRotacionarRestOriginal);

		// B) Espelhar Horizontal
		submenuRotacionarEspelharHorizontal = new JMenuItem("Espelhar Horizontalmente");
		submenuRotacionarEspelharHorizontal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					restauraImagem();
					trocaImagem(imagem.espelhamentoHorizontal());
				}
			}
		});
		menuRotacionar.add(submenuRotacionarEspelharHorizontal);

		// C) Espelhar Vertical
		submenuRotacionarEspelharVertical = new JMenuItem("Espelhar Verticalmente");
		submenuRotacionarEspelharVertical.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					restauraImagem();
					trocaImagem(imagem.espelhamentoVertical());
				}
			}
		});
		menuRotacionar.add(submenuRotacionarEspelharVertical);

		// D) 180
		submenuRotacionar180Graus = new JMenuItem("Rotacionar 180 graus");
		submenuRotacionar180Graus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					restauraImagem();
					trocaImagem(imagem.rotacionar(180));
				}
			}
		});
		menuRotacionar.add(submenuRotacionar180Graus);

		// E) 270
		submenuRotacionar270Graus = new JMenuItem("Rotacionar 270 graus");
		submenuRotacionar270Graus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					restauraImagem();
					trocaImagem(imagem.rotacionar(90));
				}
			}
		});
		menuRotacionar.add(submenuRotacionar270Graus);

		// F) Transladar
		submenuRotacionarTransladar = new JMenuItem("Transladar");
		submenuRotacionarTransladar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					adicionaTelaConfig("Translação", new JanelaTranslacao(JanelaPrincipal.this));
				}
			}
		});
		menuRotacionar.add(submenuRotacionarTransladar);

		// G) Redimensionar
		submenuRotacionarRedimensionar = new JMenuItem("Redimensionar");
		submenuRotacionarRedimensionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					adicionaTelaConfig("Redimensionar", new JanelaRedimensionar(JanelaPrincipal.this));
				}
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
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaMediaQuadrante_2(imagem);
				}

			}
		});
		menuEstatisticas.add(submenuEstatisticasMediaQuadrante_2);

		submenuEstatisticasModaQuadrante_4 = new JMenuItem("Exibir Moda Quadrante 4");
		submenuEstatisticasModaQuadrante_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaModaQuadrante_4(imagem);
				}
			}
		});
		menuEstatisticas.add(submenuEstatisticasModaQuadrante_4);

		submenuEstatisticasVarianciaQuadrante_12 = new JMenuItem("Exibir Variância Quadrantes 1 e 2");
		submenuEstatisticasVarianciaQuadrante_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaVarianciaQuadrante_12(imagem);
				}
			}
		});
		menuEstatisticas.add(submenuEstatisticasVarianciaQuadrante_12);

		submenuEstatisticasQtdPixelsInferior = new JMenuItem("Exibir Quantidade de pixels na metade inferior");
		submenuEstatisticasQtdPixelsInferior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imagem == null) {
					abrirNovaImagem();
				} else {
					new JanelaExibirQuantidadePixelsInferior(imagem);
				}
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
				new JanelaHistograma(imagem);
			}
		});
		menuHistograma.add(submenuExibirHistograma);

		submenuExibirHistogramaQuadrante1 = new JMenuItem("Exibir Histograma Quadrante 1");
		submenuExibirHistogramaQuadrante1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		JMenuItem submenuFiltro_35;
		JMenuItem submenuFiltro_40;
		JMenuItem submenuFiltro_50;
		JMenuItem submenuFiltro_60;
		JMenuItem submenuFiltro_70;
		JMenuItem submenuFiltro_80;
		JMenuItem submenuFiltro_90;
		JMenuItem submenuFiltro_110;
		JMenuItem submenuFiltro_120;
		JMenuItem submenuFiltro_130;
		JMenuItem submenuFiltro_131;

		menuFiltrosDiversos = new JMenu("Diversos");
		menuFiltros.add(menuFiltrosDiversos);
		submenuFiltro_10 = new JMenuItem("Valores maiores ou iguais a média de toda a imagem recebem branco");
		submenuFiltro_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediaBranco(imagem).getBufferedImage());

			}

		});
		menuFiltrosDiversos.add(submenuFiltro_10);

		submenuFiltro_20 = new JMenuItem("Valores maiores ou iguais a moda de toda a imagem recebem preto");
		submenuFiltro_20.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresModaPreto(imagem).getBufferedImage());

			}

		});
		menuFiltrosDiversos.add(submenuFiltro_20);

		submenuFiltro_30 = new JMenuItem("Valores maiores ou iguais a mediana de toda a imagem recebem 140");
		submenuFiltro_30.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediana140(imagem).getBufferedImage());

			}

		});
		menuFiltrosDiversos.add(submenuFiltro_30);

		submenuFiltro_35 = new JMenuItem("Valores da mediana do terceiro quadrante NEW");
		submenuFiltro_35.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediana140(imagem).getBufferedImage());

			}

		});
		menuFiltrosDiversos.add(submenuFiltro_35);

		submenuFiltro_40 = new JMenuItem("Valores menores que a média de toda a imagem recebem 255");
		submenuFiltro_40.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.medianaQuadrante3(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_40);

		submenuFiltro_50 = new JMenuItem("Valores maiores que a mediana de toda a imagem recebem 0 e menores que a média recebem 255");
		submenuFiltro_50.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocaImagem(Efeitos.maioresMediana0MenoresMedia255(imagem).getBufferedImage());
			}
		});
		menuFiltrosDiversos.add(submenuFiltro_50);

		submenuFiltro_60 = new JMenuItem("Converter em tons de cinza");
		submenuFiltro_60.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

				trocaImagem(imagem.convolucao(media));

			}

		});
		menuFiltros.add(submenuFiltro_70);

		submenuFiltro_80 = new JMenuItem("Filtro Gausiano");
		submenuFiltro_80.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] gausiana = {
						{ 1, 2, 1 },
						{ 2, 4, 2 },
						{ 1, 2, 1 }
				};

				trocaImagem(imagem.convolucao(gausiana));

			}

		});
		menuFiltros.add(submenuFiltro_80);

		submenuFiltro_90 = new JMenuItem("Filtro Limiar");
		submenuFiltro_90.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Filtro de Limiarização", new JanelaLimiarizacao(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_90);

		submenuFiltro_110 = new JMenuItem("Filtro Livre");
		submenuFiltro_110.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Filtro Livre", new JanelaFiltroLivre(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_110);

		submenuFiltro_120 = new JMenuItem("Detecção de Bordas");
		submenuFiltro_120.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Detecção de Bordas", new JanelaBordas(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_120);

		submenuFiltro_130 = new JMenuItem("Testeeee");
		submenuFiltro_130.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Testeeeeeeeeeeeeeeee", new JanelaBordas(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_130);

		submenuFiltro_131 = new JMenuItem("Valores Pixels");
		submenuFiltro_131.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Menor 100", new JanelaTonalidadeMenor100(imagem));
			}
		});
		menuFiltros.add(submenuFiltro_131);
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
				adicionaTelaConfig("Brilho", new JanelaBrilho(JanelaPrincipal.this));

			}

		});

		menuEditar.add(submenuBrilho);
		submenuContraste = new JMenuItem("Contraste");
		submenuContraste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adicionaTelaConfig("Contraste", new JanelaContraste(JanelaPrincipal.this));
			}
		});

		menuEditar.add(submenuContraste);

		submenuTransfLivre = new JMenuItem("Transformação Livre");
		submenuTransfLivre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionaTelaConfig("Transformação Livre", new JanelaTransformacaoLivre(JanelaPrincipal.this));
			}
		});

		menuEditar.add(submenuTransfLivre);
		menuEditar.addSeparator();
		submenuRestOriginal = new JMenuItem("Restaurar Original");
		submenuRestOriginal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

}
