package fg.madLibs;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MadLibThread extends Thread {

	private String word;
	private String partOfSpeech;
	private UIJFrame frame;
	private String stringResponse;

	public MadLibThread(String word, String partOfSpeech, UIJFrame frame) {
		this.word = word;
		this.partOfSpeech = partOfSpeech;
		this.frame = frame;
	}

	@Override
	public void run() {

		StringBuilder builder = new StringBuilder();
		builder.append("https://wordsapiv1.p.mashape.com/words/");
		builder.append(word);
		builder.append("/definitions");

		HttpResponse<JsonNode> response = null;

		try {
			response = Unirest.get(builder.toString())
					.header("X-Mashape-Key", "LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
					.header("Accept", "application/json").asJson();

		} catch (UnirestException e) {
			e.printStackTrace();
		} catch (org.json.JSONException e2) {
			JOptionPane.showMessageDialog(null, "Invalid Word Entered");
		}
		int size = 0;
		try {
			size = response.getBody().getArray().getJSONObject(0).getJSONArray("definitions").length();
		} catch (NullPointerException e) {

		}

		boolean found = false;

		for (int i = 0; i < size; i++) {
			stringResponse = response.getBody().getArray().getJSONObject(0).getJSONArray("definitions")
					.getJSONObject(i).getString("partOfSpeech");

			if (stringResponse.equalsIgnoreCase(partOfSpeech)) {
				found = true;
				break;
			}
		}

		try {
			frame.checkWord(found);
		} catch (NotEqualsException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {

		}

	}

	public static void main(String[] args) throws IOException {

		new MadLibThread("run", "verb", new UIJFrame("How to Wash Your Face.txt")).start();
	}
}
