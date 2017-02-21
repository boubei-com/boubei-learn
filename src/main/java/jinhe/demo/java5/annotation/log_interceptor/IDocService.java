package jinhe.lt.java5.annotation.log_interceptor;

public interface IDocService {

    @LogAnnotation(argNum = 2, docNoField = "docNo", operateType = "closeSo")
    Doc closeDoc(String token, Long id, Doc doc);
}
