package control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import entity.*;

public class ToBean {
	
	/**
	 * convert a book information from json to bean
	 * @param json the string in json format
	 * @return the book converted from the string
	 * @throws JSONException
	 */
	public Book book(String json) throws JSONException
	{
		JSONObject book = new JSONObject(json);
		return object2beanbook(book);
	}
	
	/**
	 * convert a book from json object to bean
	 * @param book the json object contains a book information
	 * @return the bean object of the book
	 * @throws JSONException
	 */
	private Book object2beanbook(JSONObject book) throws JSONException
	{
		Book b = new Book();
        
        JSONObject rating = book.getJSONObject("rating");
        Rating rate = new Rating();
        rate.setMax(rating.getInt("max"));
        rate.setNumRaters(rating.getInt("numRaters"));
        rate.setAverage(rating.getDouble("average"));
        rate.setMin(rating.getInt("min"));
        b.setRating(rate);
        
        org.json.JSONArray tags = book.getJSONArray("tags");
        List<Tag> ts = new ArrayList<Tag>();
        for(int i = 0; i<tags.length(); i++)
        {
        	JSONObject tag = tags.getJSONObject(i);
        	Tag t = new Tag();
        	t.setCount(tag.getInt("count"));
        	t.setName(tag.getString("name"));
        	ts.add(t);
        }
        b.setTags(ts);
        
        org.json.JSONArray authors = book.getJSONArray("author");
        List<String> as = new ArrayList<String>();
        for(int i = 0; i<authors.length(); i++)
        {
        	String a = authors.getString(i);
        	as.add(a);
        }
        b.setAuthor(as);
        
        org.json.JSONArray translators = book.getJSONArray("translator");
        List<String> trs = new ArrayList<String>();
        for(int i = 0; i<translators.length(); i++)
        {
        	String t = translators.getString(i);
        	trs.add(t);
        }
        b.setTranslator(trs);
        
        JSONObject images = book.getJSONObject("images");
        b.setImage_url(images.getString("large"));
        
        b.setId(book.getInt("id"));
        b.setIsbn10(book.getString("isbn10"));
        b.setIsbn13(book.getString("isbn13"));
        b.setTitle(book.getString("title"));
        b.setOrigin_title(book.getString("origin_title"));
        b.setAlt_title(book.getString("alt_title"));
        b.setSubtitle(book.getString("subtitle"));
        b.setUrl(book.getString("url"));
        b.setAlt(book.getString("alt"));
        b.setPublisher(book.getString("publisher"));
        b.setPubdate(book.getString("pubdate"));
        b.setBinding(book.getString("binding"));
        b.setPrice(book.getString("price"));
        b.setPages(book.getString("pages"));
        b.setAuthor_intro(book.getString("author_intro"));
        b.setSummary(book.getString("summary"));
        
        return b;
	}
	
	/**
	 * converts a json string with a search result information to bean
	 * @param json the json string contains search result
	 * @return the bean object of the search result
	 * @throws JSONException
	 */
	public SearchResult searchResult(String json) throws JSONException
	{
		JSONObject searchResult = new JSONObject(json);
		SearchResult s = new SearchResult();
		
		s.setStart(searchResult.getInt("start"));
		s.setCount(searchResult.getInt("count"));
		s.setTotal(searchResult.getInt("total"));
		
		org.json.JSONArray books = searchResult.getJSONArray("books");
		List<SimpleBook> bs = new ArrayList<SimpleBook>();
		for(int i = 0; i<books.length(); i++)
		{
			JSONObject b = books.getJSONObject(i);
			SimpleBook sb = new SimpleBook();
			
        	sb.setId(b.getInt("id"));
        	sb.setTitle(b.getString("title"));
            JSONObject images = b.getJSONObject("images");
            sb.setImage_url(images.getString("small"));
        	bs.add(sb);
		}
		s.setBooks(bs);
		
		return s;
	}
	
	public HttpError error(String json) throws JSONException
	{
		JSONObject err = new JSONObject(json);
		HttpError he = new HttpError();
		
		he.setMsg(err.getString("msg"));
		he.setCode(err.getInt("code"));
		
		return he;
	}

}
