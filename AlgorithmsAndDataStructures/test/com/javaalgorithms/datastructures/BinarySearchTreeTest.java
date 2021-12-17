package com.javaalgorithms.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void getNodeValue() {
        final int key = 0;
        final int value = 0;
        BinarySearchTree<Integer, Integer> BST = new BinarySearchTree<>();
        BST.insert(key, value);
        int result = BST.getNodeValue(key);
        assert key == result;

        final String sKey = "0";
        final String sValue = "0";
        BinarySearchTree<String, String> sBST = new BinarySearchTree<>();
        sBST.insert(sKey, sValue);
        String sResult = sBST.getNodeValue(sKey);
        assert sKey.equals(sResult);
    }

    @Test
    void remove() {
        final int key = 0;
        final int value = 0;
        BinarySearchTree<Integer, Integer> BST = new BinarySearchTree<>();
        BST.insert(key, value);
        int result = BST.getNodeValue(key);
        assert key == result;

        BST.remove(key);
        Integer afterRemoved = BST.getNodeValue(key);
        assert afterRemoved == null;
    }

    @Test
    void insertMultipleTreeNodes() {
        final int key = 0;
        final int value = 0;
        BinarySearchTree<Integer, Integer> BST = new BinarySearchTree<>();
        BST.insert(key, value);
        BST.insert(0, 0);
        BST.insert(-1, -1);
        BST.insert(5,5);
        BST.insert(3,3);
        BST.insert(8,8);
        BST.insert(1,1);
        BST.insert(2,2);
        BST.insert(4,4);
        BST.insert(7,7);
        BST.insert(6,6);
        BST.insert(9,9);
        BST.insert(-100, -100);
        BST.insert(100, 100);
        assert key == BST.getNodeValue(key);
        assert -1 == BST.getNodeValue(-1);
        assert 1 == BST.getNodeValue(1);
        assert -100 == BST.getNodeValue(-100);
        assert 100 == BST.getNodeValue(100);

        BST.remove(999);
        BST.remove(0);
        BST.remove(1);
        BST.remove(7);
        BST.remove(-1);
        BST.remove(9);
        BST.remove(4);
    }
}