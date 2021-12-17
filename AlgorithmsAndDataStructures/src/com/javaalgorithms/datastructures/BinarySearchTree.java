package com.javaalgorithms.datastructures;

import com.javaalgorithms.datastructures.TreeNode;

/**
 * Generic BinarySearchTree Class.
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.1
 * @since 1.0
 */
public class BinarySearchTree <T extends Comparable<T>, K extends Comparable<K>> {

    TreeNode<T, K> root;
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert a TreeNode to the BinarySearchTree with the specified key and value.
     * @param key key of the new node.
     * @param value value of the new node.
     */
    public void insert(T key, K value){
        // Assign the root node for this BST after executing the insertion.
        this.root = insert(this.root, key, value);
    }

    /**
     * Insert a TreeNode to the BinarySearchTree with the specified key and value.
     * This is a private function, invoked by the insert(T key, K value).
     * @param ptr given a root ptr in the BST,
     *            this is where the insertion will start to find an appropriate place to insert a new node;
     *            if this ptr is given as null, this function will create a new root node.
     * @param key key of the new node.
     * @param value value of the new node.
     * @return the root of this BST.
     */
    private TreeNode<T, K> insert(TreeNode<T, K> ptr, T key, K value){
        if(ptr == null) {
            return new TreeNode<>(key, value);
        }

        if(key.compareTo(ptr.key) < 0) {
            ptr.left = insert(ptr.left, key, value);
        }else if(key.compareTo(ptr.key) > 0) {
            ptr.right = insert(ptr.right, key, value);
        }else {
            ptr.val = value;
        }
        return ptr;
    }

    /**
     * Find a node which has the same key we're looking for.
     * @param ptr given a root ptr in the BST,
     *            this is where the insertion will start to find an appropriate place to insert a new node;
     * @param key key of the new node.
     * @return the node object with the same key; return null if we can't find it.
     */
    private TreeNode<T, K> findNode(TreeNode<T, K> ptr, T key){
        if(ptr == null) {
            return null;
        }

        if(key.compareTo(ptr.key) < 0){
            return findNode(ptr.left, key);
        }else if(key.compareTo(ptr.key) > 0) {
            return findNode(ptr.right, key);
        }else {
            return ptr;
        }
    }

    /**
     * Get the node's value if we can find it in the BST.
     * @param key is what we want to search in the BST.
     * @return value of the TreeNode.
     */
    public K getNodeValue(T key){
        TreeNode<T, K> n = findNode(this.root, key);
        if(n == null) {
            return null;
        }
        return n.val;
    }

    /**
     * Given a TreeNode in the BST, find its successor.
     * @param ptr given a TreeNode ptr in the BST.
     * @return the successor of the given TreeNode.
     */
    private TreeNode<T, K> findSuccessor(TreeNode<T, K> ptr){
        TreeNode<T, K> curr = ptr.right;
        while(curr != null && curr.left != null)
            curr = curr.left;
        return curr;
    }

    /**
     * Remove a TreeNode from the BinarySearchTree with the specified key.
     * @param key key of the new node.
     */
    public void remove(T key) {
        this.root = remove(this.root, key);
    }

    /**
     * Remove a TreeNode from the BinarySearchTree with the specified key.
     * This is a private function, invoked by the remove(T key).
     * @param ptr given a root ptr in the BST,
     *            this is where the insertion will start to find an appropriate place to insert a new node;
     * @param key key of the new node.
     * @return the root of this BST
     */
    private TreeNode<T, K> remove(TreeNode<T, K> ptr, T key){
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
            TreeNode<T, K> inorderSuccessor = findSuccessor(ptr);
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
