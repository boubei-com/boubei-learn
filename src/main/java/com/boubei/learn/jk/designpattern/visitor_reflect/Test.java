package com.boubei.learn.jk.designpattern.visitor_reflect;

import java.util.ArrayList;
import java.util.List;

import com.boubei.learn.jk.reflect.User;

/** 
 * <p> Test.java </p> 
 * 
 * @author 金普俊 2006-4-25
 *
 */

public class Test {

    @SuppressWarnings("unchecked")
	public static void main(String[] args){
        List list = new ArrayList();
        list.add("jinpj");
        list.add(new Float(12.0));
        
        User user = new User();
        user.setName("jinpj");
        list.add(user);
        
        list.add(null);
        list.add(new Long(1200));
        
        PrintVisitor visitor = new PrintVisitor();
        visitor.visitCollection(list);
    }
}

