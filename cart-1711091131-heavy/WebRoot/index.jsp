<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<jsp:include page="commonTest.jsp" />
  	<c:if test="${empty list}">
  		空
  	</c:if>
  	
  	<c:if test="${!empty list}">
  	<a href="#">购物车</a>
  	
  	
  		<c:forEach items="${list}" var="obj">
  		<form action="newadd" method="post">
    	<table align="center" width="60%" border="1">
    	
    		<tr>
    			<td><input type="hidden" name="id" value="${obj.id }"/></td>
    		</tr>
    		<tr>
    			<td width="50%">名称：</td>
    			<td>${obj.name }<input type="hidden" name="name" value="${obj.name }"/></td>
    		</tr>
    		<tr>
    			<td>单价：</td>
    			<td>${obj.price }<input type="hidden" name="price" value="${obj.price }"/></td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center"><input type="submit" value="加入购物车"></input></td>
    		</tr>
    	</table>
    	</form>
    </c:forEach>
    
  	</c:if>
    
  </body>
</html>
