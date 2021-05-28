<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int k=5;
	pageContext.setAttribute("k",k);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title></title>
</head>
<body>
	<%
		int sum = 0;
		for(int i = 1 ; i < 11 ; i++){
			sum += i;
			out.print(i+(i==10 ? "" : "+"));
		}
		out.print("="+sum);
	%>
	
	<br/>
	
	<c:set var="sum" value="0" scope="page" />
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:set var="sum" value="${sum+i}" />
		${i}${i eq 10 ? '' : '+' }
	</c:forEach>
	= ${sum }
</body>
</html>