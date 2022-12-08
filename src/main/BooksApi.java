package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The BooksApi class
 */
public class BooksApi {
    /**
     * This function takes a author name and returns an ArrayList of Book.
     *
     * @param authorname the author name
     * 
     */
	public ArrayList<Books> getBookByAuthor(String authorname) throws URISyntaxException, ProtocolException, JSONException {
		ArrayList<Books> books = new ArrayList<>();
		try {
			String mainUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:"+authorname ;
			mainUrl = mainUrl.replace(" ","%20");
			URL url = new URL (mainUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			/*
			for (int c; (c = in.read()) >= 0;)
			     sb.append((char)c);
			*/
			String response = sb.toString();
			// System.out.println(response);
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jsonArray = myResponse.getJSONArray("items");
			for(int i = 0;i<10 ; i++) {
				JSONObject firstrow = new JSONObject();
				firstrow = jsonArray.getJSONObject(i);
				JSONObject volumeinfo = new JSONObject();
				volumeinfo = firstrow.getJSONObject("volumeInfo");
			    String  Categorie;
		    	String Author;
		    	try {
		    		JSONArray categ = volumeinfo.getJSONArray("categories");
		    		Categorie = categ.getString(0);
		    	}
		    	catch(JSONException e) {
		    		 Categorie = "Non Clasified";
		    	}
		    		  	
		    	try {
		    		  JSONArray authors = volumeinfo.getJSONArray("authors");
		    		  Author = authors.getString(0);
		    	}
		    	catch(JSONException e) {
		    		  Author = "Unknown";
		    	}
		    	
		    	String date;
		    	try {
		    		date = volumeinfo.getString("publishedDate");
		    		}
		    	catch(JSONException e) {
			    	date = "Unknown";
			    }
					books.add(new Books(i,true,Author,volumeinfo.getString("title"),date,Categorie));
			}
			   /* String id = jsonArray.getJSONObject(0).getString("id");
			    System.out.println(id);
			    JSONObject firstrow = new JSONObject();
			    firstrow = jsonArray.getJSONObject(0);
			    JSONObject volumeinfo = new JSONObject();
			    volumeinfo = firstrow.getJSONObject("volumeInfo");
			    System.out.println(volumeinfo.getString("description"));*/
			   	
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}
	

    /**
     * This function takes a book title and returns an object of Book.
     *
     * @param booktitle the books title
     * 
     */
	public Books getBookByTitle(String booktitle) throws URISyntaxException, ProtocolException, JSONException, ParseException {
		Books bok = new Books();
		try {
			String mainUrl = "https://www.googleapis.com/books/v1/volumes?q=intitle:"+booktitle ;
			mainUrl = mainUrl.replace(" ","%20");
			URL url = new URL (mainUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			/*
			for (int c; (c = in.read()) >= 0;)
				sb.append((char)c);
			*/
			String response = sb.toString();
			// System.out.println(response);
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jsonArray = myResponse.getJSONArray("items");
			String id = jsonArray.getJSONObject(0).getString("id");
			System.out.println(id);
			JSONObject firstrow = new JSONObject();
			firstrow = jsonArray.getJSONObject(0);
			JSONObject volumeinfo = new JSONObject();
			volumeinfo = firstrow.getJSONObject("volumeInfo");
			bok.setTitle(volumeinfo.getString("title"));
			String Category;
			String author;
			try {
				JSONArray categ = volumeinfo.getJSONArray("categories");
			    Category = categ.getString(0);
			}
			catch(JSONException e) {
			    Category = "Not Classified";
			}
			
			try {
			    JSONArray authors = volumeinfo.getJSONArray("authors");
			    author = authors.getString(0);
			}
			catch(JSONException e) {
			    author = "Unknown";
			}
			
			String date;
    		try {
	    		date = volumeinfo.getString("publishedDate");
	    	}
    		catch(JSONException e) {
	    		date = "Unknown";
	    	}
			    
			bok.setCategory(Category);
			bok.setAuthorName(author);
			//String dat = volumeinfo.getString("publishedDate");  
			bok.setCreationDate(date);
			bok.setId(1);
			bok.setAvailable(true);
			   	
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return bok;
	}
	

    /**
     * This function takes a category  and returns an ArrayList of Book.
     *
     * @param Categ category we looking for
     * 
     */
	public ArrayList<Books> getBookByCateg(String Categ) throws URISyntaxException, ProtocolException, JSONException {
		ArrayList<Books> books = new ArrayList<>();
		try {
			String mainUrl = "https://www.googleapis.com/books/v1/volumes?q=subject:"+Categ ;
			mainUrl = mainUrl.replace(" ","%20");
			URL url = new URL (mainUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			/*
			for (int c; (c = in.read()) >= 0;)
			     sb.append((char)c);
			*/
			String response = sb.toString();
			// System.out.println(response);
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jsonArray = myResponse.getJSONArray("items");
			for(int i = 0;i<10 ; i++) {
				JSONObject firstrow = new JSONObject();
				firstrow = jsonArray.getJSONObject(i);
				JSONObject volumeinfo = new JSONObject();
				volumeinfo = firstrow.getJSONObject("volumeInfo");			    		 
			    String  Categorie;
			    String Author;
			    try {
			    	JSONArray categ = volumeinfo.getJSONArray("categories");
			    	JSONArray authors = volumeinfo.getJSONArray("authors");
			    	Author = authors.getString(0);
			    	Categorie = categ.getString(0);
			    }
			    catch(JSONException e) {
			    	Author = "Unknown";
			    	Categorie = "Non Clasified";
			    }
			    
			    String date;
			    try {
				    date = volumeinfo.getString("publishedDate");
				}
			    catch(JSONException e) {
				    date = "Unknown";
				}
				books.add(new Books(i,true,Author,volumeinfo.getString("title"),date,Categorie));
			}
			/*
			String id = jsonArray.getJSONObject(0).getString("id");
			System.out.println(id);
			JSONObject firstrow = new JSONObject();
			firstrow = jsonArray.getJSONObject(0);
			JSONObject volumeinfo = new JSONObject();
			volumeinfo = firstrow.getJSONObject("volumeInfo");
			System.out.println(volumeinfo.getString("description"));
			*/
			   	
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}

}