package jinhe.lt.datastructure.list;

/**
 * <p>
 * LinkList.java
 * </p>
 * 
 * @author Jon.King 2006-5-17
 * 
 */
public class LinkList {
	
	static class Link {
	    public int key; // data item (key)

	    public double value; // data item

	    public Link next; // next link in list

	    public Link(int id, double dd) {
	        key = id; // initialize data
	        value = dd; // ('next' is automatically
	    }
	    
	    public void displayLink(){
	        System.out.print("{" + key + ", " + value + "} ");
	    }
	} 
	
    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id, double dd) {
        Link newLink = new Link(id, dd);
        newLink.next = first;
        first = newLink;
    }

    public Link find(int key) {
        Link current = first;
        while (current.key != key) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    public Link delete(int key) {
        Link current = first;
        Link previous = first;
        while (current.key != key) {
            if (current.next == null)
                return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == first)
            first = first.next;
        else
            previous.next = current.next;
        return current;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        LinkList theList = new LinkList(); 
        theList.insertFirst(1, 2.99); 
        theList.insertFirst(2, 4.99);
        theList.insertFirst(3, 6.99);
        theList.insertFirst(4, 8.99);
        theList.displayList(); 
        Link f = theList.find(4); 
        if (f != null)
            System.out.println("Found link with key " + f.key);
        else
            System.out.println("Can't find link");
        Link d = theList.delete(2); 
        if (d != null)
            System.out.println("Deleted link with key " + d.key);
        else
            System.out.println("Can't delete link");
        theList.displayList(); 
    }
}
