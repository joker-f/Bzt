package com.lanou3g.homework.login;

import com.lanou3g.homework.documents.InputElement;
import com.lanou3g.homework.exception.passWordElection;
import com.lanou3g.lianxi.document.Documents;
import com.lanou3g.lianxi.exception.Exceptions;
import com.lanou3g.lianxi.exception.Exceptionz;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
    public static void main(String[] args) throws DocumentException, IOException {
        login();
    }

/*    private static String userName = null;
    private static String passWord = null;*/
    public static void login() throws DocumentException, IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请选择：1注册 2登录");
        int e = scanner.nextInt();
        scanner.nextLine();
        switch (e) {
            case 1:
                System.out.println("输入姓名");
                String name = scanner.nextLine();
                System.out.println("输入用户名");
                String userName = scanner.nextLine();
                System.out.println("输入密码");
                String passWord = scanner.nextLine();
                boolean email1,tel1,password;
                String email= "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                String tel="[1][38]\\d([-]{0,1})\\d{4}([-]{0,1})\\d{4}";
                email1= Pattern.matches(email,userName);
                tel1=Pattern.matches(tel,userName);
                String inps =scanner.nextLine();
                String ps="^[a-zA-Z0-9]{6,14}$";
                password=Pattern.matches(ps,inps);
               // imp(scanner);
                //判断密码是否安全
                try {
                    passWordElection.detection(passWord);
                    //判断账号是否已经注册
                    if(email1||tel1){
                        Person person = new Person(name,userName, passWord);
                        InputElement.Input(person);
                        System.out.println("注册成功");
                    }
                } catch (Exceptionz exceptionz) {
                    System.out.println(exceptionz.getMessage());

                }
                Person person = new Person(name,userName, passWord);
                InputElement.Input(person);
                break;
            case 2:
                //调用输入用户名和密码的方法
                //imp(scanner);
                System.out.println("输入用户名");
                String userName1 = scanner.nextLine();
                System.out.println("输入密码");
                String passWord1 = scanner.nextLine();
                Person ui = null;
                try {
                    ui=UserOperate.login(userName1,passWord1);
                    System.out.println("登录成功");
                } catch (Exceptions exceptions) {
                    exceptions.printStackTrace();
                }
                break;
        }
       /* boolean email1,tel1;
        String email= "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String tel="[1][38]\\d([-]{0,1})\\d{4}([-]{0,1})\\d{4}";
        //String tel="[1][0-9]{0,10}";
        // String tel="^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
        email1= Pattern.matches(email,user);
        tel1=Pattern.matches(tel,user);
       System.out.println(email1);
        //System.out.println(tel1);
        if (email1||tel1){
            //pass();
            boolean password;
            System.out.println("请输入您的密码(密码在[6-14]位,且有大小写字母和数组)");
            String inps = input.nextLine();
            String ps="^[a-zA-Z0-9]{6,14}$";
            password=Pattern.matches(ps,inps);
            if(password){
                System.out.println("登录成功");
            }else{
                System.out.println("密码错误");
            }
            Person person=new Person(user,inps);
            InputElement.Input(person);
        }else {
            System.out.println("你输入的邮箱格式或电话号码格式有误");
        }
        System.out.println("登录");
        System.out.println("请输入账户名");
        //imp(input);
        System.out.println("输入用户名");
        String userName = input.nextLine();
        System.out.println("输入密码");
        String password = input.nextLine();
       // List<Person> personList = InputElement.signIn();*/
    }

    public static void pass(){
        Scanner input=new Scanner(System.in);
        boolean password;
        System.out.println("请输入您的密码(密码在[6-14]位,且有大小写字母和数组)");
        String inps = input.nextLine();
        String ps="^[a-zA-Z0-9]{6,14}$";
        password=Pattern.matches(ps,inps);
        if(password){
            System.out.println("登录成功");
        }else{
            System.out.println("密码错误");
        }
    }
    //封装从控制台输入用户名和密码的方法
 /*   public static void imp(Scanner scanner){
        System.out.println("输入用户名");
        String userName = scanner.nextLine();
        System.out.println("输入密码");
        String passWord = scanner.nextLine();
    }*/
}
