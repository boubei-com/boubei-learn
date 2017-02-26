package com.boubei.learn.jk;

import java.util.ArrayList;
import java.util.List;

/** 
 * <p> Test.java </p> 
 * 
 * @author Jon.King  2006-5-12
 */
public class DeepClone {
	
    /**
     * 用static修饰inner类是因为这样可以在不存在外围类（enclosing class）实例的情况下创建inner类实例
     */
    static class Animal{
        String where;
        Animal(String where){
            this.where = where;
        }
        public String toString(){
            return where;
        }
    }
    
    static class Sheep extends Animal implements Cloneable{
        String name;
        List<Sheep> children = new ArrayList<Sheep>();
        
        Sheep(String name){
            super("land");
            this.name = name;
        }
        
        List<Sheep> getChildren(){
            return children;
        }
        public String toString(){
            return name;
        }
        /* 
         * clone时，对象的List属性的引用被复制给clone出来的对象，需要先将其去掉，避免两个List引用操作同一个List对象
         * 
         */
        public Object clone(){
            try {
                Sheep sheep = (Sheep) super.clone();
                sheep.name = sheep.name + "Copy";
                sheep.children = new ArrayList<Sheep>();
                for(int i = 0; i < this.children.size(); i++){
                    Sheep kitten = (Sheep)this.children.get(i);
                    sheep.children.add((Sheep) kitten.clone());
                }     
                return sheep;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("clone error!", e);
            }
        }
    }
    
    public static void main(String[] args){        
        Sheep mother = new Sheep("mother");
        for(int i = 0; i < 3; i++){
            Sheep kitten = new Sheep("kitten" + i); 
            for(int j = 0; j < 3; j++){
                Sheep son = new Sheep(i + "son" + j); 
                kitten.getChildren().add(son);
            }
            mother.getChildren().add(kitten);
        }  
        Sheep motherCopy = (Sheep)mother.clone();
        
        System.out.println(motherCopy + " : " + mother);
        System.out.println(motherCopy == mother);
        for(int i = 0; i < 3; i++){
            System.out.println(motherCopy.getChildren().get(i)+ " : " + mother.getChildren().get(i));
            System.out.println(motherCopy.getChildren().get(i) == mother.getChildren().get(i));
        }
    }
}
