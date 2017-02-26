package com.boubei.learn.jk.annotation.log;


public class Test {
 
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		IDocService service = ProxyService.wrap(new DocService());
		Doc doc = new Doc();
		doc.setDocNo("so-001");
		doc.setId(10000L);
		doc.setDocType("SO");

		service.closeDoc(null, 10000L, doc);
	}

}
