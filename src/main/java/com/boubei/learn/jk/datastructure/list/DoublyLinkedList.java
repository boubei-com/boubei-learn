package com.boubei.learn.jk.datastructure.list;

import com.boubei.learn.jk.datastructure.Iterator;

/**
 * <p>
 * DoublyLinkedList.java
 * </p>
 * 双向列表
 * 
 * @author Jon.King 2006-5-17
 * 
 */
public class DoublyLinkedList {
	
	static class  Link {
	    public double dData;  

	    public Link next;  

	    public Link previous; 

	    public Link(double d){
	        dData = d;
	    }

	    public void displayLink(){
	        System.out.print(dData + " ");
	    }
	}
	
    private Link first;

    private Link last;

    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(double dd) {
        Link newLink = new Link(dd);
        if (isEmpty())
            last = newLink;  //first=last=null -->first=last=newLink
        else{
            first.previous = newLink;
            newLink.next = first;
        }           
        first = newLink;
    }

    public void insertLast(double dd) {
        Link newLink = new Link(dd);
        if (isEmpty())
            first = newLink;
        else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    public Link deleteFirst() {
        Link temp = first;
        if (first.next == null)
            last = null;
        else
            first.next.previous = null;
        first = first.next;
        return temp;
    }

    public Link deleteLast() {
        Link temp = last;
        if (last.previous == null)
            first = null;
        else
            last.previous.next = null;
        last = last.previous;
        return temp;
    }

    public boolean insertAfter(double key, double dd) {
        Link current = first;
        
        while (current.dData != key) {
            current = current.next;
            if (current == null)
                return false;
        }
        Link newLink = new Link(dd);
        if (current == last) {
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current;
        current.next = newLink;
        return true;
    }

    public Link deleteKey(double key) {
        Link current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null)
                return null;
        }
        if (current == first)
            first = current.next;
        else
            current.previous.next = current.next;
        
        if (current == last)
            last = current.previous;
        else
            current.next.previous = current.previous;
        return current;
    }

    public void displayForward() {
        System.out.print("List (first-->last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public void displayBackward() {
        System.out.print("List (last-->first): ");
        Link current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println("");
    }
    
    public Iterator iterator(){
        return new LinkIterator(first);
    }
    
    private class LinkIterator implements Iterator{
        Link current = null;
        
        public LinkIterator(Link current){
            this.current = current;
        }
        
        public boolean hasNext() {           
            return current.next != null;
        }

        public Object next() {
            current = current.next;
            return current;
        }

        public void remove() {
            if(current == first){
                first = current.next;
            }else{
                current.previous.next = current.next;
            }
            
            if(current == last){                
                last = current.previous;
            }else{
                current.next.previous = current.previous;
            }
        }
        
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(22);
        list.insertFirst(44);
        list.insertFirst(66);
        list.insertLast(11);
        list.insertLast(33);
        list.insertLast(55);
        list.displayForward();
        list.displayBackward();
        list.deleteFirst();
        list.deleteLast();
        list.deleteKey(11);
        list.displayForward();
        list.insertAfter(22, 77);
        list.insertAfter(33, 88);
        list.displayForward();
        
        Iterator it = list.iterator();
        it.remove();
        while(it.hasNext()){
            ((Link)it.next()).displayLink();
        }
    }
}
