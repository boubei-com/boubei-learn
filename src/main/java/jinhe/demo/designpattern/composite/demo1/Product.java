package jinhe.lt.designpattern.composite.demo1;

/** 
 * <p> Product.java </p> 
 * 
 * @author Jon.King 2006-5-6
 * 
 * Product类就是对应的Leaf类，表示叶子节点，叶子节点没有子节点
 */
public class Product implements SoftwareComponent {
    
    double price = 1200.00;
    
    public double getTotalPrice() {
        return price;
    }

    public SoftwareComponent findSoftwareComponentById(Long id) {
        return new Product();
    }

}

