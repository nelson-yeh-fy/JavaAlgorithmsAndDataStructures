package com.javaalgorithms.datastructures;

/**
 * Generic TreeNode Class.
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class TreeNode <T extends Comparable<T>> {
    T key;
    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    /** Creates a TreeNode with the specified key and value.
     * @param key <T> the type of key in the node.
     * @param val <T> the type of value in the node.
     */
    TreeNode(T key,T val){
        this.key = key;
        this.val = val;
    }

    /** Creates a TreeNode with the specified key, value, left child, and right child.
     * @param key <T> the type of key in the node.
     * @param val <T> the type of value in the node.
     * @param l TreeNode<T> as a left child node.
     * @param r TreeNode<T> as a right child node.
     */
    TreeNode(T key,T val, TreeNode<T> l, TreeNode<T> r){
        this.key = key;
        this.val = val;
        this.left = l;
        this.right = r;
    }

    /** Retrieves key from a TreeNode.
     * @return <T> the value of "key".
     */
    public T getKey() {
        return this.key;
    }

    /** Retrieves value from a TreeNode.
     * @return <T> the value of "val".
     */
    public T getValue() {
        return this.val;
    }
}