package fg.madLibs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UIJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] partsOfSpeech;
	private JLabel label;
	private ArrayList<String> words;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> texts;
	private ArrayList<String> filtered;
	private ArrayList<String> filteredWords;
	private ArrayList<String> textFile;
	private ArrayList<String> randomWords;
	private ArrayList<Integer> index;
	private String fileName;
	private String image;
	private JButton randomButton;
	private JButton submit;
	private MadLibThread thread;
	private RandomThread randomThread;
	private int counter = 0;
	private JTextField field;

	public UIJFrame(String filename, String imageURL) throws IOException {

		setTitle("MadLibs");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.decode("#9F0251"));

		// container.setBackground(Color.BLACK);

		JPanel north = new JPanel();
		north.setBackground(Color.decode("#8C489F"));
		container.add(north, BorderLayout.NORTH);

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
		south.setBackground((Color.decode("#9F0251")));

		container.add(south, BorderLayout.SOUTH);

		Font font = new Font("Type Embellishments One LET", Font.BOLD, 18);

		JPanel main = new JPanel();
		main.setBackground((Color.decode("#9F0251")));
		container.add(main);

		JScrollPane scroll = new JScrollPane(main, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		container.add(scroll);

		// container.add(main);
		GroupLayout layout = new GroupLayout(main);
		main.setLayout(layout);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		GroupLayout.Group yLabelGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);

		hGroup.addGroup(yLabelGroup);

		GroupLayout.Group yText = layout.createParallelGroup();
		hGroup.addGroup(yText);
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		layout.setVerticalGroup(vGroup);

		this.image = imageURL;
		this.labels = new ArrayList<JLabel>();
		this.texts = new ArrayList<JTextField>();
		this.label = new JLabel("Fill out the text boxes below with the appropriate information: ");
		this.label.setBackground(Color.decode("#8C489F"));
		this.label.setOpaque(true);
		this.label.setForeground(Color.decode("#C3C3E5"));
		this.label.setFont(font);
		north.add(label);

		this.fileName = filename;
		this.submit = new JButton("Display the story!");
		this.submit.setBackground(Color.decode("#177F75"));
		this.submit.setForeground(Color.decode("#CBFFFA"));
		this.submit.setFont(font);
		this.filtered = new ArrayList<String>();
		this.filteredWords = new ArrayList<String>();
		this.textFile = new ArrayList<String>();
		this.randomWords = new ArrayList<String>();
		this.words = new ArrayList<String>();
		this.index = new ArrayList<Integer>();
		this.randomButton = new JButton("Randomize My MadLib!!");
		this.randomButton.setBackground(Color.decode("#177F75"));
		this.randomButton.setForeground(Color.decode("#CBFFFA"));
		this.randomButton.setFont(font);

		south.add(submit);
		south.add(randomButton);

		labels = new ArrayList<JLabel>();
		texts = new ArrayList<JTextField>();

		readFile(fileName);

		int p = GroupLayout.PREFERRED_SIZE;

		for (counter = 0; counter < partsOfSpeech.length; counter++) {
			final String speech = partsOfSpeech[counter];
			final JLabel label = new JLabel((counter + 1 + ". " + partsOfSpeech[counter]));
			label.setForeground(Color.decode("#EC799A"));
			label.setFont(font);
			labels.add(label);
			field = new JTextField("");
			field.setBackground(Color.decode("#EC799A"));
			field.setForeground(Color.decode("#9F0251"));
			field.setFont(font);
			field.setPreferredSize(new Dimension(150, 25));
			texts.add(field);

			field.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent e) {

					field.setEditable(true);
				}

				public void focusLost(FocusEvent e) {
					field.setEditable(false);
					if (!field.getText().equalsIgnoreCase("")) {
						words.add(field.getText());
					}
					for (PartsOfSpeech pos : PartsOfSpeech.values()) {
						if (speech.equalsIgnoreCase(pos.name())) {
							filtered.add(speech);
							filteredWords.add(field.getText());
							index.add(counter);

							// threadCall(field.getText(), speech);
						}
					}

				}
			});
		}

		for (JLabel label : labels) {
			yLabelGroup.addComponent(label);
		}

		for (JTextField text : texts) {
			yText.addComponent(text, p, p, p);
		}

		for (int k = 0; k < labels.size(); k++) {

			vGroup.addGroup(layout.createParallelGroup().addComponent(labels.get(k))
					.addComponent(texts.get(k), p, p, p));
		}

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();
				new DisplayFrame(textFile, words, image).setVisible(true);

			}

		});

		randomButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//field.setText("Hello");
				//texts.set(0, field);
				filterArray();

				try {
					randomThreadCall();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				displayRandom();
				// System.out.println(randomWords);


			}
		});

	}

	public void readFile(String filename) throws FileNotFoundException {

		Scanner file = new Scanner(new File(filename));

		this.partsOfSpeech = file.nextLine().split("%");
		file.reset();
		String pattern = "[%]*";

		while (!(file.hasNext(pattern)) && file.hasNext()) {
			String line1 = file.next();
			textFile.add(line1);
		}
	}

	public void displayText() {
		int counter = 0;
		for (int i = 0; i < textFile.size(); i++) {
			if (textFile.get(i).equals(";")) {
				textFile.set(i, words.get(counter));
				counter++;
			}
		}

		System.out.println(textFile.toString().replace(",", " ")
				.replace("[", "").replace("]", "").trim());

	}

	public void addRandomWords(String word) {

		filteredWords.add(word);

	}

	public void threadCall(String word, String partOfSpeech) {

		thread = new MadLibThread(word, partOfSpeech, this);
		thread.start();

	}

	public void randomThreadCall() throws IOException {

		for (int i = 0; i < filtered.size(); i++) {

			randomThread = new RandomThread(filtered.get(i).toLowerCase(), this, index.get(i), texts, field);

			randomThread.start();

		}
		
		System.out.println(filteredWords);
	}

	public void checkWord(boolean found) throws NotEqualsException {
		if (!found) {
			try {
				throw new NotEqualsException();
			} catch (NotEqualsException e) {
				System.out
						.println("Word entered is not the correct part of speech. Please Try again!");
				e.printStackTrace();
			}
		}
	}

	public void filterArray() {

		for (int i = 0; i < partsOfSpeech.length; i++) {
			for (PartsOfSpeech pos : PartsOfSpeech.values()) {
				if (partsOfSpeech[i].equalsIgnoreCase(pos.name())) {
					filtered.add(partsOfSpeech[i]);
					index.add(i);

					if (!(words.isEmpty())) {
						filteredWords.add(words.get(i));

					} else {
						break;
					}
				}
			}
		}

		System.out.println(filtered.toString());
		System.out.println(filteredWords.toString());
		System.out.println(index);
	}

	public void displayRandom() {

		// System.out.println(randoms);
		System.out.println(filteredWords);
		// for (int i = 0; i < index.size(); i++) {
		// texts.set(index.get(i), new JTextField(randomWords.get(i)));
		// }
	}

	public static void main(String[] args) throws IOException {
		new UIJFrame("How To Wash Your Face.txt", "AdviceFromDadImage.jpeg")
				.setVisible(true);
	}

}
