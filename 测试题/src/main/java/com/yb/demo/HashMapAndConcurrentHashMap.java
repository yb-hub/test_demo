package com.yb.demo;

import java.util.HashMap;

/**
 * @author yb
 * @description
 * @data 2021/7/21
 */
public class HashMapAndConcurrentHashMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("k1","v1");
        hashMap.put("k3","v3");
        hashMap.put("k2","v2");
        for (int i = 4; i < 15000; i++) {
            hashMap.put("k"+i,"v"+i);
        }
    }
}
