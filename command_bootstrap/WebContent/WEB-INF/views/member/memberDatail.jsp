<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>ejyoo Adminmanagement</title>
	
	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
	<script src="<%= request.getContextPath() %>/js/memberSystemUtil.js"></script>
	<script>
		${script}
	</script>
</head>
<body>
	<div class="wrapper">
		
	  
	  
	
	  <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">회원 상세</h1>
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
<!-- 	              <li class="breadcrumb-item"><a href="#">Home</a></li> -->
<!-- 	              <li class="breadcrumb-item active">Starter Page</li> -->
	            </ol>
	          </div><!-- /.col -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
	
	    <!-- Main content -->
	    <div class="content">
	      <div class="container-fluid">
	       	<div class="row">
	       		<!-- left column -->
	       		<div class="col-sm-12">
	       			<!-- jquery validation -->
	       			<div class="card card-secondary">
	              <div class="card-header">
	                <h3 class="card-title">회원 상세보기</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form id="fm" method="post" action="/member/registForm.do">
	                <div class="card-body">
	                	<div class="form-group">
	                    <label for="id">아이디</label>
	                    <input type="text" name="id" class="form-control form-control-border" id="id" value="${member.id}" readonly>
	                  </div>
	                  <div class="form-group">
	                    <label for="pwd">비밀번호</label>
	                    <input type="password" name=pwd" class="form-control form-control-border" id="pwd" value="${member.pwd}" readonly/>
	                  </div>
	                  <div class="form-group">
	                    <label for="phone">휴대전화</label>
	                    <input type="text" name="phone" class="form-control form-control-border" id="phone" value="${member.phone}" readonly/>
	                  </div>
	                  <div class="form-group">
	                    <label for="email">이메일</label>
	                    <input type="email" name="email" class="form-control form-control-border" id="email" value="${member.email}" readonly/></td>
	                  </div>
	                </div>
	                <!-- /.card-body -->
	                <div class="card-footer">
	                  <a type='button' class="btn btn-secondary btn-sm" href='/member/modifyForm.do?id=${member.id}'>수정</a>
	                  <a type='button' class="btn btn-secondary btn-sm" onclick="confirmMessage('delete','/member/remove.do?id=${member.id}')">삭제</a>
	                	<a type="button" class="btn btn-secondary btn-sm" onclick="javascript:location.href='javascript:history.go(-1);'">이전으로</a>
	                </div>
	              </form>
	            </div>
	            <!-- /.card -->
	            </div>
	          <!--/.col (left) -->
	        </div>
	        <!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content -->
	  </div>
	  <!-- /.content-wrapper -->
	
	  
	</div>
	<!-- ./wrapper -->
	
	<!-- REQUIRED SCRIPTS -->
	
	<!-- jQuery -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>
	<!-- Page specific script -->
	<script>
		$(function () {
			$('.form-control-border').css("background-color","white")
		});
	</script>
</body>
</html>