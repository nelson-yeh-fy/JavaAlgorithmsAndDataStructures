package com.javaalgorithms.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void getKey() {
        final int key = 0;
        final int value = 0;
        TreeNode<Integer, Integer> node = new TreeNode<>(key, value);
        assert key == node.getKey();

        final String sKey = "key";
        final String sValue = "value";
        TreeNode<String, String> sNode = new TreeNode<>(sKey, sValue);
        assert sKey.equals(sNode.getKey());
    }

    @Test
    void getValue() {
        final int key = 0;
        final int value = 0;
        TreeNode<Integer, Integer> node = new TreeNode<>(key, value, null, null);
        assert value == node.getValue();

        final String sKey = "key";
        final String sValue = "value";
        TreeNode<String, String> sNode = new TreeNode<>(sKey, sValue, null, null);
        assert sValue.equals(sNode.getValue());
    }
}