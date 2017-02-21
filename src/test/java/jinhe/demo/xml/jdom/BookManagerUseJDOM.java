package jinhe.lt.xml.jdom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

@SuppressWarnings("unchecked")
public class BookManagerUseJDOM implements BookManager {
	public boolean error;
	Element root; // Root of document
	Document doc; // JDOM document
	List books; // List of Elements
	int index = -1; // index for current Element in "Books"

	/**
	 * 构造方法，创建document
	 */
	public BookManagerUseJDOM() {
		this.buildDocument();
	}

	public void buildDocument() {
		try {
			SAXBuilder builder = new SAXBuilder();
			buildFromDoc(builder.build("src/test/java/jinhe/lt/xml/jdom/books.xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buildFromDoc(Document doc) {
		this.doc = doc;
		root = doc.getRootElement();
		books = root.getChildren();
	}

	// 设置索引
	public void setIndex(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}

	// 获得图书的总量
	public int getNumberOfBooks() {
		return books.size();
	}

	public List getBooks() {
		return books;
	}

	public String getId() {
		Element book = (Element) books.get(index);
		return book.getAttributeValue("id");
	}

	public String getTitle() {
		Element book = (Element) books.get(index);
		return book.getChildText("title");
	}

	public String getLength() {
		Element book = (Element) books.get(index);
		return book.getChildText("length");
	}

	// 获得当前索引的图书的所有作者
	public ArrayList getAuthors() {
		Element book = (Element) books.get(index);
		List authors = book.getChildren("author");
		ArrayList a = new ArrayList(authors.size());
		for (int i = 0; i < authors.size(); i++) {
			Element author = (Element) authors.get(i);
			a.add(author.getText());
		}
		return a;
	}

	public String getAuthorLines() {
		ArrayList a = getAuthors();
		String s = "";
		for (int i = 0; i < a.size(); i++) {
			if (!s.equals(""))
				s += "\n";
			s += a.get(i);
		}
		return s;
	}

	// 更新某本图书
	public void update(Book bookv) {
		Element book = (Element) books.get(index);
		book.setAttribute(new Attribute("id", bookv.getId()));
		book.addContent(new Element("title").setText(bookv.getTitle()));
		book.addContent(new Element("length").setText(bookv.getLength()));
		book.removeChild("author");
		for (int i = 0; i < bookv.getAuthors().size(); i++) {
			book.addContent(new Element("author").setText((String) bookv
					.getAuthors().get(i)));
		}
	}

	// 创建一本新的图书
	public void create(Book bookv) {
		Element book = new Element("book");
		book.setAttribute(new Attribute("id", bookv.getId()));
		book.addContent(new Element("title").setText(bookv.getTitle()));
		book.addContent(new Element("length").setText(bookv.getLength()));
		for (int i = 0; i < bookv.getAuthors().size(); i++) {
			book.addContent(new Element("author").setText((String) bookv
					.getAuthors().get(i)));
		}
		books.add(book);
	}

	// 对图书的标题进行排序
	public void sort() {
		// sort does not work with JDOM beta 8
		List x = new ArrayList(books);
		Collections.sort(x, new SortTitle());
		books.clear();
		books.addAll(x);
	}

	// 保存图书信息到文件
	public void save() {
		XMLOutputter outputter = new XMLOutputter();
		// XMLOutputter outputter = new XMLOutputter(" ",true);
		try {
			// outputter.setTextNormalize(true);
			FileWriter f = new FileWriter(new File("books.xml"));
			outputter.output(doc, f);
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获得所有图书的标题
	public Vector getTitles() {
		Vector ret = new Vector();
		Iterator it = books.iterator();

		while (it.hasNext()) {
			Element book = (Element) it.next();

			ret.add(book.getChildText("title"));
		}

		return ret;
	}

	public boolean error() {
		return error;
	}

	// 返回指定图书的详细信息
	public Book getDetails(int i) {

		index = i;
		Element book = (Element) books.get(i);
		Book bookv = new Book();
		bookv.setId(book.getAttribute("id").getValue());
		bookv.setTitle(book.getChildText("title"));
		bookv.setLength(book.getChildText("length"));
		List authors = book.getChildren("author");
		ArrayList a = new ArrayList(authors.size());
		for (int j = 0; j < authors.size(); j++) {
			Element author = (Element) authors.get(j);
			a.add(author.getText());
		}

		bookv.setAuthors(a);
		return bookv;

	}

	// 删除图书
	public void delete() {
		books.remove(index);
	}

	public String getErrorText() {
		return null;
	}
}

/**
 * 工具类，用于对title进行排序
 */
@SuppressWarnings("unchecked")
class SortTitle implements Comparator {
	public int compare(Object o1, Object o2) {
		Element d1 = (Element) o1;
		Element d2 = (Element) o2;
		return (d1.getChildText("title")).compareToIgnoreCase(d2.getChildText("title"));
	}
}