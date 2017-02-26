package com.boubei.learn.jk.annotation.log;

public class Doc implements IDoc{
    
    private Long id;
    
    private String docNo;
    
    private String docType;

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }
}
