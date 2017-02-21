package jinhe.lt.designpattern.composite.demo2;

/**
 * <p>
 * CaculateTotalPriceVisitor.java
 * </p>
 * 
 * @author Jon.King 2006-5-6
 * 
 */
public class CaculateTotalPriceVisitor implements Visitor {
    private double totalPrice;

    public void visitBrand(Brand brand) {
    }

    public void visitSoftwareSet(SoftwareSet softwareSet) {
    }

    public void visitProduct(Product product) {
        // 每次在组合的结构中碰到Product对象节点的时候，就会调用此方法
        totalPrice += product.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public static void main(String[] args){
        CaculateTotalPriceVisitor visitor = new CaculateTotalPriceVisitor(); 
//      将该visitor对象传到结构中 
        SoftwareSet data = new SoftwareSet();
        data.accept(visitor); 
//      调用visitor对象的getTotalPrice()方法就返回了总价格 
        double price = visitor.getTotalPrice(); 
        System.out.println(price);
    }
    /*
     * 在类SoftwareManager中的main方法中，调用软件集对象（data）的
     * accept方法，并将生成的visitor对象传给它。 accept方法开始递归
     * 调用各个子对象的accept方法。如果当前的对象是SoftwareSet的实例，
     * 则调用visitor对象visitSoftwareSet方法，在visitor对象中对该
     * 节点的数据进行一些处理，然后返回；依次类推，遍历到Brand对象和
     * Product对象也与此类似。当前的逻辑是计算软件产品的总价格，因此
     * 当遍历到Product对象的时候，取出产品的价格并且累加，最后当结构
     * 遍历完毕后，调用visitor对象的getTotalPrice方法返回给定软件集
     * 对象的（data）的总的价格。如果需要加入一个新的计算逻辑，只实现
     * Visitor接口，并且将该类的实例传给data对象的accept方法就可以
     * 实现不同的逻辑方法了。
     */
}
