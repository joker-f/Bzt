<%@ page import="java.util.List" %>
<%@ page import="com.lanou3g.been.Book" %>
<%@ page import="com.lanou3g.dao.BookDao" %>
<%@ page import="com.lanou3g.dao.UserDao" %>
<%@ page import="com.lanou3g.been.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %><%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/3
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>主页</title>
  <script src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
<%--<button>点我</button>--%>
<%--<table border="1px" id="userTable">
    <tr>
        <td>姓名</td>
        <td>密码</td>
    </tr>
</table><br>--%>
<%--<table border="1px" id="bookTbale">
    <tr>
        <td>书名</td>
        <td>作者</td>
        <td>价钱</td>
    </tr>
</table>--%>

<table border="1px">
    <tr>
        <td>用户名</td>
        <td>密码</td>
    </tr>
    <%
        User user = (User) session.getAttribute("user");
        if(user != null){
    %>
        <tr>
            <td><%=
            user.getUsername()
            %></td>
            <td><%=
            user.getPassword()
            %></td>
        </tr>
    <%
        }else {
            response.sendRedirect("null.jsp");
        }
    %>

</table>
<table border="1px">
    <tr>
        <td>id</td>
        <td>书名</td>
        <td>作者</td>
        <td>价钱</td>
    </tr>
        <%
    BookDao bookDao = new BookDao();
     List<Book> books = bookDao.insertAllBook();
    for (Book book : books) {
  %>
    <tr>
        <td><a href=""><%
            out.write(book.getBid());
        %></a></td>
        <td><%
            out.write(book.getBname());
        %> </td>
        <td><%
            out.write(book.getBauthor());
        %></td>
        <td><%
            out.write(String.valueOf(book.getBprice()));
        %></td>
    </tr>
        <%
    }
  %>
</table>
<a href="/quit">点击退出到登录页面</a>
</body>
<script type="text/javascript">
/*    //使用json拉取得到用户信息
//    $('button').click(function () {
        $('#userTable>tr').remove();
        $.getJSON("http://localhost:8080/show",function (data, status) {
            if(status == "success"){
                $.each(data,function (index, obj) {
                    var username = obj['username'];
                    var password = obj['password'];
                    $('#userTable').append(
                        $('<tr>').append(
                            $('<td>').text(username)
                        ).append(
                            $('<td>').text(password)
                        )
                    )
                })
            }
        })*/
//    })
        //获取到图书的信息可以不用json拉取获得，可以在jsp页面中直接得到简单方便些
   /* $("#bookTbale>tr").remove();
    $.getJSON("http://localhost:8080/book",function (data,status) {
        if(status=="success"){
            $.each(data,function (index, obj) {
                var bname=obj['bname'];
                var bauthor=obj['bauthor'];
                var bprice=obj['bprice']
                $('#bookTbale').append(
                    $('<tr>').append(
                        $('<td>').text(bname)
                    ).append(
                        $('<td>').text(bauthor)
                    ).append(
                        $('<td>').text(bprice)
                    )
                )
            })
        }
    })*/

</script>

</html>
