<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jsp.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	List<MemberVO> memberList = new ArrayList<MemberVO>();
	for(int i = 1 ; i < 11 ; i++){
		MemberVO member = new MemberVO("mimi","mimi","mimi@naber.com","010-1234-1234");
		memberList.add(member);
	}
	pageContext.setAttribute("memberList",memberList);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title></title>
</head>
<body>
	<h1> 아이디 : ${member.takeId() }</h1>
	<h1> 아이디 : ${member.id }</h1>
<%-- 	<h1> 아이디 : ${memberList.id }</h1> --%>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이메일</th>
				<th>휴대전화</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(memberList != null) for(MemberVO member : memberList){
					pageContext.setAttribute("member",member);
				
			%>
					<tr>
						<td>${member.id }</td>
						<td>${member.pwd }</td>
						<td>${member.email }</td>
						<td>${member.phone }</td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
	
	%>
</body>
</html>