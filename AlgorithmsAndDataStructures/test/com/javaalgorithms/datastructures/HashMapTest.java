package com.javaalgorithms.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    @Test
    public void driveHashMap(){
        HashMap<Integer, Integer> obj = new HashMap<>(100);
        int key = 1, val = 2;
        obj.put(key,val);
        obj.put(4,4);
        obj.put(7,7);
        assert obj.get(key) == 2;
        assert obj.get(4) == 4;
        assert obj.get(10) == null;
        obj.remove(key);
        assert obj.get(key) == null;

        HashMap<String, String> obj2 = new HashMap<>(100);
        obj2.put("k1", "v1");
        obj2.put("k2", "v2");
        obj2.put("k3", "v3");
        assert obj2.get("k1").equals("v1");
        assert obj2.get("k2").equals("v2");
        assert obj2.get("k3").equals("v3");
        assert obj2.get("k0") == null;
        obj2.remove("k1");
        assert obj2.get("k1") == null;
    }
}