package com.lanou3g.homework01.documents;


import com.lanou3g.homework01.login.Person;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputElement {
    public static Document document = null;
    //注册
    public static void Input(Person person) throws IOException, DocumentException {
        File file=new File("src/out.xml");
        if (file.exists()){
//            System.out.println("1");
            SAXReader reader = new SAXReader();
            document = reader.read(new File("src/out.xml"));
        }
        if (!file.exists()) {
//            System.out.println("2");
            document = DocumentHelper.createDocument();
            Element element1 = document.addElement("occupation");
        }
     /*   if (file.exists()){
            System.out.println("3");
            Element a = document.addElement("person");
        }*/
//        System.out.println("4");
        //获取根标签
        Element element = document.getRootElement();
        Element element1 = element.addElement("occupation");
        // 给属性和值
        element1.addAttribute("name", person.getName());
        Element element4 = element1.addElement("name");
        Element element5 = element4.addText(person.getName());
//        System.out.println(element5+"000");
        Element element2 = element1.addElement("userName");
        element2.addText(person.getUserName());
//        System.out.println("6");
        Element element3 = element1.addElement("password");
        element3.addText(person.getPassWord());
        OutputFormat outputFormats = OutputFormat.createPrettyPrint();
        outputFormats.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new FileWriter("src/out.xml"), outputFormats);
        xmlWriter.write(document);
//        System.out.println("5");
        //将这个工具关闭 并且把工具中的内容  写进目标文件中
        xmlWriter.close();
    }

    //登录
    public static List<Person> listElement() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        document = saxReader.read(new File("src/out.xml"));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            System.out.println(i);
            Element element1 = element.element("occupation");
            Attribute name = element.attribute("name");
            Element userName = element.element("userName");
            Element password = element.element("password");
            Person p = new Person(name.getValue(),userName.getText(),password.getText());
            personList.add(p);
            System.out.println(p);
        }
        return personList;
    }


}
