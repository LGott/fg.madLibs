package fg.madLibs;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RandomThread extends Thread {

	private String pos;
	private HttpResponse<JsonNode> response = null;
	private UIJFrame frame;
	private ArrayList<String> test;
	private String word;
	private ArrayList<JTextField> texts;
	private int index;
	private JTextField field;

	public RandomThread(String pos, UIJFrame frame, int index, ArrayList<JTextField> fields, JTextField field) {

		this.pos = pos;
		this.frame = frame;
		test = new ArrayList<String>();
		this.texts = fields;
		this.index = index;
		this.field = field;
	}

	@Override
	public void run() {

		Random rand = new Random();
		int num = rand.nextInt(20) + 8; // random number from 150 to 500
		StringBuilder builder = new StringBuilder();

		builder.append("https://wordsapiv1.p.mashape.com/words/?partOfSpeech=");
		builder.append(pos);
		builder.append("&limit=100&page=");
		builder.append(num);

		try {
			response = Unirest
					.get(builder.toString())
					.header("X-Mashape-Key",
							"LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
					.header("Accept", "application/json").asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		word = response.getBody().getObject().getJSONObject("results")
				.getJSONArray("data").get(0).toString();
		System.out.println(word); // For testing purposes

		field.setText(word);
		texts.set(index, field);
		//frame.addRandomWords(word);

	}
	/**
	 * public ArrayList<String> returnWord() { return this.test; }
	 * 
	 * public static void main(String args[]) throws IOException {
	 * 
	 * <<<<<<< HEAD RandomThread random = new RandomThread("verb", new UIJFrame(
	 * "Mad Lib Advice From Dad.txt", "AdviceFromDadImage.jpeg"));
	 * random.start(); ======= // RandomThread random = new
	 * RandomThread("adjective"); // random.start(); >>>>>>>
	 * 649b30ad9fe31e3c272c7d31b1958eb6f9d58516 }
	 **/
}
