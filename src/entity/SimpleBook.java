package entity;

public class SimpleBook {
	private int id;
	private String title;
	private String image_url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		if (title.length()<=20)
			return title;
		else
			return title.substring(0,18)+"...";
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
