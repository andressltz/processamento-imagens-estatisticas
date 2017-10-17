// package com.iceberg.janelas.filtros;
//
// import javax.swing.JPanel;
//
// import com.iceberg.sistema.Imagem;
//
// import java.awt.Color;
// import java.io.File;
//
// public class FiltroLaplaciano extends JPanel {
// public FiltroLaplaciano( File arquivoComImagem ) {
// Imagem img1 = new Imagem( arquivoComImagem );
// Imagem img2 = new Imagem( arquivoComImagem );
//
// int largura = img1.getLargura();
// int altura = img1.getAltura();
//
// for (int y = 1; y < altura - 1; y++) {
// for (int x = 1; x < largura - 1; x++) {
//
//// Color c00 = img1.redimensionar(scalaX, scalaY);
//// img1.get
//
// Color c00 = (Color)(img1.getTomCinza(x-1, y-1));
//
//// Color c00 = img1.get(x-1, y-1);
//// Color c01 = img1.get(x-1, y);
//// Color c02 = img1.get(x-1, y+1);
//// Color c10 = img1.get(x, y-1);
//// Color c11 = img1.get(x, y);
//// Color c12 = img1.get(x, y+1);
//// Color c20 = img1.get(x+1, y-1);
//// Color c21 = img1.get(x+1, y);
//// Color c22 = img1.get(x+1, y+1);
//
// int r = -c00.getRed() - c01.getRed() - c02.getRed() +
// -c10.getRed() + 8*c11.getRed() - c12.getRed() +
// -c20.getRed() - c21.getRed() - c22.getRed();
//
// int g = -c00.getGreen() - c01.getGreen() - c02.getGreen() +
// -c10.getGreen() + 8*c11.getGreen() - c12.getGreen() +
// -c20.getGreen() - c21.getGreen() - c22.getGreen();
//
// int b = -c00.getBlue() - c01.getBlue() - c02.getBlue() +
// -c10.getBlue() + 8*c11.getBlue() - c12.getBlue() +
// -c20.getBlue() - c21.getBlue() - c22.getBlue();
//
// r = Math.min(255, Math.max(0, r));
// g = Math.min(255, Math.max(0, g));
// b = Math.min(255, Math.max(0, b));
//
// Color color = new Color(r, g, b);
//
// picture2.set(x, y, color);
//
// }
//
// }
//
// picture2.show();
//
// }
//
// }
