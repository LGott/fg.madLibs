package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MadLibJFrame extends JFrame {

	private JPanel buttonPanel;
	private JPanel topPanel;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JLabel iconLabel;
	private ImageIcon icon;
	private JLabel choose;
	private JButton submit;
	JComboBox<String> box;

	public MadLibJFrame() {
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
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBackground(Color.BLACK);

		container.add(topPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.CENTER);

		one = new JButton("One");
		one.setBackground(Color.ORANGE);
		one.setPreferredSize(new Dimension(80, 40));

		two = new JButton("Two");
		two.setBackground(Color.ORANGE);
		two.setPreferredSize(new Dimension(80, 40));

		three = new JButton("Three");
		three.setBackground(Color.ORANGE);
		three.setPreferredSize(new Dimension(80, 40));

		four = new JButton("Four");
		four.setBackground(Color.ORANGE);
		four.setPreferredSize(new Dimension(80, 40));

		icon = new ImageIcon("./logo.png");
		iconLabel = new JLabel();
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);

		iconLabel.setIcon(icon);

		choose = new JLabel("Choose one of the following:");
		choose.setOpaque(true);
		choose.setBackground(Color.BLACK);
		choose.setForeground(Color.WHITE);
		choose.setHorizontalAlignment(JLabel.CENTER);
		choose.setVerticalAlignment(JLabel.CENTER);
		choose.setFont(new Font("Cursive", Font.CENTER_BASELINE, 25));
		topPanel.add(iconLabel);
		topPanel.add(choose, BorderLayout.SOUTH);

		submit = new JButton("Submit");
		buttonPanel.add(submit, BorderLayout.SOUTH);
		// buttonPanel.add(one);
		// buttonPanel.add(two);
		// buttonPanel.add(three);
		// buttonPanel.add(four);

		String[] options = { "Advice from Dad", "Interview",
				"How To Wash Your Face", "Vacation" };

		box = new JComboBox<String>(options);
		box.setBackground(Color.GRAY);
		box.setForeground((Color.ORANGE));
		box.setSelectedIndex(3);
		box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

		buttonPanel.add(box, BorderLayout.NORTH);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int select = box.getSelectedIndex();

				String story = null;
				String imageURL = null;

				switch (select) {

				case 0:

					story = "Mad Lib Advice From Dad.txt";
					imageURL = "AdviceFromDadImage.jpeg";

					break;
				case 1:

					story = "Mad Lib Job Interview.txt";
					imageURL = "AdviceFromDadImage.jpeg";

					break;

				case 2:

					story = "How To Wash Your Face.txt";
					imageURL = "AdviceFromDadImage.jpeg";

					break;

				case 3:

					story = "Vacation.txt";
					imageURL = "AdviceFromDadImage.jpeg";

					break;

				}

				try {
					new UIJFrame(story, imageURL).setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

}
