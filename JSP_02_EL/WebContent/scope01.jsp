<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	pageContext.setAttribute("msg", "너무 졸립다");
	request.setAttribute("msg", "자고싶다");
	session.setAttribute("msg", "그러면 안돼! 공부해야지");
	application.setAttribute("msg", "역쉬 난 너무 멋져");
%>

<h1>${msg}</h1>
<h1>${sessionScope.msg}</h1>
<h1>${requestScope.msg}</h1>
<h1>${param.msg}</h1>

￦{value}
￦{value1}{value2}