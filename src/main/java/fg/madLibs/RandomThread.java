package fg.madLibs;

import java.io.IOException;
import java.util.Random;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RandomThread extends Thread {

	// ArrayList<String> randomWords;
	private String pos;
	private HttpResponse<JsonNode> response = null;
	private UIJFrame frame;

	public RandomThread(String pos, UIJFrame frame) {

		this.pos = pos;
		this.frame = frame;

	}

	@Override
	public void run() {

		Random rand = new Random();
		int num = rand.nextInt(28) + 10; // random number from 150 to 500
		StringBuilder builder = new StringBuilder();

		builder.append("https://wordsapiv1.p.mashape.com/words/?partOfSpeech=");
		builder.append(pos);
		builder.append("&limit=500&page=");
		builder.append(num);

		try {
			response = Unirest.get(builder.toString())
					.header("X-Mashape-Key", "LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
					.header("Accept", "application/json").asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		String word = (String) response.getBody().getObject().getJSONObject("results").getJSONArray("data").get(0);
		frame.addRandomWords(word);

	}

	// }

	public static void main(String args[]) throws IOException {

		RandomThread random = new RandomThread("noun", new UIJFrame("Mad Lib Advice From Dad.txt",
				"AdviceFromDadImage.jpeg"));
		random.start();
	}
}