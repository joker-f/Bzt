package com.lanou3g.homework.playgame;

import com.lanou3g.homework.login.User;
import com.lanou3g.lianxi.login.Person;
import com.sun.org.apache.bcel.internal.classfile.Constant;

import java.util.*;

public class playGame {
    private static final int DIFFICULT_CODE = 1;
    private static final int MIDDLE_CODE = 2;
    private static final int EASY_CODE = 3;
    private static final int TIMES_DIEEICULT=30;
    private static final int TIMES_MIDDLE=20;
    private static final int TIMES_EASY=10;
    private static List<Character> difficult;
    private static List<Character> middle;
    private static List<Character> easy;
    private static Random random;
    private static Scanner input;
    private static NetTool netTool;
    private static String nickname = "douniwan";

    public static void main(String[] args) {
        playGame();
    }
    public static void playGame(){
        initGame();
        input=new Scanner(System.in);
        random=new Random();
        netTool=new NetTool();
        System.out.println("��ӭ,Ĭ���ǳ�Ϊ:zhangsan");
        System.out.println("1��ʼ��Ϸ,2��ѯǰʮ");
        int choice=input.nextInt();
        switch (choice){
            case 1:
                System.out.println("��ѡ����Ϸ�Ѷ�:1����,2�е�,3��");
                int game=input.nextInt();
                switch (game){
                    case DIFFICULT_CODE:
                    startGame(difficult,TIMES_DIEEICULT);
                        break;
                    case MIDDLE_CODE:
                        startGame(middle,TIMES_MIDDLE);
                        break;
                    case EASY_CODE:
                        startGame(easy,TIMES_EASY);
                        break;
                }
                break;
            case 2:
                //System.out.println("kaishi");
                List<User> users=netTool.pullJsonArray(Constants.URL_TEN,User.class);
                for (int i = 0; i < users.size(); i++) {
                    User user=users.get(i);
                    int index=i+1;
                    System.out.println("��"+index+"��:\t�ǳ�:"+user.getNickname()+"\t�ɼ�:"+user.getScore());
                }
                break;
        }
    }

    public static void initGame(){
        difficult =new ArrayList<>();
        middle=new ArrayList<>();
        easy=new ArrayList<>();
        //32�ǿո�,126��~
        //�����ַ�
        for (char i = 32; i < 126; i++) {
            difficult.add(i);
        }
        //��д��ĸ
        for(char i=65;i<=90;i++){
            easy.add(i);
        }
        //Сд��ĸ
        for (char i=97;i<=122;i++){
            easy.add(i);
        }
        //����
        for(char i=48;i<=57;i++){
            middle.add(i);
        }
        middle.addAll(easy);
    }
    public static void startGame(List<Character> cs,int times){
        StringBuilder game=new StringBuilder();
        for(int i=0;i<times;i++){
            int index=random.nextInt(cs.size()-1);
            game.append(cs.get(index));
        }
        for (int i = 3; i >=0 ; i--) {
            if(i==0){
                System.out.println("��ʼ��Ϸ");
            }else {
                System.out.println("����ʱ"+i+"���ʼ");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //���������ַ���
        System.out.println(game);
        //��ǰ��ʱ��
        long start=System.currentTimeMillis();
        input=new Scanner(System.in);
        String receive=input.nextLine();
        //�����������ַ�������������ɵ�
        if (game.toString().equals(receive)){
            long end=System.currentTimeMillis();
            long score=end-start;
            //?
            Number number=new Long(score);
            int time=number.intValue();

            System.out.println("�ɼ�Ϊ:"+time+"����");
            String result=netTool.pushScore(Constants.URL_FIRST,nickname,time);
            //?
            if(result.equals("SUCCESS")){
                System.out.println("�ɼ��ύ�ɹ�");
            }
            User user=netTool.pullXml(Constants.URL_FIRST,User.class);
            System.out.println("Ŀǰ��һ��Ϊ:"+user.getNickname()+"����Ϊ:"+user.getScore());
        }else{
            System.out.println("��Ϸʧ��");
        }
    }

}
