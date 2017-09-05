package com.iceberg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Negativo {
	private static JFileChooser chooser = new JFileChooser();
    static {
	chooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg",
			"png", "bmp"));
	chooser.setAcceptAllFileFilterUsed(false);
}

public static void main(String[] args) {
	try {
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;

		File file = chooser.getSelectedFile();
		BufferedImage img = ImageIO.read(file);
		// Inverte a imagem
		for (int y = 0; y < img.getHeight(); ++y)
			for (int x = 0; x < img.getWidth(); ++x) {
				Color pixel = new Color(img.getRGB(x, y));
				// Aplicamos a fórmula que o julio falou
				Color inverso = new Color(
						255 - pixel.getRed(),
						255 - pixel.getGreen(), 
						255 - pixel.getBlue());

				// Definimos novamente no pixel
				img.setRGB(x, y, inverso.getRGB());
			}

		file = new File(file.getParentFile(), "inverso.png");
		ImageIO.write(img, "png", file);
		JOptionPane.showMessageDialog(null, "<html&gt;<body&gt;Imagem:<br>"
				+ file.getAbsolutePath() + "<br>Gerada com sucesso!");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,
				"Não foi possível inverter a imagem.");
	}

}
}