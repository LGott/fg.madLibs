package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DisplayFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> file;
	private ArrayList<String> words;

	public DisplayFrame(ArrayList<String> file, ArrayList<String> words, String imageURL, String title) {

		this.file = file;
		this.words = words;

		setTitle("MadLibs");
		setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());

		container.add(topPanel, BorderLayout.NORTH);
		container.add(mainPanel, BorderLayout.CENTER);
		container.add(southPanel, BorderLayout.SOUTH);

		ImageIcon icon = new ImageIcon("./logo.png");
		JLabel label = new JLabel();
		label.setIcon(icon);
		topPanel.add(label);

		JLabel southLabel = new JLabel();
		southLabel.setIcon(icon);
		southPanel.add(southLabel);

		JTextArea backgroundArea = new JTextArea();
		backgroundArea.setLineWrap(true);
		backgroundArea.setWrapStyleWord(true);

		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).equals(";")) {
				file.set(i, words.get(counter));
				counter++;
			}

		}

		Font font = new Font("Type Embellishments One LET", Font.BOLD, 18);

		String fileString = this.file.toString().replace(",", " ").replace("[", "").replace("]", "").trim();

		backgroundArea.setText(title + "\n" + fileString);
		backgroundArea.setBackground(Color.BLACK);
		backgroundArea.setForeground(Color.WHITE);
		backgroundArea.setFont(font);
		backgroundArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(backgroundArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scroll);

	}

}