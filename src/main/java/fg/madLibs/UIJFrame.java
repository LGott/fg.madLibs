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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
	private ArrayList<Integer> index;
	private String fileName;
	private String image;
	private JButton randomButton;
	private JButton submit;
	private MadLibThread thread;
	private RandomThread randomThread;
	private int counter = 0;
	private ArrayList<Integer> userIndex;

	private JLabel imageLabel;
	private JButton enterButton;

	public UIJFrame(String filename, String imageURL) throws IOException {

		setTitle("MadLibs");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.decode("#9F0251"));

		JPanel north = new JPanel();
		north.setBackground(Color.decode("#8C489F"));
		north.setLayout(new BorderLayout());
		container.add(north, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
		buttonPanel.setBackground(Color.decode("#8C489F"));

		north.add(buttonPanel, BorderLayout.SOUTH);

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
		south.setBackground(Color.decode("#8C489F"));

		container.add(south, BorderLayout.SOUTH);

		Font font = new Font("Berlin Sans FB", Font.PLAIN, 25);

		JPanel main = new JPanel();
		main.setBackground((Color.decode("#9F0251")));
		container.add(main);

		JScrollPane scroll = new JScrollPane(main,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		container.add(scroll);

		GroupLayout layout = new GroupLayout(main);
		main.setLayout(layout);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.Group yLabelGroup = layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING);

		hGroup.addGroup(yLabelGroup);

		GroupLayout.Group yText = layout.createParallelGroup();
		hGroup.addGroup(yText);
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		layout.setVerticalGroup(vGroup);

		this.image = imageURL;
		this.label = new JLabel(
				"Click a button to choose which way you want to play: ",
				SwingConstants.CENTER);
		this.label.setBackground(Color.decode("#8C489F"));
		this.label.setOpaque(true);
		this.label.setForeground(Color.decode("#C3C3E5"));
		this.label.setFont(font);
		this.imageLabel = new JLabel(new ImageIcon("pink logo.jpg"));
		this.fileName = filename;
		this.submit = new JButton("Display My Mad Lib!");
		this.submit.setBackground(Color.decode("#AA0078"));
		this.submit.setForeground(Color.decode("#CBFFFA"));
		this.submit.setFont(font);

		this.filtered = new ArrayList<String>();
		this.filteredWords = new ArrayList<String>();
		this.textFile = new ArrayList<String>();
		this.words = new ArrayList<String>();
		this.index = new ArrayList<Integer>();
		this.labels = new ArrayList<JLabel>();
		this.texts = new ArrayList<JTextField>();
		this.randomButton = new JButton("Randomize My MadLib!!");
		this.randomButton.setBackground(Color.decode("#421C52"));
		this.randomButton.setForeground(Color.decode("#CBFFFA"));
		this.randomButton.setFont(font);

		this.enterButton = new JButton("Enter info myself!!");
		this.enterButton.setBackground(Color.decode("#421C52"));
		this.enterButton.setForeground(Color.decode("#CBFFFA"));
		this.enterButton.setFont(font);

		this.userIndex = new ArrayList<Integer>();

		north.add(label, BorderLayout.CENTER);
		north.add(imageLabel, BorderLayout.NORTH);
		south.add(submit);
		buttonPanel.add(randomButton);
		buttonPanel.add(enterButton);

		readFile(fileName);

		int p = GroupLayout.PREFERRED_SIZE;

		for (counter = 0; counter < partsOfSpeech.length; counter++) {
			final String speech = partsOfSpeech[counter];
			final JLabel label = new JLabel(
					(counter + 1 + ". " + partsOfSpeech[counter]));
			label.setForeground(Color.decode("#EC799A"));
			label.setFont(font);
			this.labels.add(label);
			final JTextField field = new JTextField("");
			field.setBackground(Color.decode("#EC799A"));
			field.setForeground(Color.decode("#9F0251"));
			field.setFont(font);
			field.setPreferredSize(new Dimension(200, 25));
			field.setEnabled(false);
			this.texts.add(field);
			field.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent e) {

				}

				public void focusLost(FocusEvent e) {

					if (userIndex.size() > 0) {
						for (int i = 0; i < userIndex.size(); i++) {
							if (texts.get(userIndex.get(i)).getText()
									.equals("Enter Word:")) {

								if (!field.getText().equals("Enter Word:")) {
									words.set(userIndex.get(i - 1),
											field.getText());
								}
								break;
							}
						}
					}

					if (!field.getText().equalsIgnoreCase("")) {
						words.add(field.getText());
					}

					System.out.println(words.toString());
					for (PartsOfSpeech pos : PartsOfSpeech.values()) {
						if (speech.equalsIgnoreCase(pos.name())) {
							filtered.add(speech);
							filteredWords.add(field.getText());
							index.add(counter);

							threadCall(field.getText().toLowerCase(),
									speech.toLowerCase());
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

			vGroup.addGroup(layout.createParallelGroup()
					.addComponent(labels.get(k))
					.addComponent(texts.get(k), p, p, p));
		}

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();

				new DisplayFrame(textFile, words, image).setVisible(true);
			}
		});

		enterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				randomButton.setEnabled(false);
				for (int i = 0; i < texts.size(); i++) {
					texts.get(i).setEnabled(true);
				}
			}
		});

		randomButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				enterButton.setEnabled(false);

				filterArray();

				try {

					randomThreadCall();
					displayRandom();

				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				displayRandom();
				displayRandomText();

			}
		});

		InputStream in = new FileInputStream("Ring08.wav");

		AudioStream music = new AudioStream(in);

		AudioPlayer.player.start(music);

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

	public void displayRandomText() {

		class DelayTask extends TimerTask {
			@Override
			public void run() {
				JOptionPane
						.showMessageDialog(
								null,
								"Not all words can be Randomized!\n Please fill in the remaining text boxes :)",
								"MadLibs", JOptionPane.PLAIN_MESSAGE,
								new ImageIcon("./small icon.png"));
			}
		}

		new Timer().schedule(new DelayTask(), 5200);

		for (int i = 0; i < texts.size(); i++) {

			final JTextField field = texts.get(i);
			field.setEnabled(true);

		}

		for (int i = 0; i < texts.size(); i++) {
			if (texts.get(i).isEnabled() == true) {
				texts.get(i).setText("Enter Word:");
				counter = i;

			}
		}

	}

	public void addRandomWords(String word, int index) {

		words.set(index, word);
		System.out.println(words.toString());

	}

	public void threadCall(String word, String partOfSpeech) {

		thread = new MadLibThread(word, partOfSpeech, this);
		thread.start();

	}

	public void randomThreadCall() throws IOException, InterruptedException {

		for (int i = 0; i < filtered.size(); i++) {

			randomThread = new RandomThread(filtered.get(i).toLowerCase(),
					this, index.get(i), texts, texts.get(index.get(i)));

			randomThread.start();
		}
	}

	public void checkWord(boolean found) throws NotEqualsException {
		if (!found) {
			try {
				throw new NotEqualsException();
			} catch (NotEqualsException e) {
				JOptionPane

						.showMessageDialog(null,
								"Word entered is not the correct part of speech. Please Try again!");
			} catch (NullPointerException e){
				JOptionPane
				.showMessageDialog(null,
						"Word entered is not the correct part of speech. Please Try again!");

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

		// make an index array of all the spots where the not random words will
		// go

		for (int i = 0; i < partsOfSpeech.length; i++) {
			if (!index.contains(i)) {
				userIndex.add(i);
			}
		}

		System.out.println(filtered);
		System.out.println(filteredWords);
		System.out.println(index);
		System.out.println(userIndex);

	}

	public void displayRandom() {

		for (int i = 0; i < texts.size(); i++) {
			words.add("");
		}

		System.out.println(words);
		System.out.println(words.toString());

	}

	public static void main(String[] args) throws IOException {
		new UIJFrame("How To Wash Your Face.txt", "AdviceFromDadImage.jpeg")
				.setVisible(true);
	}

}
