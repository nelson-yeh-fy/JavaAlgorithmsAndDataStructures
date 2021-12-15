package com.javaalgorithms.datastructures;

public class HashMap {
// Approach 2: Array of BST trees (array index is the hash code of a key, so the same hash contains a BST)
// https://leetcode.com/problems/design-hashmap/discuss/227081/Java-Solutions
    BST[] trees = new BST[10000];
    private BST findTree(int key){
        int idx = Integer.hashCode(key) % 3;
        if(trees[idx] == null)
            trees[idx] = new BST();
        return trees[idx];
    }
    // value will always be non-negative.
    public void put(int key, int value) {
        (findTree(key)).insert(key, value);
    }
    // Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
    public int get(int key) {
        return (findTree(key)).get(key);
    }
    // Removes the mapping of the specified value key if this map contains a mapping for the key
    public void remove(int key) {
        (findTree(key)).remove(key);
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        // write your code here
        HashMap obj = new HashMap();
        int key = 1, val = 2;
        obj.put(key,val);
        obj.put(4,4);
        obj.put(7,7);
        int param_2 = obj.get(key);
        obj.remove(key);
    }
}

class Node {
    int key, val;
    Node left;
    Node right;
    Node(int k, int v){
        this.key = k; this.val = v;
    }
}

class BST {
    Node root = null;
    public void insert(int key, int value){
        this.root = insert(this.root, key, value);
    }
    public Node insert(Node ptr, int key, int value){
        if(ptr == null)
            return new Node(key, value);

        if(key < ptr.key)
            ptr.left = insert(ptr.left, key, value);
        else if(key > ptr.key)
            ptr.right = insert(ptr.right, key, value);
        else
            ptr.val = value;
        return ptr;
    }
    private Node findNode(Node ptr, int key){
        if(ptr == null)
            return null;

        if(key < ptr.key)
            return findNode(ptr.left, key);
        else if(key > ptr.key)
            return findNode(ptr.right, key);
        else
            return ptr;
    }
    public int get(int key){
        Node n = findNode(this.root, key);
        if(n == null)
            return -1;
        return n.val;
    }
    private Node findSuccessor(Node ptr){
        if(ptr == null)
            return null;
        Node curr = ptr.right;
        while(curr != null && ptr.left != null)
            curr = curr.left;
        return curr;
    }
    public void remove(int key) {
        this.root = remove(this.root, key);
    }
    public Node remove(Node ptr, int key){
        if(ptr == null)
            return null;

        // 1. Locate the target node
        if(key < ptr.key)
            ptr.left = remove(ptr.left, key);
        if(key > ptr.key)
            ptr.right = remove(ptr.right, key);
        // 2. Delete the node by checking three situations
        if(key == ptr.key){
            // 2.1 no child, delete it directly
            // 2.2 only one subtree, replace the ptr with its subtree
            if(ptr.left == null)
                return ptr.right;
            if(ptr.right == null)
                return ptr.left;
            // 2.3 two subtrees, find the inorder successor
            Node inorderSuccessor = findSuccessor(ptr);
            if(inorderSuccessor != null){
                ptr.key = inorderSuccessor.key;
                ptr.val = inorderSuccessor.val;
                ptr.right = remove(ptr.right, inorderSuccessor.key);
            }
            return ptr;
        }
        return ptr;
    }
}


/*
 * Approach 1: use a big array with two pre-requisites: fixed size, non-negative key.
 **/
/*class HashMap {
    // Initialize your data structure here.
    int[] map;
    public HashMap() {
        map = new int[1000001];
    }

    // value will always be non-negative.
    public void put(int key, int value) {
        // make it value+1, this way distinguish from default value (0)
        map[key] = value + 1;
    }

    // Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
    public int get(int key) {
        return map[key] - 1;
    }

    // Removes the mapping of the specified value key if this map contains a mapping for the key
    public void remove(int key) {
        map[key] = 0;
    }
}*/