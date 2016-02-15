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

		PlayGamePanel panel = new PlayGamePanel();

		container.add(panel, BorderLayout.CENTER);

		button = new JButton("PLAY GAME!");
		button.setPreferredSize(new Dimension(150, 40));
		button.setBackground(Color.MAGENTA);

		container.add(button, BorderLayout.SOUTH);

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
			e.printStackTrace();
		}
	}

}
