package com.lanou3g.utils;



import com.lanou3g.user.domain.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

public class SendEmail {
    private String sendAddr = "421754005@qq.com";//���ͷ�������
//    private String receiveAddr = "1300812844@qq.com";//���շ�������
    //�ⲻ�����룬�ǿ���QQ�����������¼ʱ�����õ���Ȩ��
    //�������������ô���޷���������¼QQ�����
    private String pass = "yilgqpbcoyewbhid";
    private String receiveAddr;
    private User user=new User();
//    @Test
    public void sendE(String receiveAddr,String uid) throws MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host","smtp.qq.com");
        prop.setProperty("mail.smtp.auth","true");
        prop.setProperty("mail.smtp.port","587");
        //�ȹ���һ���ʼ��˺ţ���Ҫ�õ��ʼ���ַ������
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendAddr,pass);
            }
        };
        //����������ӣ���ʱ����õ���qq.com�ķ�����
        //ʵ���Ͼ�����Java���룬��½���Լ���QQ����
        Session session = Session.getInstance(prop,auth);

        //����һ���ʼ�����
        //����ǵ�¼������֮�󣬽���༭�ʼ��Ĵ���
        MimeMessage msg = new MimeMessage(session);

        //���÷�����
        msg.setFrom(new InternetAddress(sendAddr));

        //�����ռ���
        System.out.println("-209--"+receiveAddr);
        msg.setRecipients(Message.RecipientType.TO,receiveAddr);

        String uuid = UUID.randomUUID().toString().replaceAll("-","")+uid;
//        user.setCode(uuid);
        System.out.println("-208-"+uuid);
        msg.setSubject("��������joke-f���ʼ�");
        msg.setContent("��������һЩinteresting�Ķ���<br/><a href=http://localhost:8080/BookShop/jsps/user/msg.jsp"+uuid+">�����</a>","text/html;charset=UTF-8");
        Transport.send(msg);
    }

}