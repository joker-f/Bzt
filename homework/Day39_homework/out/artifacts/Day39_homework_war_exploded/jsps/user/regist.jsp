<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		  }
		  .kong{
			  width: auto;
			  height: 100px;
		  }
		  .body-center{
			  width:1200px;
			  height: 560px;
			  margin: auto;
			  background-color: whitesmoke;
		  }
		  .body-center-text{
			  width: 540px;
			  height: 480px;
			  margin: auto;
			  padding: 30px 30px;
		  }
		  .body-center-text-head{
			  width: 130px;
			  /*height: 50px;*/
			  margin: auto;
		  }
		  .body-center-text-head-p{
			  font-size: 30px;
		  }
		  .body-center-text-body{
			  /*width: 620px;*/
		  }

		  .email-text{
			  margin: 20px 0px 30px 0px;
		  }
		  .email-dizhi{
			  font-size: 20px;
		  }
		  .email{
			  width: 320px;
			  height: 50px;
			  margin: 0px 0px 0px 25px;
		  }
		  .password-text{
			  margin: 20px 0px 80px 0px;
		  }
		  .password-label{
			  font-size: 20px;
		  }
		  .password-input{
			  width: 320px;
			  height: 50px;
			  margin: 0px 0px 0px 25px;
		  }
		  .zhuce-text{
			  margin: 40px 0px 50px 110px;
		  }
		  .zhuce-button{
			  font-size: 25px;
			  width: 327px;
			  height: 50px;
			  margin: 0px 0px 90px 0px;
			  background-color: dodgerblue;
		  }
		  .body-center-text-foot{
			  position: fixed;
			  bottom: 0px;
		  }
	  </style>

  </head>
  <body>
  <div class="big">
	  <div class="head">
		  <div class="head-lcon-center">
			  <div class="head-lcon-left">
				  <img src="/img/logo.png" class="head-lcon-left-img">
				  <div class="head-lcon-left-p">注册页面</div>
			  </div>
			  <div class="head-lcon-right">
				  <a href="#">网易云登录</a>
				  <a href="#">帮助与文档</a>
			  </div>
		  </div>
	  </div>
	  <div class="body">
		  <div class="kong"></div>
		  <div class="body-center">
			  <div class="body-center-text">
				  <div class="body-center-text-head">
					  <p class="body-center-text-head-p">邮箱注册</p>
				  </div>
				  <div class="body-center-text-body">
					  <form action="regist.action" method="post">
						  <input type="hidden" name="method" value="register">
						  <div>
							  用户名：<input type="text" name="username" value="${sessionScope.users.username}"/><br/>
						  </div>
						  <div class="email-text">
							  <label for="username" class="email-dizhi">邮箱地址</label>
							  <input type="text" name="email" id="username" class="email"
									 onfocus="showTips('email','xxx@qq.com')" onblur="check('email','邮箱不能为空')">
							  <span id="emailspan"></span>
						  </div>
						  <div class="password-text">
							  <label for="password" class="password-label">输入密码</label>
							  <input type="text" name="password" id="password" class="password-input"
									 onfocus="showTips('password','密码必填')" onblur="check('password','密码不能为空!')">
							  <span id="emailspan1"></span>
						  </div>
						  <div class="zhuce-text">
							  <input type="submit" name="zhuce" id="zhuce"  value="注册"
									 class="zhuce-button" onfocus="change(this)">
						  </div>
					  </form>
				  </div>
				  <div class="body-center-text-foot">
					  <p>版权为<strong>fw</strong>，仅供粘贴复制剪切抄袭方便使用</p>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>

  </body>
  <script type="text/javascript">
      function check(id,info){
          //1.获取用户输入的用户名数据
          var uValue = document.getElementById(id).value;
          //2.进行校验
          if(uValue==""){
              document.getElementById(id+"span").innerHTML="<font color='red'>"+info+"</font>";
          }else{
              document.getElementById(id+"span").innerHTML="";
          }
      }
      function showTips(id,info){
          document.getElementById(id+"span").innerHTML="<font color='gray'>"+info+"</font>";
      }
      function change(ele) {
          var zhuce = document.getElementById("zhuce");
          zhuce.style.backgroundColor="whitesmoke";
      }
  </script>
<%--  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="../../user" method="post">
	<input type="hidden" name="method" value="register"/>
	用户名：<input type="text" name="username" value="${sessionScope.users.username}"/><br/>
	密　码：<input type="password" name="password"/><br/>
	邮　箱：<input type="text" name="email" value="${sessionScope.users.email}"/><br/>
	&lt;%&ndash;输入校验：<input type="text" name="check">&ndash;%&gt;
	<input type="submit" value="注册"/>
</form>--%>
  <%--</body>--%>
</html>
