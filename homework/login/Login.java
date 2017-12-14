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
        System.out.println("��ѡ��1ע�� 2��¼");
        int e = scanner.nextInt();
        scanner.nextLine();
        switch (e) {
            case 1:
                System.out.println("��������");
                String name = scanner.nextLine();
                System.out.println("�����û���");
                String userName = scanner.nextLine();
                System.out.println("��������");
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
                //�ж������Ƿ�ȫ
                try {
                    passWordElection.detection(passWord);
                    //�ж��˺��Ƿ��Ѿ�ע��
                    if(email1||tel1){
                        Person person = new Person(name,userName, passWord);
                        InputElement.Input(person);
                        System.out.println("ע��ɹ�");
                    }
                } catch (Exceptionz exceptionz) {
                    System.out.println(exceptionz.getMessage());

                }
                Person person = new Person(name,userName, passWord);
                InputElement.Input(person);
                break;
            case 2:
                //���������û���������ķ���
                //imp(scanner);
                System.out.println("�����û���");
                String userName1 = scanner.nextLine();
                System.out.println("��������");
                String passWord1 = scanner.nextLine();
                Person ui = null;
                try {
                    ui=UserOperate.login(userName1,passWord1);
                    System.out.println("��¼�ɹ�");
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
            System.out.println("��������������(������[6-14]λ,���д�Сд��ĸ������)");
            String inps = input.nextLine();
            String ps="^[a-zA-Z0-9]{6,14}$";
            password=Pattern.matches(ps,inps);
            if(password){
                System.out.println("��¼�ɹ�");
            }else{
                System.out.println("�������");
            }
            Person person=new Person(user,inps);
            InputElement.Input(person);
        }else {
            System.out.println("������������ʽ��绰�����ʽ����");
        }
        System.out.println("��¼");
        System.out.println("�������˻���");
        //imp(input);
        System.out.println("�����û���");
        String userName = input.nextLine();
        System.out.println("��������");
        String password = input.nextLine();
       // List<Person> personList = InputElement.signIn();*/
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
    //��װ�ӿ���̨�����û���������ķ���
 /*   public static void imp(Scanner scanner){
        System.out.println("�����û���");
        String userName = scanner.nextLine();
        System.out.println("��������");
        String passWord = scanner.nextLine();
    }*/
}
