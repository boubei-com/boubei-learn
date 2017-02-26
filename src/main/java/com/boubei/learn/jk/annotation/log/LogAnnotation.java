package com.boubei.learn.jk.annotation.log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
 
    int argNum() default 2;

    String docNoField() default "docNo";
    
    String operateType();
}
