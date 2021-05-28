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
		if(k>3){
			out.println("잘한다");
		}
	%>
	<c:if test="${k>3}">
		잘한다.
	</c:if>
</body>
</html>