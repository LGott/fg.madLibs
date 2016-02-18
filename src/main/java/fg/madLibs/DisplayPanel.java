package fg.madLibs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel {

	private BufferedImage image;

	public DisplayPanel(String imageURL) {

		try {
			image = ImageIO.read(new File(imageURL));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

	}
	
	public BufferedImage getImage(){
		return this.image;
	}

}
