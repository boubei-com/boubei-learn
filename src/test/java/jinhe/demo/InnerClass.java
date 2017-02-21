package jinhe.lt;

/** 
 * <p> Test.java </p> 
 * 
 * @author Jon.King  2006-5-12
 */
public class InnerClass {

    private String id = "ajun learn rock-and-roll!";
    private String xxxx;

    class Y{
        private String id = InnerClass.this.id;   //内部类可以通过此方式引用外围类的属性和方法,InnerClass.this可获取外围类的引用
        protected String name;
        protected String getWhere(){
            xxxx = "$jinpujun";                         //内部类的方法中可直接给外围类的属性赋值
            return id + xxxx +" on land";
        }
        void test() throws CloneNotSupportedException{
            super.clone();
            this.clone();  
        }  
    }
    class YY extends Y{
        void test(){
            super.getWhere();
            this.getWhere();
        }
    }
    class X{
        Y a = new Y();
        YY b = new YY();
        void test(){
            //a.clone();           //protected修饰的方法同package和子类可以访问,YY无法调用Y的clone()方法．?
            a.getWhere();          //因为X和YY在同一个包里，可以调用。而Object和X不在同一包里，故不行
            b.getWhere();
        }
    }
    
    public static void main(String[] args){  
        InnerClass test = new InnerClass();
        InnerClass.Y y = test.new Y();
        System.out.println(y.getWhere());
    }
}
