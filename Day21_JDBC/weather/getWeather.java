package com.lanou3g.homework01.weather;
import com.lanou3g.homework.exception.weatherNullException;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.Scanner;

public class getWeather {
/*    public static void main(String[] args) throws IOException {
       query();
    }*/
    //@Test
    public static void query() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你要查询天气的城市的拼音");
        String s =scanner.nextLine();
        URL url=new URL("http://api.k780.com/?app=weather.today&weaid="+s+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json");
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
        System.out.println("城市为"+chaXun.getResult().getCitynm()+",天气为"+chaXun.getResult().getWeather()+chaXun.getResult().getCityno());


    }
}
