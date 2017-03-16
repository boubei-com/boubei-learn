package com.boubei.learn.hank;

//class FreshJuice {
//	enum FreshJuiceSize{ SMALL, MEDIUM , LARGE }
//    FreshJuiceSize size;
//}
//
//public class RuNoob {
//	public static void main(String []args){
//		FreshJuice juice = new FreshJuice();
//		juice.size = FreshJuice. FreshJuiceSize.MEDIUM  ;
//		System.out.print(juice.size);
//	}
//}

//public class RuNoob{
//   public RuNoob(String name){
//      //这个构造器仅有一个参数：name
//      System.out.println("小狗的名字是 : " + name ); 
//   }
//   public static void main(String []args){
//      // 下面的语句将创建一个RuNoob对象
//	   RuNoob myRuNoob = new RuNoob( "tommy" );
//   }
//}

public class RuNoob{
   int RuNoobAge;
   public RuNoob(String name){
      // 这个构造器仅有一个参数：name
      System.out.println("小狗的名字是 : " + name ); 
   }
 
   public void setAge( int age ){
       RuNoobAge = age;
   }
 
   public int getAge( ){
       System.out.println("小狗的年龄为 : " + RuNoobAge ); 
       return RuNoobAge;
   }
 
   public static void main(String []args){
      /* 创建对象 */
      RuNoob myRuNoob = new RuNoob( "tommy" );
      /* 通过方法来设定age */
      myRuNoob.setAge( 2 );
      /* 调用另一个方法获取age */
      myRuNoob.getAge( );
      /*你也可以像下面这样访问成员变量 */
      System.out.println("变量值 : " + myRuNoob.RuNoobAge ); 
   }
}