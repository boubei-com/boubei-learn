package com.boubei.learn.yuest.pgsqldb.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*这是使用JDBC基础的类
 * 需要在整个工程下右键属性->java building path ->add external jars->导入相应的数据库驱动包
 * 
 */
public class Jdbcbase {
	static String url="jdbc:postgresql://192.168.163.139:5432/postgres";
	static String usr="postgres";
	static String psd="postgresql.open";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			tJDBC1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//创建一个方法用来实现整个与数据库交互的过程
	static void tJDBC1() throws Exception{
		Connection  conn=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		//注册驱动的多種方式，驅動管理者實際上管理的是drivers的一個vector表格
		//DriverManager.registerDriver(new org.postgresql.Driver());
		//System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
		//增加多個數據庫驅動用“:”分隔
			//推薦Class.froName方式註冊驅動，修改數據庫方便，不會產生垃圾驅動
			//根據類名將類加載到虛擬機中時，會加載靜態代碼塊，在靜態代碼塊中註冊驅動（源碼中有對應實現）
		Class.forName("org.postgresql.Driver");
		
		//建立连接
		//url格式，不同數據庫格式不同
		//JDBC:子協議:子名稱//主機名:端口/數據庫名？屬性名=屬性值&
		//其中localhost:3306數據庫對應自己主機默認端口可缺省，缺省后jdbc:mysql:///jdbc
		conn=DriverManager.getConnection(url,usr,psd);
		
		//创建语句
		st=conn.createStatement();
		
		//执行语句
		//驱动会将结果包装成一个二维表格
		rs= st.executeQuery("select * from public.emp");
//		rs= st.executeQuery("select * from public.employees");
		//处理结果(next相当于向下按记录移动）
		while(rs.next()){
			//getObject 重载方法有两个根据行id来取得对象，根据列名来取得对象。
			System.out.println(rs.getObject(1));
					//+"\t"+rs.getObject(2)+"\t"+
					//rs.getObject(3)+"\t"+rs.getObject(4)+"\t"+rs.getObject(5));
		}
		
		//6释放资源
		//资源利用原则：连接要尽可能晚建，释放要尽可能早释放。
		//rs.close();這樣釋放資源并不完善，如果有程序異常就會停止關閉資源
		//st.close();而在finally中是必須要執行的代碼
		//conn.close();
		
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
			}finally{
				try{
					if(st!=null){
						st.close();
					};
				}finally{
						if(conn!=null){
							conn.close();
					}
				}
			}
		}	
	}
}
