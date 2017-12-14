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
        System.out.println("欢迎,默认昵称为:zhangsan");
        System.out.println("1开始游戏,2查询前十");
        int choice=input.nextInt();
        switch (choice){
            case 1:
                System.out.println("请选择游戏难度:1困难,2中等,3简单");
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
                    System.out.println("第"+index+"名:\t昵称:"+user.getNickname()+"\t成绩:"+user.getScore());
                }
                break;
        }
    }

    public static void initGame(){
        difficult =new ArrayList<>();
        middle=new ArrayList<>();
        easy=new ArrayList<>();
        //32是空格,126是~
        //所有字符
        for (char i = 32; i < 126; i++) {
            difficult.add(i);
        }
        //大写字母
        for(char i=65;i<=90;i++){
            easy.add(i);
        }
        //小写字母
        for (char i=97;i<=122;i++){
            easy.add(i);
        }
        //数字
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
                System.out.println("开始游戏");
            }else {
                System.out.println("倒计时"+i+"秒后开始");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //输出随机的字符串
        System.out.println(game);
        //当前的时间
        long start=System.currentTimeMillis();
        input=new Scanner(System.in);
        String receive=input.nextLine();
        //如果你输入的字符串等于随机生成的
        if (game.toString().equals(receive)){
            long end=System.currentTimeMillis();
            long score=end-start;
            //?
            Number number=new Long(score);
            int time=number.intValue();

            System.out.println("成绩为:"+time+"毫秒");
            String result=netTool.pushScore(Constants.URL_FIRST,nickname,time);
            //?
            if(result.equals("SUCCESS")){
                System.out.println("成绩提交成功");
            }
            User user=netTool.pullXml(Constants.URL_FIRST,User.class);
            System.out.println("目前第一名为:"+user.getNickname()+"分数为:"+user.getScore());
        }else{
            System.out.println("游戏失败");
        }
    }

}
