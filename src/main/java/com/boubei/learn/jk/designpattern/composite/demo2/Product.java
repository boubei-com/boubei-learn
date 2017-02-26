package com.boubei.learn.jk.designpattern.composite.demo2;

/** 
 * <p> Product.java </p> 
 * 
 * @author Jon.King 2006-5-6
 * 
 * Product类就是对应的Leaf类，表示叶子节点，叶子节点没有子节点
 */
public class Product implements SoftwareComponent {
    
    double price = 1200.00;
    
    public double getPrice() {
        return price;
    }

    public SoftwareComponent findSoftwareComponentById(Long id) {
        return new Product();
    }
    
    public void accept(Visitor visitor) { 
        visitor.visitProduct(this); 
    } 
}

