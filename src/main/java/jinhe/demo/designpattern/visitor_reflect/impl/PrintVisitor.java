package jinhe.lt.designpattern.visitor_reflect.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import jinhe.lt.designpattern.visitor_reflect.ReflectiveVisitor;
import jinhe.lt.reflect.User;

/**
 * <p>
 * PrintVisitor.java
 * </p>
 * 
 * @author Jon.King 2006-4-26
 * 
 */
@SuppressWarnings("unchecked")
public class PrintVisitor implements ReflectiveVisitor {
    
	public void visitCollection(Collection collection) {
        for (Iterator it = collection.iterator(); it.hasNext();) {
            visit(it.next());
        }
    }

    public void visitString(String string) {
        System.out.println("'" + string + "'");
    }

    public void visitFloat(Float f) {
        System.out.println(f.toString() + "f");
    }

    public void visitUser(User user) {
        System.out.println(user.toString());
    }

    /**
     * 默认调用Object的toString()
     * 
     * @param o
     */
    public void visitObject(Object o) {
        System.out.println(o.toString());
    }
    
    public void visitNull(){
        System.out.println("被访问的对象为null值！");
    }

    public void visit(Object o) {
        if(o == null){
            visitNull();
            return;
        }
        try {
            Method method = getMethod(o.getClass());
            method.invoke(this, new Object[] { o });
        } catch (Exception e) { 
            throw new RuntimeException("拦截方法时出错", e);
        }
        
        /* 
         * 不用getMethod方法时实现如下，只在本类中找
         * 
            String methodName = o.getClass().getName(); //连对象所在的包名一块返回的，例：com.jinpj.visitor.User
            methodName = "visit" + methodName.substring(methodName.lastIndexOf('.') + 1);
            try {
                // Get the method visitFoo(Foo foo)
                Method m = getClass().getMethod(methodName, new Class[] { o.getClass() });
                m.invoke(this, new Object[] { o });
            } catch (Exception e) {           
                visitObject(o);  // No method, so do the default implementation
            } 
        */     
    }
    
    /**
     * 扩展visit()方法，使得它也能够处理所有的父类。
     * 首先，得增加一个新方法，称为getMethod(Class c)，它返回的是要调用的方法；
     * 为了找到这个相匹配的方法，根据传进来的类名去寻找相应的方法，如果没找到，就在父类中找；
     * 还没找到，再到接口中找。最后，就拿visitObject()作为缺省。
     * 
     * @param clazz
     * @return
     */
    protected Method getMethod(Class clazz) {
        Class newClazz = clazz;
        Method m = null;
        
        /* 
         * 先在this类中找，找不到再在父类中找，注意：此处是 while(){...}循环
         */
        while (m == null && (newClazz != Object.class)) {
           String method = newClazz.getName();
           method = "visit" + method.substring(method.lastIndexOf('.') + 1);
           try {
              m = this.getClass().getMethod(method, new Class[] {newClazz});
           } catch (NoSuchMethodException e) {
              newClazz = newClazz.getSuperclass(); //******
           }
        }
        
        /*
         * 经过上面的while循环，newClazz已经是this类的最顶层父类了
         * 判断父类是否为Object.class，true则再在接口里找
         */   
        if (newClazz == Object.class) {
           Class[] interfaces = clazz.getInterfaces();
           for (int i = 0; i < interfaces.length; i++) {
              String method = interfaces[i].getName();
              method = "visit" + method.substring(method.lastIndexOf('.') + 1);
              try {
                 m = getClass().getMethod(method, new Class[] {interfaces[i]});
              } catch (NoSuchMethodException e) {
                 //即使抛出异常也不管，继续往下
              }
           }
        }
        
        if (m == null) {
           try {
              m = this.getClass().getMethod("visitObject", new Class[] {Object.class});
           } catch (Exception e) {
               //此处不可能抛出异常， 因为默认的visitObject总是存在
           }
        }
        return m;
     }

}

/*
 * 上面对Visitable接口避而不谈自有原因。传统Visitor模式的另一个好处是，它允许Visitable对象来控制 对 对象结构的访问。
    例如，假设有一个实现了Visitable的TreeNode对象，你可以让一个accept()方法来遍历它的左右节点：
    
    public void accept(Visitor visitor) {
       visitor.visitTreeNode(this);
       visitor.visitTreeNode(leftsubtree);
       visitor.visitTreeNode(rightsubtree);
    }
    
    这样，只用对Visitor类再进行一点修改，就可以进行Visitable控制访问：
    
    public void visit(Object object) throws Exception
    {
         Method method = getMethod(getClass(), object.getClass());
         method.invoke(this, new Object[] {object});
         if (object instanceof Visitable)
         {
              callAccept((Visitable) object);
         }
    }
    public void callAccept(Visitable visitable) {
       visitable.accept(this);
    }
    
    如果已经实现了一个Visitable对象结构，可以保留callAccept()方法并使用Visitable控制访问。
    如果想在visitor中访问结构，只需改写callAccept()方法，使之什么也不做。
    
    想让数个不同的访问者对同一个对象集合进行访问时，Visitor模式可以发挥它的强大作用。
    假设已经有一个解释器，一个中缀写作器，一个后缀写作器，一个XML写作器和一个SQL写作器，它们都作用在同一个对象集合上。
    那么，也可以很容易地为相同的对象集合写出一个前缀写作器和一个SOAP写作器。另外，这些写作器可以正常地和它们所不知道的对象工作；
    当然，如果愿意，也可以让它们抛出异常。
*/
