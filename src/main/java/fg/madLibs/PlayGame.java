package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

//import the sun.audio package

public class PlayGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;

	public PlayGame() throws IOException {

		setTitle("MadLibs");
		setSize(550, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.BLACK);

		PlayGamePanel panel = new PlayGamePanel();

		container.add(panel, BorderLayout.CENTER);

		button = new JButton("PLAY GAME!");
		button.setPreferredSize(new Dimension(150, 40));
		button.setBackground(Color.MAGENTA);

		container.add(button, BorderLayout.SOUTH);

		InputStream in = new FileInputStream("Ring08.wav");

		AudioStream music = new AudioStream(in);

		AudioPlayer.player.start(music);

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
