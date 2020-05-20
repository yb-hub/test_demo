package com.yb.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2020/5/19
 */
public class FastjsonMain {
    public static void main(String[] args) {

        User user = new User("yang", 18);
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);

        //使用fastjson进行json序列化
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        String jsonList = JSON.toJSONString(userList);
        System.out.println(jsonList);

        //使用fastjson进行反序列化
        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println(jsonObject);

        List<User> users = JSON.parseArray(jsonList, User.class);
        System.out.println(users);

        User user1 = JSON.parseObject(jsonString, User.class);
        System.out.println(user1);

        //使用fastjson创建json对象
        JSONObject jsonObject1 = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject1);
    }
}
