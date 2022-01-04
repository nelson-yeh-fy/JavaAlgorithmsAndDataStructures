package com.javaalgorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic HashMap Class by using array of BinarySearchTrees.
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
/*
 * Approach 2: Array of BST trees (array index is the hash code of a key, so the same hash contains a BST)
 * https://leetcode.com/problems/design-hashmap/discuss/227081/Java-Solutions
 */
public class HashMap <T extends Comparable<T>, K extends Comparable<K>>{
    int capacity;
    List<BinarySearchTree<T, K>> trees;
    public HashMap(int capacity) {
        this.capacity = capacity;
        trees = new ArrayList<>(capacity);
        for(int i = 0 ; i < capacity ; i++)
            trees.add(null);
    }

    /**
     * Put a new key, value pair into our HashMap
     * @param key <T> the type of key in the node.
     * @param val <K> the type of value in the node.
     */
    public void put(T key, K val) {
        BinarySearchTree<T, K> tree = findTree(key);
        tree.insert(key, val);
    }

    /**
     * Get the value of the HashMap
     * @param key <T> the type of key in the node.
     * @return null if no such key exists in the HashMap
     */
    public K get(T key) {
        BinarySearchTree<T, K> tree = findTree(key);
        return tree.getNodeValue(key);
    }

    /**
     * Remove a key value pair
     * @param key <T> the type of key in the node.
     */
    public void remove(T key) {
        BinarySearchTree<T, K> tree = findTree(key);
        tree.remove(key);
    }

    /**
     * find a tree in the ArrayList<BinarySearchTree<T,K>>
     * @param key <T> the type of key in the node.
     * @return BinarySearchTree <T, K>
     */
    private BinarySearchTree<T, K> findTree(T key){
        int hash = key.hashCode() % capacity;
        if(trees.get(hash) == null)
            trees.set(hash, new BinarySearchTree<>());
        return trees.get(hash);
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