package com.lanou3g.homework.login;

import com.lanou3g.homework.documents.InputElement;
import com.lanou3g.lianxi.exception.Exceptions;
import com.lanou3g.lianxi.exception.Exceptionz;
import org.dom4j.DocumentException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserOperate {
    public static void registers(Person person) throws Exceptionz, DocumentException {
        List<Person> people = InputElement.listElement();
        for (Person p : people) {
            if (p.getUserName().equals(person.getUserName())){
                System.out.println(p.getUserName());
                System.out.println("’Àªß“—¥Ê‘⁄");
            }
        }
    }
    public static Person login(String userName,String passWord) throws Exceptions, DocumentException {
        List<Person> personList = InputElement.listElement();
        for (Person person : personList) {
            //√‹¬Î≤ª∆•≈‰‘Ú≈◊≥ˆ√‹¬Î¥ÌŒÛ“Ï≥£
            if (person.getUserName().equals(userName) && !person.getPassWord().equals(passWord)) {
                System.out.println("√‹¬Î¥ÌŒÛ");
            }
            if (person.getUserName().equals(userName)&&person.getPassWord().equals(passWord)) {
                time();
            }
        }
        return null;
    }
    public static void time() {
        Date date=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String t = dateFormat.format(date);
        System.out.print(t);
    }
}
