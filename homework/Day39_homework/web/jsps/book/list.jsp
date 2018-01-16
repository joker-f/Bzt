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
      <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
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
<%--<c:if test="${sessionScope.books!=null}">
    <c:forEach items="${sessionScope.books}" var="book">
        <tr>
            &lt;%&ndash;<td><a href="#"><img src="<c:url value='/book_img/8758723-1_l.jpg'/>" border="0"/></a></td>&ndash;%&gt;
            <div>
                <a href="<c:url value='/book?method=load&bid=${book.bname}'/> "><img src="<c:url value='${book.image}'/>"></a>
                <br>
            <a href="<c:url value='/book?method=load&bid=${book.bname}'/>">${book.bname}</a>
            </div>
        </tr>
    </c:forEach>
</c:if>--%>


<table>
    <tr>

    </tr>
</table>
</body>
     <%--将图书分类的子页面，通过ajax获取--%>
  <script type="text/javascript">
      function bookClicked() {
          $('table>tr').remove();
//          $.getJSON("http://localhost:8080/book?method=findAll",function (data, status) {
          $.getJSON("",function (data, status) {
              if(status == "success"){
                  $.each(data,function (index, obj) {
                      var bid=obj['bid'];
                      var  bname= obj['bname'];
                      var price = obj['price'];
                      var author=obj['auhtor'];
                      var image=obj['image'];
                      var cid=obj['cid']
                      $('table').append(
                          $('<tr>').append(
                              $('<td>').text(bid)
                          ).append(
                              $('<td>').text(bname)
                          ).append(
                              $('<td>').text(price)
                          ).append(
                              $('<td>').text(author)
                          ).append(
                              $('<td>').text(image)
                          ).append(
                              $('<td>').text(cid)
                          )
                      )
                  })
              }
          })
      }
  </script>
</html>

