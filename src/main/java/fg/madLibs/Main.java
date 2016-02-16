package fg.madLibs;

import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();

		HttpResponse<JsonNode> response = null;

		builder.append("https://wordsapiv1.p.mashape.com/words/?partOfSpeech=");
		builder.append("adverb");
		builder.append("&limit=500&page=28");

		try {
			response = Unirest
					.get(builder.toString())
					.header("X-Mashape-Key",
							"LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
					.header("Accept", "application/json").asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(response.getBody().getObject()
				.getJSONObject("results").getJSONArray("data").get(0));

		StringBuilder builder2 = new StringBuilder();
		builder2.append("https://wordsapiv1.p.mashape.com/words/");
		builder2.append("run");
		builder2.append("/definitions");

		HttpResponse<JsonNode> response2 = null;
		try {
			response2 = Unirest
					.get(builder2.toString())
					.header("X-Mashape-Key",
							"LsvNmn9sVvmshJNr08Cav83z1Eovp1BNciPjsnA0yzYSlgfJOE")
					.header("Accept", "application/json").asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		/**
		String stringResponse = null;

		int counter = 0;
		while ((stringResponse != null)) {
			stringResponse = response.getBody().getArray().getJSONObject(0)
					.getJSONArray("definitions").getJSONObject(counter)
					.getString("partOfSpeech");

			System.out.println(stringResponse);
			if ((stringResponse.equals("noun"))) {
				break;
			}
			counter++;
			

		}
		*/
		
		String res = response2.getBody().getArray().getJSONObject(0)
				.getJSONArray("definitions").getJSONObject(0).getString("partOfSpeech");
		System.out.println(res);
		
		//System.out.println(stringResponse);
		// frame.checkWord(partOfSpeech, stringResponse);
		int size = response2.getBody().getArray().getJSONObject(0)
				.getJSONArray("definitions").length();
		
		ArrayList<String> definitions = new ArrayList<String>();
		boolean found = false;
		for (int i = 0; i < size; i++){
			String resp = response2.getBody().getArray().getJSONObject(0)
					.getJSONArray("definitions").getJSONObject(i).getString("partOfSpeech");
			if(resp.equalsIgnoreCase("verb")){
			found = true;
			System.out.println(resp);
			break;
			
			}
			
		}
		
		System.out.println(definitions);
		
		for(int i = 0; i < definitions.size(); i++){
			if(definitions.get(i).equals("verb")){
				String yes = definitions.get(i);
				System.out.println(yes);
			}
		}
	}
	// .getJSONObject(0)
	// .getJSONArray("definitions").getJSONObject(0).toString()

}
