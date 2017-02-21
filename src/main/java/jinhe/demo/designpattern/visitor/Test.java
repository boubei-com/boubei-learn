/* ==================================================================   
 * Created [2006-4-25 23:28:57] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
*/

package jinhe.lt.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

import jinhe.lt.designpattern.visitor.impl.PrintVisitor;
import jinhe.lt.designpattern.visitor.impl.VisitableFloat;
import jinhe.lt.designpattern.visitor.impl.VisitableString;
import jinhe.lt.designpattern.visitor.impl.VisitableUser;
import jinhe.lt.reflect.User;

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
        list.add(new VisitableString("jinpj"));
        list.add(new VisitableFloat(new Float(12.0)));
        
        User user = new User();
        user.setName("jinpj");
        list.add(new VisitableUser(user));
        
        PrintVisitor visitor = new PrintVisitor();
        visitor.visitCollection(list);
    }
}

