package com.lanou3g.homework.playgame;

import com.lanou3g.homework.login.User;
import com.lanou3g.lianxi.login.Person;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetTool {
    public String pushScore(String url,String nickname,int score){
        String realUrl=url+"?"+"username="+nickname+"&score="+score;
        String json=getResultString(realUrl);
        return json;
    }

    public <T>java.util.List<T> pullJsonArray(String url,Class<T> clazz){
        String json=getResultString(url);
        JSONArray jsonArray=JSONArray.fromObject(json);
        java.util.List<T> list=null;
        try {
            jsonArray.toList(jsonArray,clazz.newInstance(),new JsonConfig());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

/*    public <T> T pullJsonObject(String url,Class<T> clazz){
        String json=getResultString(url);
        JSONObject jsonObject=JSONObject.fromObject(json);
        T t= (T) JSONObject.toBean(jsonObject);
        return t;
    }*/
    //?
    public <T> T pullXml(String url,Class<T> clazz){
        String xml=getResultString(url);
        XStream xStream=new XStream(new Dom4JDriver());
        xStream.alias("User", User.class);
        T t=null;
        try {
            xStream.fromXML(xml,clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    public String getResultString(String url){
        StringBuilder sb=new StringBuilder();
        try {
            URL u=new URL(url);
            URLConnection conn=u.openConnection();
            InputStream is=conn.getInputStream();
            byte[] bytes=new byte[1024];
            int len=0;
            while ((len=is.read(bytes))!=-1){
                String str=new String(bytes,0,len);
                sb.append(str);
            }



            is.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return  sb.toString();
    }

    //写一个方法读取xml文件里的内容
    @Test
    public void xml() throws IOException {
        URL url=new URL("http://192.168.20.194:8080/day16/first");
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
        Object o = JSONObject.toBean(joss, User.class);
        User chaXun=(User)o;
        System.out.println("123");
        System.out.println(chaXun.getNickname()+chaXun.getScore()+"---1234");
    }

}
