package fg.madLibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RandomThread extends Thread {

	private String pos;
	private HttpResponse<JsonNode> response = null;
	private UIJFrame frame;
	ArrayList<String> test;
	String word;

	public RandomThread(String pos, UIJFrame frame) {

		this.pos = pos;
		this.frame = frame;
		test = new ArrayList<String>();
	}

	@Override
	public void run() {

		for (int i = 0; i < 7; i++) {
			Random rand = new Random();
			int num = rand.nextInt(5) + 10; // random number from 150 to 500
			StringBuilder builder = new StringBuilder();

			builder.append("https://wordsapiv1.p.mashape.com/words/?partOfSpeech=");
			builder.append(pos);
			builder.append("&limit=100&page=");
			builder.append(num);

			try {
				response = Unirest.get(builder.toString())
						.header("X-Mashape-Key", "LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
						.header("Accept", "application/json").asJson();
			} catch (UnirestException e) {
				e.printStackTrace();
			}

			word = (String) response.getBody().getObject().getJSONObject("results").getJSONArray("data").get(0);
			System.out.println(word); // For testing purposes For testing
			// purposes

			frame.addRandomWords(word);
			frame.displayRandom();
			System.out.println(word);
			test.add(word);
			System.out.println(test);
		}

	}

	public ArrayList<String> returnWord() {
		return this.test;
	}

	public static void main(String args[]) throws IOException {

		// RandomThread random = new RandomThread("adjective");
		// random.start();
	}
}
