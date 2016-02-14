package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import the sun.audio package

public class PlayGame extends JFrame {

	private JPanel topPanel;
	private JPanel buttonPanel;
	private JLabel label;
	private JButton button;

	public PlayGame() throws IOException {

		setTitle("MadLibs");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.BLACK);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.BLACK);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.BLACK);

		ImageIcon big = new ImageIcon("./MadLibPic.png");
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setIcon(big);

		button = new JButton("PLAY GAME!");
		button.setPreferredSize(new Dimension(150, 40));
		button.setBackground(Color.MAGENTA);
		buttonPanel.add(button);

		topPanel.add(label);
		container.add(topPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.SOUTH);

		String musicFile = "Ring05.wav";

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();
				new MadLibJFrame().setVisible(true);

			}
		});
	}

	public static void main(String[] args) {
		try {
			new PlayGame().setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
