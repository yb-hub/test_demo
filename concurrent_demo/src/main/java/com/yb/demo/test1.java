package com.yb.demo;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yb
 * @description
 * @data 2020/7/2
 */
public class test1 {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        int i = atomicInteger.addAndGet(1);
        System.out.println(i);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);

        list.sort(Comparator.comparing(number -> number));

        for (Integer integer : list) {
            System.out.println(integer);
        }


        String str = "200628192000";
        DateTimeFormatter yyMMddHHmmSS = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime parse = LocalDateTime.parse(str, yyMMddHHmmSS);
        long timestamp = parse.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String s = String.valueOf(timestamp);
        String substring = s.substring(0, s.length() - 3);
        System.out.println(substring);
    }
}
