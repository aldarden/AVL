// This program creates AVL trees and prints the size of each node
package Q3;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        
        tree.add(11);
        tree.add(1);
        tree.add(14);
        tree.add(2);
        tree.add(7);
        tree.add(15);
        tree.add(5);
        tree.add(8);
        tree.add(4);
        
        tree.printInOrder();
        System.out.println("");
        tree.printFacts();
    }
}


class AVLTree{
    TreeNode root;
    
    public AVLTree(){
        this.root = null;
    }
    
    
    public void add(int value){
        root = add(root, value);
    }
    
    public TreeNode add(TreeNode node, int value){
        if (node == null) {
            node = new TreeNode(value);
        } else if (value < node.item) {
                node.left = add(node.left, value);
                if(balanceFactor(node) == 2){
                    if(node.left.item > value){
                      node = rightRotation(node);
                    }else{
                      node = leftRightRotation(node);
                    }
                }
            } 
        else if (value > node.item) {
                node.right = add(node.right, value);
                if(balanceFactor(node) == -2){
                    if(value > node.right.item){
                      node = leftRotation(node);
                    }else{
                      node = rightLeftRotation(node);
                    }
                }
            } 
        
        else{
                throw new IllegalArgumentException("Duplicate Value");
            }
        return node;
    }
        
    // Rotations
    
    public TreeNode leftRotation(TreeNode node){
        TreeNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }
    
    public TreeNode rightRotation(TreeNode node){
        TreeNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }
    
    public TreeNode rightLeftRotation(TreeNode node){
        node.right = rightRotation(node.right);
        node = leftRotation(node);
        return node;
    }
    
    public TreeNode leftRightRotation(TreeNode node){
        node.left = leftRotation(node.left);
        node = rightRotation(node);
        return node;
    }
    
    // Height, BalanceFactor, Size, and Print methods
    
    public int findHeight(TreeNode node){
        if(node == null){
            return -1;
        }
        
        return 1 + Math.max(findHeight(node.left), findHeight(node.right));
 
    }
    
    public int findSize(TreeNode node){
        if(node == null){
            return 0;
        }
        
        return 1 + findSize(node.left) + findSize(node.right);
    }
    
    public int balanceFactor(TreeNode node){
        int balanceFactor = findHeight(node.left) - findHeight(node.right);
        return balanceFactor;
    }
    
    public void printFacts(){
        System.out.println("Node    Size ");
        printFacts(root);
    }
    
    public void printFacts(TreeNode node){
        if(node != null){
            System.out.println(node.item + "        " + findSize(node));
            printFacts(node.left);
            printFacts(node.right);
        }
    }
    
    public void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.item);
            printInOrder(node.right);
        }
    }
    
}

class TreeNode{
    int item, height, balanceFactor;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(){
        this.item = 0;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
    
    public TreeNode(int item){
        this.item = item;
        this.height = 1;
    }
    
    public TreeNode getLeft(){
        return this.left;
    }
    
    public TreeNode getRight(){
        return this.right;
    }
    
    
    
   
}

