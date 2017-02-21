package jinhe.lt.datastructure;

/** 
 * <p> Iterator.java </p> 
 * 
 * @author Jon.King 2006-5-17
 *
 */

public interface Iterator {

    boolean hasNext();
    
    Object next();
    
    void remove();
}

