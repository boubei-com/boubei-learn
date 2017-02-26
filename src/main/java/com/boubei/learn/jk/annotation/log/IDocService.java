package com.boubei.learn.jk.annotation.log;

public interface IDocService {

    @LogAnnotation(argNum = 2, docNoField = "docNo", operateType = "closeSo")
    Doc closeDoc(String token, Long id, Doc doc);
}
