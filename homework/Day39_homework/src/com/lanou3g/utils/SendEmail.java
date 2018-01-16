package com.lanou3g.utils;



import com.lanou3g.user.domain.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

public class SendEmail {
    private String sendAddr = "421754005@qq.com";//发送方的邮箱
//    private String receiveAddr = "1300812844@qq.com";//接收方的邮箱
    //这不是密码，是开启QQ邮箱第三方登录时候设置的授权码
    //如果不开启，那么是无法第三方登录QQ邮箱的
    private String pass = "yilgqpbcoyewbhid";
    private String receiveAddr;
    private User user=new User();
//    @Test
    public void sendE(String receiveAddr,String uid) throws MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host","smtp.qq.com");
        prop.setProperty("mail.smtp.auth","true");
        prop.setProperty("mail.smtp.port","587");
        //先构建一个邮件账号，需要用到邮件地址和密码
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendAddr,pass);
            }
        };
        //与服务器连接，这时候就用到了qq.com的服务器
        //实际上就是用Java代码，登陆了自己的QQ邮箱
        Session session = Session.getInstance(prop,auth);

        //创建一个邮件对象
        //这就是登录了邮箱之后，进入编辑邮件的窗口
        MimeMessage msg = new MimeMessage(session);

        //设置发件人
        msg.setFrom(new InternetAddress(sendAddr));

        //设置收件人
        System.out.println("-209--"+receiveAddr);
        msg.setRecipients(Message.RecipientType.TO,receiveAddr);

        String uuid = UUID.randomUUID().toString().replaceAll("-","")+uid;
//        user.setCode(uuid);
        System.out.println("-208-"+uuid);
        msg.setSubject("这是来自joke-f的邮件");
        msg.setContent("这里面有一些interesting的东西<br/><a href=http://localhost:8080/BookShop/jsps/user/msg.jsp"+uuid+">点击！</a>","text/html;charset=UTF-8");
        Transport.send(msg);
    }

}