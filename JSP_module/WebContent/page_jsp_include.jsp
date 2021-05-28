<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
<title></title>
<style>
	*{
		margin:0; padding:0;
	}
	header{
		height:150px;
	}
	div#content-wrap{
		height:600px;
		position:relative;
	}
	aside{
		width:300px;
		height:100%;
	}
	section{
		position:absolute;
		left:300px;
		top:0;
		width:100%;
		height:100%;
	}
	footer{
		height:150px;
	}
</style>
</head>

<% request.setAttribute("msg", "from page"); %>
<% String msg = "local variable"; %><!-- 공유 안됨 -->

<body>
	<header>
<%-- 		<jsp:include page="/WEB-INF/views/include/header.jsp?msg=hello" ></jsp:include> --%>
		<jsp:include page="/WEB-INF/views/include/header.jsp" >
			<jsp:param name="msg1" value="hello!!!" />
		</jsp:include>
	</header>
	<div id="content-wrap">
		<aside>
			<jsp:include page="/WEB-INF/views/include/aside.jsp" ></jsp:include>
		</aside>
		<section>
			<h1>section</h1>
		</section>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" ></jsp:include>
	</footer>
</body>
</html>