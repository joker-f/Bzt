package com.lanou3g.homework01.areaCode;

import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class queryNum {
    public static void main(String[] args) throws IOException {
        qNum();
    }
    public static void qNum() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你要查询区号的电话号码");
        String num=scanner.next();
        URL url=new URL("http://api.k780.com/?app=phone.get&phone="+num+""+"&appkey=29836&" +
                "sign=4fe58a60443a900a251f8b05763d4622&format=json ");
        URLConnection coon=url.openConnection();
        InputStream is=coon.getInputStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buf=new byte[1024];
        int result=0;
        while ((result=is.read(buf))>0){
            out.write(buf,0,result);
        }
        if (is!=null){
            is.close();
        }
        byte[] b=out.toByteArray();
        System.out.println(new String(b,"UTF-8"));
        String str=new String(b,"UTF-8");
        JSONObject jsonObject=JSONObject.fromObject(str);
        areaCade cade= (areaCade) JSONObject.toBean(jsonObject,areaCade.class);
        System.out.println("区号为"+cade.getResult().getStyle_citynm());
    }
}
