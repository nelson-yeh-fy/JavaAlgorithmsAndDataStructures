package com.javaalgorithms.datastructures;

/**
 * Generic TreeNode Class.
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.1
 * @since 1.0
 */
public class TreeNode <T extends Comparable<T>, K extends Comparable<K>> {
    T key;
    K val;
    TreeNode<T, K> left;
    TreeNode<T, K> right;

    /** Creates a TreeNode with the specified key and value.
     * @param key <T> the type of key in the node.
     * @param val <K> the type of value in the node.
     */
    TreeNode(T key,K val){
        this.key = key;
        this.val = val;
    }

    /** Creates a TreeNode with the specified key, value, left child, and right child.
     * @param key <T> the type of key in the node.
     * @param val <K> the type of value in the node.
     * @param l TreeNode<T, K> as a left child node.
     * @param r TreeNode<T, K> as a right child node.
     */
    TreeNode(T key, K val, TreeNode<T, K> l, TreeNode<T, K> r){
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
     * @return <K> the value of "val".
     */
    public K getValue() {
        return this.val;
    }
}