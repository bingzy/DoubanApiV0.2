package control;

import java.util.List;

import org.json.JSONException;

import entity.*;

public class Service {
	private String base_url = "https://api.douban.com/v2/";
	private String book_url = "book/";
	public int searchCount = 10;
	
	public boolean status = false;
	public String errMsg = "Oops! An unexpected error occured.";
	
	private String getRequest(String request_url)
	{
		HTTP http = new HTTP();
		String json = http.GET(request_url);
		int rCode = http.responseCode;
		if(rCode==200 || rCode==201 || rCode==202)
		{
			status = true;
			return json;
		}
		
		else
		{
			status = false;
//			ToBean tb = new ToBean();
//			try {
//				HttpError he = tb.error(json);
//				errMsg = rCode + he.getMsg();
//				return null;
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return null;
	}
	
	/**
	 * get a book through its ID
	 * @param id the id of the book
	 * @return the book with that id
	 */
	public Book getBookByID(int id)
	{
		String request_url = base_url + book_url + id;
		String json = getRequest(request_url);
		if(json != null)
		{
			ToBean toBean = new ToBean();
			try
			{
				return toBean.book(json);
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else return null;
		
	}
	
	/**
	 * get a book through its ISBN
	 * @param isbn the id of the book
	 * @return the book with that isbn
	 */
	public Book getBookByISBN(String isbn)
	{
		String request_url = base_url + book_url + "isbn/" + isbn;
		String json = getRequest(request_url);
		if (json != null)
		{
			ToBean toBean = new ToBean();
			try
			{
				return toBean.book(json);
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else return null;
	}
	
	/**
	 * search for books through key or tag
	 * @param key key of this search request
	 * @param start offset of the search result
	 * @param type true for search for key, false for search for tags
	 * @return the search result of this request
	 */
	public SearchResult searchBookByKeyOrByTag(String key, int start, boolean type)
	{
		//type = true search for key
		//type = false search for tag
		String request_url = null;
		if(type==true)
		{
			request_url = base_url + book_url + "search/?q=" + key + "&count=" + searchCount + "&start=" + start;
			//System.out.println(request_url);
		}
		else
		{
			request_url = base_url + book_url + "search/?tag=" + key + "&count=" + searchCount + "&start=" + start;
		}
		
		String json = getRequest(request_url);
		if (json == null) return null;
		
		ToBean toBean = new ToBean();
		try
		{
			return toBean.searchResult(json);
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
