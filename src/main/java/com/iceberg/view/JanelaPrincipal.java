package com.iceberg.view;

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

import com.iceberg.controller.estatisticas.EstatisticasCalculoController;
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
import com.iceberg.model.Arquivo;
import com.iceberg.model.Imagem;
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

		// Parte 1
		JMenuItem submenuEstatisticasMediaQuadrante_2;
		JMenuItem submenuEstatisticasMedianaQuadrante_3;
		JMenuItem submenuEstatisticasModaQuadrante_4;
		JMenuItem submenuEstatisticasVarianciaQuadrante_12;
		JMenuItem submenuEstatisticasQtdPixelsSuperior;
		JMenuItem submenuEstatisticasQtdPixelsInferior;

		// Parte 2
		JMenuItem submenuEstatisticasAlteraMediaQuadrante_02;
		JMenuItem submenuFiltro_20;
		JMenuItem submenuEstatisticasAlteraMedianaQuadrante_03;
		JMenuItem submenuFiltro_40;
		JMenuItem submenuFiltro_50;

		submenuEstatisticasMediaQuadrante_2 = new JMenuItem("Exibir média quadrante 2");
		submenuEstatisticasMediaQuadrante_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.calculaMediaQuadrante2(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasMediaQuadrante_2);

		submenuEstatisticasMedianaQuadrante_3 = new JMenuItem("Exibir mediana do quadrante 3");
		submenuEstatisticasMedianaQuadrante_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.calculaMedianaQuadrante3(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasMedianaQuadrante_3);

		submenuEstatisticasModaQuadrante_4 = new JMenuItem("Exibir moda quadrante 4");
		submenuEstatisticasModaQuadrante_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.calculaModaQuadrante4(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasModaQuadrante_4);

		submenuEstatisticasVarianciaQuadrante_12 = new JMenuItem("Exibir variância quadrantes 1 e 2");
		submenuEstatisticasVarianciaQuadrante_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.calculaVarianciaQuadrante12(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasVarianciaQuadrante_12);

		submenuEstatisticasQtdPixelsSuperior = new JMenuItem("Exibir quantidade de pixels na metade superior");
		submenuEstatisticasQtdPixelsSuperior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.exibirQuantidadePixelsSuperior(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasQtdPixelsSuperior);

		submenuEstatisticasQtdPixelsInferior = new JMenuItem("Exibir quantidade de pixels na metade inferior");
		submenuEstatisticasQtdPixelsInferior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				EstatisticasCalculoController.exibirQuantidadePixelsInferior(imagem);
			}
		});
		menuEstatisticas.add(submenuEstatisticasQtdPixelsInferior);
		menuEstatisticas.addSeparator();
		//
		// Apresente novamente a imagem com as seguintes alterações:
		//
		// a) Valores maiores ou iguais a média do quadrante 2 recebem branco.
		// b) Valores maiores ou iguais a moda do quadrante 4 recebem 200.
		// c) Valores maiores ou iguais a mediana do quadrante 3 recebem 220.
		// d) Valores menores que a média do quadrante 2 recebem 100.
		// e) Valores maiores que a média do quadrante 2 recebem 0 e menores que a mediana do quadrante 3 recebem 255.


		/**
		 * Parte 1<br>
		 * f) Informe quantos pixels com tonalidade menor do que 100 tem na metade superior da imagem.<br>
		 * g) Informe quantos pixels com tonalidade superior a 150 tem na metade inferior da imagem.<br>
		 * Parte 2<br>
		 * Apresente novamente a imagem com as seguintes alterações: <br>
		 * b) Valores maiores ou iguais a moda do quadrante 4 recebem 200.<br>
		 * d) Valores menores que a média do quadrante 2 recebem 100.<br>
		 * e) Valores maiores que a média do quadrante 2 recebem 0 e menores que a mediana do quadrante 3 recebem 255.<br>
		 */
		submenuEstatisticasAlteraMediaQuadrante_02 = new JMenuItem("Valores maiores ou iguais a média do quadrante 2 recebem branco");
		submenuEstatisticasAlteraMediaQuadrante_02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.mediaQuadrante2Branco(imagem));
			}
		});
		menuEstatisticas.add(submenuEstatisticasAlteraMediaQuadrante_02);

		// submenuFiltro_20 = new JMenuItem("Valores maiores ou iguais a moda de toda a imagem recebem preto");
		// submenuFiltro_20.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// validaImagemJaCarregada();
		// trocaImagem(Efeitos.maioresModaPreto(imagem).getBufferedImage());
		// }
		// });
		// menuEstatisticas.add(submenuFiltro_20);

		submenuEstatisticasAlteraMedianaQuadrante_03 = new JMenuItem("Valores maiores ou iguais a mediana do quadrante 3 recebem 220");
		submenuEstatisticasAlteraMedianaQuadrante_03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				trocaImagem(Efeitos.maioresMedianaQuadrante3Recebe220(imagem));
			}
		});
		menuEstatisticas.add(submenuEstatisticasAlteraMedianaQuadrante_03);

		// submenuFiltro_40 = new JMenuItem("Valores menores que a média de toda a imagem recebem 255");
		// submenuFiltro_40.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// validaImagemJaCarregada();
		// trocaImagem(Efeitos.medianaQuadrante3(imagem).getBufferedImage());
		// }
		// });
		// menuEstatisticas.add(submenuFiltro_40);

		// submenuFiltro_50 = new JMenuItem("Valores maiores que a mediana de toda a imagem recebem 0 e menores que a média recebem 255");
		// submenuFiltro_50.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// validaImagemJaCarregada();
		// trocaImagem(Efeitos.maioresMediana0MenoresMedia255(imagem).getBufferedImage());
		// }
		// });
		// menuEstatisticas.add(submenuFiltro_50);
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
		JMenuItem submenuFiltro_60;
		JMenuItem submenuFiltro_70;
		JMenuItem submenuFiltro_90;
		JMenuItem submenuFiltro_110;
		JMenuItem submenuFiltro_120;
		JMenuItem submenuFiltro_130;
		JMenuItem submenuFiltroGaussiano;

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

		submenuFiltro_130 = new JMenuItem("Testeeee");
		submenuFiltro_130.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validaImagemJaCarregada();
				adicionaTelaConfig("Testeeeeeeeeeeeeeeee", new JanelaBordas(JanelaPrincipal.this));
			}
		});
		menuFiltros.add(submenuFiltro_130);

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
