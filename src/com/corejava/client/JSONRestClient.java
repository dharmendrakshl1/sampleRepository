package com.corejava.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JSONRestClient {

	public void netClientJSONGET()
	{
		try{
			URL url = new URL("http://localhost:8080/RESTJerseyExample/restservice/ftocservice");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			//connection.setRequestProperty("Accept", "text/plain");
			
			if(connection.getResponseCode() != 200)
			{
				throw new RuntimeException("Failed: Http Error Code : "+connection.getResponseCode()+" : "+connection.getResponseMessage());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String jsonOutput = br.readLine();
			while(jsonOutput != null)
			{
				System.out.println(jsonOutput);
				jsonOutput = br.readLine();
			}
			
			connection.disconnect();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void netClientJSONPOST()
	{
		try{
			//URL url = new URL("http://localhost:8080/RESTJerseyExample/restservice/ftocservice/50"); //In Web Service Method is GET
			//URL url = new URL("http://localhost:8080/RESTJerseyExample/restservice/ftocservice/convert?fheit=50"); //In Web Service Method is GET
			URL url = new URL("http://localhost:8080/RESTJerseyExample/restservice/ftocservice/jsoninput");//In Web Service Method is POST
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			
			//Below commented code is to pass as JSON object in Request Parameter(As as Input)
			String input = "{\"fahrenheit\":45,\"celsius\":\"0\"}";
			OutputStream os = connection.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			
			if(connection.getResponseCode() != HttpURLConnection.HTTP_CREATED && !(connection.getResponseCode() == HttpURLConnection.HTTP_OK))
			{
				throw new RuntimeException("Failed: Http Error Code : "+connection.getResponseCode()+" : "+connection.getResponseMessage());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String jsonOutput = br.readLine();
			while(jsonOutput != null)
			{
				System.out.println(jsonOutput);
				jsonOutput = br.readLine();
			}
			
			connection.disconnect();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONRestClient jrs = new JSONRestClient();
		//jrs.netClientJSONGET();
		jrs.netClientJSONPOST();
	}
}
