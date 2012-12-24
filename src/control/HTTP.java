/**
 * This class deals with the connection problems.
 * Problems:
 * The post method has not been tested.
 * Chinese character encoding problem.
 * Problems when dealing with exceptions.
 */
package control;

import java.net.*;
import java.io.*;

public class HTTP
{	
	protected URL target;
	protected int responseCode=-1;
	private String responseMessage="";
	private HttpURLConnection connection;
	private BufferedReader reader;
	
	public HTTP() { }
	public HTTP(String url)
	{
		GET(url) ;
	}
	
	/**
	 * the get method
	 * @param url the url to be requested
	 * @return the response message of the request
	 */
	@SuppressWarnings("finally")
	public String GET(String url)
	{	
		try
		{
			target = new URL(url);
			connection = (HttpURLConnection) target.openConnection();
			connection.connect();
			responseCode = connection.getResponseCode();
			//reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			//System.out.println("#1");/////////
			responseMessage = reader.readLine();
			//System.out.println("#2"+responseMessage);////////////
			reader.close();
		}
		catch(ProtocolException p)
		{
			p.printStackTrace();
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
		finally
		{
			connection.disconnect();
			return responseMessage;
		}

	}

	/**
	 * the post method
	 * @param url the requested url
	 * @param content the content to be posted
	 * @return the response message of the request
	 */
	public String POST(String url,String content)
	{
		try
		{
			target = new URL(url);
			connection = (HttpURLConnection) target.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			content = URLEncoder.encode(content, "utf-8");
			out.writeBytes(content); 
			out.flush();
			out.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			responseMessage = reader.readLine();
			reader.close();
			connection.disconnect();
			return responseMessage;

		}
		catch(ProtocolException p)
		{
			p.printStackTrace();
			return null;
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(IOException i)
		{
			i.printStackTrace();
			return null;
		}
	}

}