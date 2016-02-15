package fg.madLibs;

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
		builder.append("&limit=100&page=28");

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
		
		System.out.println(response.getBody().getObject().getJSONObject("results").getJSONArray("data").get(0));
		
		StringBuilder builder2 = new StringBuilder();
		builder2.append("https://wordsapiv1.p.mashape.com/words/");
		builder2.append("red");
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

		String stringResponse = response2.getBody().getArray().getJSONObject(0)
				.getJSONArray("definitions").getJSONObject(0).toString();
		
		System.out.println(stringResponse);
	}

}
