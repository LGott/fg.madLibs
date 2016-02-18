package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboPopup;

public class MadLibJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	private JPanel topPanel;
	private JLabel iconLabel;
	private ImageIcon icon;
	private JLabel choose;
	private JButton submit;
	JComboBox<String> box;

	public MadLibJFrame() {
		setTitle("MadLibs");
		setSize(550, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.BLACK);

		Font font = new Font("Type Embellishments One LET", Font.BOLD, 18);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.YELLOW);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBackground(Color.BLACK);

		container.add(topPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.CENTER);

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

		String[] options = { "Advice from Dad", "Interview", "How To Wash Your Face", "Vacation", "A Familiar Story",
				"Clinton Against Obama" };

		box = new JComboBox<String>(options);
		box.setBackground(Color.BLACK);
		box.setForeground((Color.decode("#FF9900")));
		box.setFont(font);
		box.setSelectedIndex(5);
		box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

		Object child = box.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup) child;
		JList list = popup.getList();
		list.setSelectionBackground(Color.decode("#FF8000"));

		buttonPanel.add(box, BorderLayout.NORTH);

		submit.setBackground(Color.decode("#FF9900"));
		submit.setForeground(Color.BLACK);
		submit.setFont(font);
		submit.setHorizontalAlignment(JButton.CENTER);
		submit.setVerticalAlignment(JButton.CENTER);
		// submit.setPreferredSize(new Dimension(80,20));

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int select = box.getSelectedIndex();

				String story = null;
				String imageURL = null;

				switch (select) {

				case 0:

					story = "Mad Lib Advice From Dad.txt";

					break;
				case 1:

					story = "Mad Lib Job Interview.txt";

					break;

				case 2:

					story = "How To Wash Your Face.txt";

					break;

				case 3:

					story = "Vacation.txt";
					break;

				case 4:
					story = "A Familiar Story.txt";
					break;

				case 5:
					story = "Clinton Against Obama.txt";
					break;
				}

				try {
					new UIJFrame(story).setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}

		});

	}

}
