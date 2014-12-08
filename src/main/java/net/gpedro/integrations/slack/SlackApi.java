package net.gpedro.integrations.slack;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonObject;


public class SlackApi {

	private String team;
	private String token;
	
	public SlackApi(String team, String token) {
		if(team == null || token == null) {
			throw new IllegalArgumentException("Missing Team or Token Configuration @ SlackApi");
		}
		
		if(token != null && token.length() != 24) {
			throw new IllegalArgumentException("Token should be 24 characters long @ SlackApi");
		}
		
		this.team  = team;
		this.token = token;
	}
	
	public void call(SlackMessage message) {
		if(message != null) {
			this.send(message.prepare());
		}
	}
	
	private String getService() {
		return "https://" + team + ".slack.com/services/hooks/incoming-webhook?token=" + token;
	}
	
	private String send(JsonObject message) {
		URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(this.getService());
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setConnectTimeout(5000);
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      String payload = "payload="+URLEncoder.encode(message.toString(), "UTF-8");
	      
	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes(payload);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      
	      System.out.println(response.toString());
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	}
	
}
