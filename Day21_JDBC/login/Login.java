package com.lanou3g.homework01.login;



import com.lanou3g.homework01.areaCode.queryNum;
import com.lanou3g.homework01.documents.InputElement;
import com.lanou3g.homework01.exception.passWordElection;
import com.lanou3g.homework01.playgame.playGame;
import com.lanou3g.homework01.weather.getWeather;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
    public static void main(String[] args) throws DocumentException, IOException {
        login();
    }
    public static void login() throws DocumentException, IOException {

        Scanner scanner=new Scanner(System.in);
        while (true) {
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
                    boolean email1, tel1, password;
                    String email = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                    String tel = "[1][38]\\d([-]{0,1})\\d{4}([-]{0,1})\\d{4}";
                    email1 = Pattern.matches(email, userName);
                    tel1 = Pattern.matches(tel, userName);
                    String inps = scanner.nextLine();
                    String ps = "^[a-zA-Z0-9]{6,14}$";
                    password = Pattern.matches(ps, inps);
                    // imp(scanner);
                    //判断密码是否安全

                    ResultSet resultSet=userName.
                   /* try {
                        passWordElection.detection(passWord);
                        //判断账号是否已经注册
                        if (email1 || tel1) {
                            Person person = new Person(name, userName, passWord);
                            InputElement.Input(person);
                            System.out.println("注册成功");
                        }
                    } catch (Exception exceptionz) {
                        System.out.println(exceptionz.getMessage());

                    }*/
                    Person person = new Person(name, userName, passWord);
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
                        ui = UserOperate.login(userName1, passWord1);
                        System.out.println("登录成功");
                        xunze();
                    } catch (Exception exceptions) {
                        exceptions.printStackTrace();
                    }
                    break;
            }
        }
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

    public static void xunze() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1天气查询,2查询手机号归属地,3手速游戏");
        switch (scanner.nextInt()){
            case 1:
                getWeather.query();
                break;
            case 2:
                queryNum.qNum();
                break;
            case 3:
                playGame.playGame();
                break;
            default:
                System.out.println("输入有误");
                break;
        }
    }
}
