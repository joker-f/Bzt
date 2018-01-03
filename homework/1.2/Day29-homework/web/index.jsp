<%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/2
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>可以改成注册页面</title>
  </head>
  <body>
  <form action="inserts" method="post">
    <label for="username">用户名</label>
    <input type="text" id="username" name="username">
    <label for="gender">性别</label>
    <input type="text" id="gender" name="gender">

    <input type="submit">
  </form>

  <a href="show.html">去看看数据</a>
  </body>
</html>
