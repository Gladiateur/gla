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
    
    <title>My JSP 'cart.jsp' starting page</title>
    
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
    
    <c:if test="${empty cart.cartMapping}">
    	无数据
    </c:if>
     <c:if test="${!empty cart.cartMapping}">
     <table width="50%" align="center" border="1">
     	<tr>
     		<td>名称</td>
     		<td>价格</td>
     		<td>数量</td>
     		<td>累计价格</td>
     	</tr>
		
    	<c:forEach items="${cart.cartMapping}" var="item">
    		<tr>
    				<td>${item.value.t.name }</td>
    				<td>${item.value.t.price }</td>
    				<td>${item.value.count }</td>
    				<td>${item.value.total }</td>
    		</tr>
    		
    	</c:forEach>
    </table>
    	<p align="center">总数：${cart.sumCount }</p>
    	<p align="center">总价：${cart.sumPrice }</p>
    	<a href="clear">清空购物车</a>
    </c:if>
  </body>
</html>
