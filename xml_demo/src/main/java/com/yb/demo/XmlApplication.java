package com.yb.demo;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yb
 * @description
 * @data 2021/11/25
 */
@Slf4j
public class XmlApplication {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("C:\\Users\\86178\\Desktop\\test_demo\\xml_demo\\src\\main\\resources\\pic_0.xml"));
        Element rootElement = document.getRootElement();
        log.info("rootElement:{}", rootElement);
        Iterator iterator = rootElement.elementIterator("object");
        Element stuChild = null;
        ArrayList<Tag> tags = new ArrayList<>();
        Image image = new Image();
        while (iterator.hasNext()) {
            Tag tag = new Tag();
            stuChild = (Element) iterator.next();
            tag.setName(stuChild.element("name").getStringValue());
            tag.setPose(stuChild.element("pose").getStringValue());
            tag.setTruncated(Integer.valueOf(stuChild.element("truncated").getStringValue()));
            tag.setDifficult(Integer.valueOf(stuChild.element("difficult").getStringValue()));

//            if ("bndbox".equals(stuChild.getName())) {
//                Iterator iterator2 = stuChild.elementIterator();
//                Element stuSon = null;
//                Bndbox bndbox = new Bndbox();
//                while (iterator2.hasNext()) {
//                    stuSon = (Element) iterator2.next();
//                    if ("xmin".equals(stuSon.getName())) {
//                        bndbox.setXmin(Integer.valueOf(stuSon.getStringValue()));
//                    }
//                    if ("ymin".equals(stuSon.getName())) {
//                        bndbox.setYmin(Integer.valueOf(stuSon.getStringValue()));
//                    }
//                    if ("xmax".equals(stuSon.getName())) {
//                        bndbox.setXmax(Integer.valueOf(stuSon.getStringValue()));
//                    }
//                    if ("ymax".equals(stuSon.getName())) {
//                        bndbox.setYmax(Integer.valueOf(stuSon.getStringValue()));
//                    }
//                }
//                System.out.println("bndbox = " + bndbox);
//                tag.setBndbox(bndbox);
//            }
            tags.add(tag);
        }
        image.setObject(tags);

    }


//        while(folder1.hasNext()){
//            folder1.next();
//            System.out.println("111");
//        }
//        String data = (String) folder.getData();
//        log.info("data={}",data);
//        while(iterator.hasNext()){
//            Element element = (Element) iterator.next();
//            String name = element.getName();
//
//            log.info(name);
//        }

}
