package com.example.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2021/7/29
 */
@Data
public class FixParams {

    private PersonList personList;

    @Data
    public class PersonList{
        private List<PersonEntity> personEntityList;
    }
    public static void main(String[] args) throws UnknownHostException {
        String s1 = "8620012000150012";
        System.out.println(s1.substring(6));
        String s = "5100010001010a0000000a00000000860a08002021082510582803403839383631313138323539303036383630323832864814044242708012000000be83068354dec40402006b80b700d980065226080056";
        System.out.println(s.length());
        //String ip = InetAddress.getLocalHost().getHostAddress();
        String ip = InetAddress.getByName(InetAddress.getLocalHost().getHostName()).getHostAddress();
        System.out.println(ip);

        boolean equals = "aa".equals(null);
        System.out.println(equals);

        HashMap<String, Object> map = new HashMap<>();
        map.put("Name","yb");
        map.put("age",18);
        map.put("TEST","test");
        map.put("SDCUUId","this is SDCUUId");
        String jsonString = JSON.toJSONString(map);
        Person person = JSON.parseObject(jsonString,Person.class);
        System.out.println(person);
    }


}
