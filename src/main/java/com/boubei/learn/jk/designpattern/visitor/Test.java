/* ==================================================================   
 * Created [2006-4-25 23:28:57] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
*/

package com.boubei.learn.jk.designpattern.visitor;

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

	public static void main(String[] args){
        List<Object> list = new ArrayList<Object>();
        list.add(new VisitableString("jinpj"));
        list.add(new VisitableFloat(new Float(12.0)));
        
        User user = new User();
        user.setName("jinpj");
        list.add(new VisitableUser(user));
        
        PrintVisitor visitor = new PrintVisitor();
        visitor.visitCollection(list);
    }
	
}

