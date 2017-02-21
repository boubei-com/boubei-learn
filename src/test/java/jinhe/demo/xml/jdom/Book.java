package jinhe.lt.xml.jdom;

import java.util.List;

/**
 * book是一个值对象
 */
public class Book implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 属性
	 */
	private String id;
	private String title;
	private String length;
	private List<String> authors;

	/**
	 * 属性的getter和setter方法
	 */
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLength() {
		return length;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setId(String newid) {
		id = newid;
	}

	public void setTitle(String newtitle) {
		title = newtitle;
	}

	public void setLength(String newlength) {
		length = newlength;
	}

	public void setAuthors(List<String> newauthors) {
		authors = newauthors;
	}

}