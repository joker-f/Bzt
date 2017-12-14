package com.lanou3g.homework.weather;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class getWeather {
    public static void main(String[] args) throws IOException {
        query();
    }
    //@Test
    public static void query() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你要查询天气的城市");
        String s = "北京";
        URL url=new URL("http://api.k780.com/?app=weather.today&weaid="+s+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json");
       /* //URLConnection coon=url.openConnection();
        InputStream is = url.openStream();

        ByteArrayOutputStream out=new ByteArrayOutputStream();

        byte buf[]=new byte[1024];
        int result = 0;
        while ((result = is.read(buf)) > 0) {
            out.write(buf, 0, result);
        }
        if (is != null) {
            is.close();
        }
        byte b[]=out.toByteArray();
        System.out.println(new String(b,"UTF-8"));
        String str=new String(b,"UTF-8");
        //System.out.println(result);
        *//*JSONObject jsonObject=JSONObject.fromObject(str);
        chaxun chaXun=(chaxun)JSONObject.toBean(jsonObject,chaxun.class);*//*
        JSONObject jsonObject=JSONObject.fromObject(str);
        Object o=JSONObject.toBean(jsonObject,chaxun.class);
        chaxun chaXun=(chaxun)o;*/
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        System.out.println(new String(b,"utf-8"));
        String str = new String(b,"utf-8");
        JSONObject joss = JSONObject.fromObject(str);
        Object o = JSONObject.toBean(joss, chaxun.class);
        chaxun chaXun=(chaxun)o;
        System.out.println(chaXun.getResult()+"城市为"+chaXun.getResult().getCityid()+",天气为"+chaXun.getResult().getWeather());


    }
}
