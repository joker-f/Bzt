package com.lanou3g.homework01.login;



import com.lanou3g.homework01.JdbcUtil.lianjie;
import com.lanou3g.homework01.areaCode.queryNum;
import com.lanou3g.homework01.documents.InputElement;
import com.lanou3g.homework01.exception.ExistException;
import com.lanou3g.homework01.exception.passWordElection;
import com.lanou3g.homework01.playgame.playGame;
import com.lanou3g.homework01.weather.getWeather;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
    public static void main(String[] args) throws DocumentException, IOException {
       // login();
    }
    public static void login() throws DocumentException, IOException, SQLException, ExistException {
        lianjie lj = new lianjie();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("��ѡ��1ע�� 2��¼");
            int e = scanner.nextInt();
            scanner.nextLine();
            switch (e) {
                case 1:
                    System.out.println("�����û���");
                    String username = scanner.nextLine();
                    System.out.println("��������");
                    String password = scanner.nextLine();
                    System.out.println("��������");
                    String age = scanner.nextLine();
                    System.out.println("�������Ա�");
                    String gender = scanner.next();
                    boolean email1, tel1, password1;
                    String email = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                    String tel = "[1][38]\\d([-]{0,1})\\d{4}([-]{0,1})\\d{4}";
                    email1 = Pattern.matches(email, username);
                    tel1 = Pattern.matches(tel, username);
                    String inps = scanner.nextLine();
                    String ps = "^[a-zA-Z0-9]{6,14}$";
                    password1 = Pattern.matches(ps, inps);
                    // imp(scanner);
                    //�ж������Ƿ�ȫ
                    ResultSet resultSet = lj.select();
                    while (resultSet.next()) {
                        if (resultSet.getString(3).equals(username)) {
                            throw new ExistException();
                        }
                    }
                    lj.add(username, password, age, gender);
                    System.out.println("ע��ɹ�");
//            }

                    /*ResultSet resultSet=userName.
                    try {
                        passWordElection.detection(passWord);
                        //�ж��˺��Ƿ��Ѿ�ע��
                        if (email1 || tel1) {
                            Person person = new Person(name, userName, passWord);
                            InputElement.Input(person);
                            System.out.println("ע��ɹ�");
                        }
                    } catch (Exception exceptionz) {
                        System.out.println(exceptionz.getMessage());

                    }
                    Person person = new Person(name, userName, passWord);
                    InputElement.Input(person);*/
                    break;
                case 2:
                    //���������û���������ķ���
                    //imp(scanner);
                    System.out.println("�����û���");
                    String userName1 = scanner.nextLine();
                    System.out.println("��������");
                    String passWord1 = scanner.nextLine();
//                    Person ui = null;
                    try {
                            login1(userName1,passWord1);
//                        ui = UserOperate.login(userName1, passWord1);
                        System.out.println("��¼�ɹ�");
                        xunze();
                    } catch (Exception exceptions) {
                        exceptions.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static void login1(String username ,String password) throws DocumentException, SQLException {
        lianjie lj=new lianjie();
        ResultSet select = lj.select();
        while (select.next()){
            if (select.getString(3).equals(username)&&select.getString(4).equals(password)){
                System.out.println("��¼�ɹ�");
//                return select;
            }
        }
//        return null;
    }
    public static void pass(){
        Scanner input=new Scanner(System.in);
        boolean password;
        System.out.println("��������������(������[6-14]λ,���д�Сд��ĸ������)");
        String inps = input.nextLine();
        String ps="^[a-zA-Z0-9]{6,14}$";
        password=Pattern.matches(ps,inps);
        if(password){
            System.out.println("��¼�ɹ�");
        }else{
            System.out.println("�������");
        }
    }

    public static void xunze() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1������ѯ,2��ѯ�ֻ��Ź�����,3������Ϸ");
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
                System.out.println("��������");
                break;
        }
    }
}
