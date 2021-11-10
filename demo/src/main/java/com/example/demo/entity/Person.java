package com.example.demo.entity;

/**
 * @author yb
 * @description
 * @data 2021/9/1
 */
public class Person {
        public String Name;
        public int age;
        public String TEST;
        private String SDCUUId;


    public String getSDCUUId() {
        return SDCUUId;
    }

    public void setSDCUUId(String SDCUUId) {
        this.SDCUUId = SDCUUId;
    }

    public String getTEST() {
        return TEST;
    }

    public void setTEST(String TEST) {
        this.TEST = TEST;
    }

    public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", TEST='" + TEST + '\'' +
                ", SDCUUId='" + SDCUUId + '\'' +
                '}';
    }
}
