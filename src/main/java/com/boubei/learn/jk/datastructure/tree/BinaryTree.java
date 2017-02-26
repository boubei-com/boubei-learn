package com.boubei.learn.jk.datastructure.tree;
 
/**
 * <p>
 * BinaryTree.java
 * </p>
 * 
 * @author Jon.King 2006-5-17
 * 
 * 排序二叉树在增，删，查找方面的性能都很高。（前提：随机产生的数列）
 * 
 * 但是如果一组已经排好序的数列such as（3，5，7，8 ...） or （33，32，27，25 ...）插入二叉树时，
 * 会很慢，此时树会失去平衡，变成一维线性的结构，因为它只会一个劲往右节点（左节点）插入数据。
 * 红黑二叉树可以弥补这一缺点
 * 
 * 除了二叉查找树带有的一般要求，我们对任何有效的红黑树加以如下增补要求:(可参考java.util.TreeSet,TreeMap)
 *
 *   1.节点是红色或黑色。 
 *
 *   2.根是黑色。 
 *
 *   3.所有叶子（外部节点）都是黑色。 
 *
 *   4.每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点) 
 *
 *   5.从每个叶子到根的所有路径都包含相同数目的黑色节点。 
 * 
 */

public class BinaryTree {
	
	static class Node {

	    double value;

	    Node leftChild;

	    Node rightChild;
	    
	    public Node(double value){
	        this.value = value;
	    }

	    public void displayNode() {
	        System.out.println(this);
	    }

	    public String toString(){    
	        return new Double(value).toString() + "\n(" + leftChild + ", " + rightChild + ")";
	    }
	}
	
    private Node root;

    public Node find(double key) {
        Node current = root;
        while (current.value != key) {
            if (key < current.value)
                current = current.leftChild;
            else
                current = current.rightChild;

            if (current == null)
                return null;
        }
        return current;
    }

    public void insert(double value) {
        Node newNode = new Node(value);

        if (root == null)
            root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value < current.value) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(double key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.value != key) {
            parent = current;
            if (key < current.value) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }
        
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }
        // 如果被删除的节点current有一个子节点
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        //  如果被删除的节点有current两个子节点
        else{
            Node successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
         
            successor.leftChild = current.leftChild;
        } 
        return true;

    }

    /**
     * returns node with next-highest value after delNode goes to right child,
     * then right child's left descendants
     * 
     * @param delNode
     * @return
     */
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }

    public Node minimum() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }
    
    public static void main(String[] args){
        double[] array = {12,3,6,2,25,36,23,16,8,9,7};
        BinaryTree tree = new BinaryTree();
        for(int i = 0; i < array.length; i++){
            tree.insert(array[i]);
        }
        tree.root.displayNode();
    }
}

