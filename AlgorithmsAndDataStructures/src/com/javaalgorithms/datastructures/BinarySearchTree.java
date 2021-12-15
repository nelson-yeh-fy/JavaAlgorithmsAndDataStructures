package com.javaalgorithms.datastructures;

import com.javaalgorithms.datastructures.TreeNode;
public class BinarySearchTree <T extends Comparable<T>> {

    TreeNode<T> root;
    public BinarySearchTree() {
        root = null;
    }

    public void insert(T key, T value){
        this.root = insert(this.root, key, value);
    }

    public <T extends Comparable<T>> TreeNode<T> insert(TreeNode<T> ptr, T key, T value){
        if(ptr == null)
            return new TreeNode(key, value);

        if(key.compareTo(ptr.key) < 0) {
            ptr.left = insert(ptr.left, key, value);
        }else if(key.compareTo(ptr.key) > 0) {
            ptr.right = insert(ptr.right, key, value);
        }else {
            ptr.val = value;
        }
        return ptr;
    }

    private <T extends Comparable<T>> TreeNode<T> findNode(TreeNode<T> ptr, T key){
        if(ptr == null)
            return null;

        if(key.compareTo(ptr.key) < 0){
            return findNode(ptr.left, key);
        }else if(key.compareTo(ptr.key) > 0) {
            return findNode(ptr.right, key);
        }else {
            return ptr;
        }
    }

    public T get(T key){
        TreeNode<T> n = findNode(this.root, key);
        if(n == null) {
            return null;
        }
        return n.val;
    }

    private <T extends Comparable<T>> TreeNode<T> findSuccessor(TreeNode<T> ptr){
        if(ptr == null)
            return null;
        TreeNode curr = ptr.right;
        while(curr != null && ptr.left != null)
            curr = curr.left;
        return curr;
    }

    public void remove(T key) {
        this.root = remove(this.root, key);
    }

    public <T extends Comparable<T>> TreeNode<T> remove(TreeNode<T> ptr, T key){
        if(ptr == null)
            return null;

        // 1. Locate the target node
        if(key.compareTo(ptr.key) < 0) {
            ptr.left = remove(ptr.left, key);
        }else if(key.compareTo(ptr.key) > 0) {
            ptr.right = remove(ptr.right, key);
        }

        // 2. Delete the node by checking three situations
        if(key.equals(ptr.key)){
            // 2.1 no child, delete it directly
            // 2.2 only one subtree, replace the ptr with its subtree
            if(ptr.left == null)
                return ptr.right;

            if(ptr.right == null)
                return ptr.left;

            // 2.3 two subtrees, find the inorder successor
            TreeNode<T> inorderSuccessor = findSuccessor(ptr);
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
