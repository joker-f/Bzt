<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <!--<meta charset="UTF-8">-->
    <title>登录</title>
    <style type="text/css">
        .head{
            margin: 10px 0px 0px 0px;
            width: 100%;
            height: 70px;
            background-color: ghostwhite;
        }
        .head-lcon-center{
            width: 1200px;
            margin: auto;
        }
        .head-lcon-left{
            float: left;
        }
        .head-lcon-left-img{
            width: 120px;
            height: 30px;
        }
        .head-lcon-left-p{
            margin: 10px 0px 0px 10px;
            float: right;
        }
        .head-lcon-right{
            margin: 10px 5px 0px 0px;
            float: right;
        }
        .body{
            width: 100%;
            height: 100%;
            background-color: gainsboro;

        }
        .body-center{
            width: 915px;
            height: 590px;
            margin: auto;
        }
        .body-center-left{
            float: left;
        }
        .body-center-left-img{
            width: 440px;
            height: 300px;
            float: left;
            margin: 160px 10px 0px 0px;
        }
        .body-center-right{
            width: 410px;
            height: 480px;
            float: right;
            margin: 100px 0px 0px 10px;
            background-color: aliceblue;
        }
        .denglu{
            width: 410px;
            height: 90px;
            padding: 25px 0px 0px 100px;
        }
        .denglu-h3{
            font-size: 30px;
        }
        .input{

        }
        .username{
            width: 320px;
            height: 50px;
            margin: 10px 0px 20px 0px;
            margin: 20px 40px 20px 40px;
            /*border: 1px solid red;*/
        }
        .password{
            width: 320px;
            height: 50px;
            margin: 10px 0px 20px 0px;
            margin: 20px 40px 20px 40px;
        }
        .denglubutton{
            width: 330px;
            height: 55px;
            margin: 20px 40px 20px 40px;
            font-size: 20px;
        }
        .body-center-right-foot{
            width: 325px;
            height: auto;
            margin: 20px 40px 20px 40px;
        }
        .register-p-lfet{
            font-size: 13px;
            float: left;
        }
        .register-p-right{
            font-size: 13px;
            float: right;
        }
        .time{
            float: right;
            margin: 100px 0px 0px 0px;
        }
    </style>
    <script type="text/javascript">
        var time = (new Date()).getTime();
        function showLeftTime() {
            var now=new Date();
            var year=now.getFullYear();
            var month=now.getMonth();
            var day=now.getDate();
            var hours=now.getHours();
            var minutes=now.getMinutes();
            var seconds=now.getSeconds();
            document.all.show.innerHTML=""+year+"年"+month+"月"+day+"日"+hours+
                "小时"+hours+":"+minutes+":"+seconds+"";
            setTimeout(showLeftTime,1000);
        }
        function innputFocus(ele) {
            ele.style.fontSize="30px";
            ele.style.border="solid red";
        }
        function inputBlur(ele) {
            ele.style.border="solid white";
            ele.style.fontSize="15px";
        }
    </script>
</head>
<body onload="showLeftTime()">
<div class="big">
    <div class="head">
        <div class="head-lcon-center">
            <div class="head-lcon-left">
                <img src="../img/logo.png" class="head-lcon-left-img">
                <div class="head-lcon-left-p">登录</div>
            </div>
            <div class="head-lcon-right">
                <a href="#">网易云登录</a>
                <a href="#">帮助与文档</a>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="body-center">
            <div class="img-center-left">
                <img src="../img/小广告.png" class="body-center-left-img">
            </div>
            <form action="login" method="post">
                <div class="body-center-right">
                    <div class="denglu"><h3 class="denglu-h3">网易云登录</h3></div>
                    <div class="input">
                        <%
                            String name="";
                            Cookie[] cookies=request.getCookies();
                            for (Cookie cookie : cookies) {
                                if("users".equals(cookie.getName())){
                                    name= URLDecoder.decode(cookie.getValue(),"utf-8");
                                    System.out.println(name);
                                }
                            }
                        %>
                        <input type="username" name="username" id="username" placeholder="请输入用户名" value="<%= name %>"
                               class="username" onfocus="innputFocus(this)" onblur="inputBlur(this)">
                        <input type="password" name="password" id="password" placeholder="请输入用户密码"
                               class="password" onfocus="innputFocus(this)" onblur="inputBlur(this)">
                        <input type="submit" name="denglu" id="denglu"
                               value="登录" class="denglubutton">
                    </div>
                    <div class="body-center-right-foot">
                        <p class="register-p-lfet" >还没账号？<a href="register.html">赶紧注册把</a></p>
                        <p class="register-p-right"><a href="#">忘记密码</a>嘛。亲</p>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="time">
        <label id="show">当前的时间为</label>
    </div>
    <!--<div class="body-center-text-foot">
        <p>版权为<strong>fw</strong>，仅供粘贴复制剪切抄袭方便使用</p>
    </div>-->
</div>
</body>
</html>