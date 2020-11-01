package com.yb.demo.serialized;

import java.io.*;

/**
 * @author yb
 * @description
 * @data 2020/8/13
 */
public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        write5();
        read5();
    }

    /**
     * 无序列化接口 会有.NotSerializableException异常
     * @throws IOException
     */
    public static void write() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
        Person person = new Person();
        person.setId(1);
        outputStream.writeObject(person);
    }
    public static void read() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        Person person1 = (Person) objectInputStream.readObject();
        System.out.println(person1.toString());
    }

    /**
     * 序列化接口
     * @throws IOException
     */
    public static void write2() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
        PersonSerialized personSerialized = new PersonSerialized();
        personSerialized.setId(1);
        outputStream.writeObject(personSerialized);
    }
    public static void read2() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        PersonSerialized person1 = (PersonSerialized) objectInputStream.readObject();
        System.out.println(person1.toString());
    }


    /**
     * 序列化接口 无 无参构造函数
     * @throws IOException
     */
    public static void write3() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
        PersonNoConstructor personSerialized = new PersonNoConstructor(1);
        outputStream.writeObject(personSerialized);
    }
    public static void read3() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        PersonNoConstructor person1 = (PersonNoConstructor) objectInputStream.readObject();
        System.out.println(person1.toString());
    }

//===================================================== 在简单实例（无继承情况下），不会调用无参构造器

    /**
     * 子类有序列化接口 父类无
     * 在序列化时，不会序列化父类，反序列化时，会调用父类无参构造方法
     * @throws IOException
     */
    public static void write4() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
        Teacher teacher = new Teacher("yang");
        teacher.setId(1111);
        outputStream.writeObject(teacher);
    }
    public static void read4() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        //Teacher teacher = (Teacher) objectInputStream.readObject();
        Person teacher = (Person) objectInputStream.readObject();
        System.out.println(teacher.toString());
    }

    public static void write5() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
        Person teacher = new Person(11);
        //teacher.setId(1111);
        outputStream.writeObject(teacher);
    }
    public static void read5() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        //Teacher teacher = (Teacher) objectInputStream.readObject();
        Teacher teacher = (Teacher) objectInputStream.readObject();
        System.out.println(teacher.toString());
    }

}
