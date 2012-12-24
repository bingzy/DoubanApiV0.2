package entity;

public class HttpError {
	private String msg;
	private int code;
	
	public String getMsg() {
		switch(code)
		{
			case 999: msg = "Unknow Error Occured."; break;
			case 1000: msg = "Permission Needed."; break;
			case 1001: msg = "Oops. URI Not Found."; break;
			case 1002: msg = "Argument Missing."; break;
			case 1003: msg = "Image Too Large."; break;
		}
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	

}
