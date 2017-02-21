package jinhe.lt.xml.jdom;

import java.util.Vector;

/**
 * 定义了图书管理的业务逻辑
 */
@SuppressWarnings("unchecked")
public interface BookManager {
	
	public boolean error();

	public Vector getTitles();// 返回所有图书的标题

	public void save();// 保存图书信息

	public Book getDetails(int i);// 获得某本图书的详细信息

	public void delete();// 删除某本图书

	public void update(Book book);// 更新图书信息

	public void create(Book book);// 创建一本新的图书

	public String getErrorText();// 获得错误信息

	public void setIndex(int i);// 设置当前图书的索引

}