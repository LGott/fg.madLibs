package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class DisplayFrame extends JFrame{
	
	private ArrayList<String> file;
	private ImageIcon icon;
	private JLabel story;
	private ArrayList<String> words;
	
	public DisplayFrame(ArrayList<String> file, ArrayList<String> words, String imageURL){
		
		this.file = file;
		this.icon = new ImageIcon(imageURL);
		this.words = words;
		
		setTitle("MadLibs");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//JLabel background = new JLabel(icon);
		//setContentPane(background);
		
		JTextArea backgroundArea = new JTextArea();
		backgroundArea.setLineWrap(true);
		backgroundArea.setWrapStyleWord(true);
		
		backgroundArea.setLayout(new BorderLayout());
		
		int counter = 0;
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).equals(";")) {
				file.set(i, words.get(counter));
				counter++;
			}

		}

		Font font = new Font("Type Embellishments One LET", Font.BOLD, 18);

		String fileString = this.file.toString().replace(",", " ").replace("[", "").replace("]", "").trim();
		backgroundArea.setText(fileString);
		backgroundArea.setBackground(Color.BLACK);
		backgroundArea.setForeground(Color.YELLOW);
		backgroundArea.setFont(font);
		
		add(backgroundArea, BorderLayout.CENTER);
		//container.add(background);
		
		//background.add(backgroundArea, BorderLayout.CENTER);
		
		
		
	}
	
	public static void main(String[] args){
		ArrayList<String> array = new ArrayList<String>();
		
		array.add("hello");
		array.add("hello");
		array.add("hello");
		array.add("hello");
		array.add("hello");
		array.add("how");
		array.add("are you");
		
		new DisplayFrame(array,array,"AdviceFromDadImage.jpeg").setVisible(true);
	}

}
