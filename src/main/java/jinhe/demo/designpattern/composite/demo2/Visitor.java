package jinhe.lt.designpattern.composite.demo2;

/**
 * <p>
 * Visitor.java
 * </p>
 * 
 * @author Jon.King 2006-5-6
 * 
 */
public interface Visitor {
	public void visitBrand(Brand brand);

	public void visitSoftwareSet(SoftwareSet softwareSet);

	public void visitProduct(Product product);
}
