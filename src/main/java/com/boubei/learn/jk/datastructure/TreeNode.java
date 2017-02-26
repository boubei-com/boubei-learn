package com.boubei.learn.jk.datastructure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private String name;

    public TreeNode(String name) {
        this.name = name;
    }

    private List<TreeNode> children = new ArrayList<TreeNode>();

    public void print() {
        System.out.print(name + " ");
    }

    public void addChild(TreeNode node) {
        if (node == null)
            return;
        children.add(node);
    }

    /**
     * 递归打印树. 优点：代码更简洁清晰，可读性更好；
     * 缺点：由于递归需要系统堆栈，所以空间消耗要比非递归代码要大很多。而且，如果递归深度太大，可能系统撑不住。
     */
    public void printTreeRecursive() {
        this.print();
        for (TreeNode node : children) {
            node.printTreeRecursive();
        }
    }

    /**
     * 利用堆栈打印树。 后进先出，右侧深度优先遍历（右侧的总是先进去）。
     */
    public void printTreeByStack() {
        Stack stack = new Stack();
        stack.push(this);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            node.print();
            for (TreeNode child : node.children) {
                stack.push(child);
            }
        }
    }
    
    static class Stack {
        private List<TreeNode> list = new ArrayList<TreeNode>();
        
        private int index = -1;
        
        public void push(TreeNode node) {
            list.add(node);
            index ++;
        }
        
        public TreeNode pop() {
            if(empty()) return null;
            
            return list.remove(index--);
        }
        
        public boolean empty() {
            return list.isEmpty();
        }
    }
    
    /**
     * 利用队列打印树。 先进先出， 广度优先遍历（高层的节点总是比低层的先入队）。
     */
    public void printTreeByQueue() {
        Queue queue = new Queue();
        queue.push(this);
        while (!queue.empty()) {
            TreeNode node = queue.pop();
            node.print();
            for (TreeNode child : node.children) {
                queue.push(child);
            }
        }
    }
    
    static class Queue {
        private List<TreeNode> list = new ArrayList<TreeNode>();
        
        public void push(TreeNode node) {
            list.add(node);
        }
        
        public TreeNode pop() {
            if(empty()) return null;
            
            return list.remove(0);
        }
        
        public boolean empty() {
            return list.isEmpty();
        }
    }

    public static void main(String args[]) {
        TreeNode A = new TreeNode("a");
        TreeNode B = new TreeNode("b");
        TreeNode C = new TreeNode("c");
        TreeNode D = new TreeNode("d");
        TreeNode E = new TreeNode("e");
        TreeNode F = new TreeNode("f");
        TreeNode G = new TreeNode("g");
        TreeNode H = new TreeNode("h");
        TreeNode I = new TreeNode("i");
        A.addChild(B);
        A.addChild(H);
        B.addChild(C);
        B.addChild(D);
        B.addChild(F);
        B.addChild(G);
        D.addChild(E);
        H.addChild(I);

        System.out.println("递归：");
        A.printTreeRecursive();
        System.out.println("\n队列：");
        A.printTreeByQueue();
        System.out.println("\n堆栈：");
        A.printTreeByStack();
    }

}
