package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PracticeFrame extends JFrame {

	public PracticeFrame() {
		
		setTitle("MadLibs");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		JLabel label = new JLabel(new ImageIcon("AdviceFromDadImage.jpeg"));
		
		
		JTextArea area = new JTextArea("Hello");
		area.setBounds(300,300,300,300);
		//area.setBackground();
		add(area);
		container.add(label);
		
		area.setVisible(true);

	}
	
	public static void main(String[] args){
		new PracticeFrame().setVisible(true);
	}
}
