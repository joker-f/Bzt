package com.lanou3g.homework.login;

import java.util.Scanner;

/**
 * ����ѡ��
 */
public class Function {

    public static void choice(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("���ֹ���:1��ѯ����,2��ѯ�ֻ��Ź�����,3��ѯ������Ϸǰʮ�û�");
        switch (scanner.nextInt()){
            case 1:
                //������ѯ

                break;
            case  2:
                //�ֻ��Ź�����
                break;
            case 3:
                //��Ϸ���ٲ�ѯ
                break;
        }
    }
}
