package fg.madLibs;



import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayGamePanel extends JPanel{
	
	private BufferedImage image;
	
	public PlayGamePanel(){
		
		try {
			image = ImageIO.read(new File("./MadLibPic.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
	
		
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
	}
	
	

	

}
