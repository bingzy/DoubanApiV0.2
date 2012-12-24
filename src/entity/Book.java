package entity;

import java.util.List;

public class Book {
	private int id;
	private String isbn10;
	private String isbn13;
	private String title;
	private String origin_title;
	private String alt_title;
	private String subtitle;
	private String url;
	private String alt;
	private String image_url;
	private List<String> author;
	private List<String> translator;
	private String publisher;
	private String pubdate;
	private Rating rating;
	private List<Tag> tags;
	private String binding;
	private String price;
	private String pages;
	private String author_intro;
	private String summary;
	
	public void getInfo()
	{
		System.out.println("id:"+id);
		System.out.println("isbn10:"+isbn10);
		System.out.println("isbn13:"+isbn13);
		System.out.println("title:"+title);
		System.out.println("origin_title:"+origin_title);
		System.out.println("alt_title:"+alt_title);
		System.out.println("subtitle:"+subtitle);
		System.out.println("url:"+url);
		System.out.println("alt:"+alt);
		System.out.println("image_url:"+image_url);
		System.out.println("author:"+author);
		System.out.println("translator:"+translator);
		System.out.println("publisher:"+publisher);
		System.out.println("pubdate:"+pubdate);
		System.out.println("rating:");
			System.out.println("\tnumraters:"+rating.getNumRaters());
			System.out.println("\taverage:"+rating.getAverage());
			System.out.println("\tmax:"+rating.getMax());
			System.out.println("\tmin:"+rating.getMin());
		System.out.println("tags:");
			for(int i = 0; i<tags.size(); i++)
			{
				System.out.println("\t"+tags.get(i).getName()+"("+tags.get(i).getCount()+")");
			}
		System.out.println("binding:"+binding);
		System.out.println("price:"+price);
		System.out.println("pages:"+pages);
		System.out.println("author_intro:"+author_intro);
		System.out.println("summary:"+summary);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn10() {
		return isbn10;
	}
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrigin_title() {
		return origin_title;
	}
	public void setOrigin_title(String origin_title) {
		this.origin_title = origin_title;
	}
	public String getAlt_title() {
		return alt_title;
	}
	public void setAlt_title(String alt_title) {
		this.alt_title = alt_title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public List<String> getAuthor() {
		return author;
	}
	public void setAuthor(List<String> author) {
		this.author = author;
	}
	public List<String> getTranslator() {
		return translator;
	}
	public void setTranslator(List<String> translator) {
		this.translator = translator;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getAuthor_intro() {
		return author_intro;
	}
	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getAuthorString()
        {
            StringBuffer authorStr = new StringBuffer();
            for(int i = 0; i<author.size(); i++)
            {
                authorStr.append(author.get(i));
                authorStr.append(" ");
            }
            return authorStr.toString();
        }
        
        public String getTranslatorString()
        {
            StringBuffer transStr = new StringBuffer();
            for(int i = 0; i<translator.size(); i++)
            {
                transStr.append(translator.get(i));
                transStr.append(" ");
            }
            return transStr.toString();
        }
        
        public String getTagsString()
        {
            StringBuffer tagsStr = new StringBuffer();
            for(int i = 0; i<tags.size(); i++)
            {
                Tag t = tags.get(i);
                tagsStr.append(t.getName());
                tagsStr.append("("+t.getCount()+")");
                tagsStr.append(" ");
            }
            return tagsStr.toString();
        }
        

}
