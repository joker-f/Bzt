<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lanou3g.bookstore.book.dao.BookDao" %>
<%@ page import="com.lanou3g.bookstore.book.domain.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
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
	body {
		font-size: 10pt;
	}
	div {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  <body>
<c:if test="${sessionScope.books!=null}">
    <c:forEach items="${sessionScope.books}" var="book">
        <tr>
            <%--<td><a href="#"><img src="<c:url value='/book_img/8758723-1_l.jpg'/>" border="0"/></a></td>--%>
            <div>
                <a href="<c:url value='/book?method=load&bid=${book.bname}'/> "><img src="<c:url value='${book.image}'/>"></a>
                <br>
            <a href="<c:url value='/book?method=load&bid=${book.bname}'/>">${book.bname}</a>
            </div>
        </tr>
    </c:forEach>
</c:if>



  </body>
 
</html>

