package com.yb.demo;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
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
        log.info("rootElement:{}",rootElement);
        Iterator iterator = rootElement.elementIterator();
        Element folder = rootElement.element("folder");
        String data = (String) folder.getData();
        log.info("data={}",data);
        while(iterator.hasNext()){
            Element element = (Element) iterator.next();
            String name = element.getName();

            log.info(name);
        }

    }
}
