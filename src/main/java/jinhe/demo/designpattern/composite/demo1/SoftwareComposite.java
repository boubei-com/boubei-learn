package jinhe.lt.designpattern.composite.demo1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * <p> AbsSoftwareComposite.java </p> 
 * 
 * @author Jon.King 2006-5-6
 *
 * SoftwareComposite类对应于Composite类，并且是抽象类，所有可以包含子节点的类都扩展这个类。
 * 这个类的主要功能是用来存储子部件，实现了接口中的方法，部分可以重用的代码写在此类中
 */
@SuppressWarnings("unchecked")
public abstract class SoftwareComposite implements SoftwareComponent {
    
	private List children = new ArrayList();
    
    public void addSoftwareComponent(SoftwareComponent s) {
        children.add(s);
    }

    public void removeSoftwareComponent(SoftwareComponent s) {
        children.remove(s);
    }

    public abstract SoftwareComponent findSoftwareComponentById(Long id);
    
    /* 
     * 由于类Brand和SoftwareSet都继承了SoftwareComposite，我们只需在类
     * SoftwareComposite中实现该方法getTotalPrice方法即可
     * 
     */
    public  double getTotalPrice(){       
        double price = 0;         
        for (Iterator it = children.iterator(); it.hasNext(); ) { 
            SoftwareComponent softwareComponent = (SoftwareComponent) it.next(); 
            //自动递归调用各个对象的getTotalPrice方法并累加 
            price += softwareComponent.getTotalPrice(); 
        } 
        return price; 
    }
}

