<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="kr.or.ddit.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html lang="kr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no">
  <title>ejyoo Adminmanagement</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Page specific css -->
  <style>
  .table.dataTable  {
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    font-size: 13px;
	}
	</style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원 관리</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
<!--               <li class="breadcrumb-item"><a href="#">Home</a></li> -->
<!--               <li class="breadcrumb-item active">Starter Page</li> -->
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
       	<div class="row">
       		<div class="col-sm-12">
   					<div class="card">
	     				<div class="card-header">
	     					<div class="row">
	     						<div class="col-sm-10">
	     							<h3 class="card-title">회원 목록</h3>
	     						</div>
	     						<div class="col-sm-2">
	     							<a type='button' class="btn btn-secondary btn-sm" href='/member/registForm.do' >회원등록</a>
	     						</div>
	     					</div>
	     				</div> <!-- /.card-header -->
     				
	     				<div class="card-body">
	     					<table id="example1" class="table table-bordered table-striped">
	                <thead>
		                <tr>
		                	<th>#</th>
		                  <th>#</th>
		                  <th>아이디</th>
		                  <th>비밀번호</th>
		                  <th>#</th>
		                  <th>#</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${memberList}" var="member">
	                		<c:set var="i" value="${i+1}"/>
										<tr>
											<td>${i}</td>
											<td><i class="fas fa-info-circle curserStyle" onclick="goLocation('/member/detail.do?id=${member.id}')"></i></td>
											<td>${member.id}</td>
											<td>${member.pwd}</td>
											<td>
												<a type='button' class="btn btn-secondary btn-sm" href='/member/modifyForm.do?id=${member.id}'>수정</a>
											</td>
											<td>
												<a type='button' class="btn btn-secondary btn-sm" onclick="confirmMessage('Admindelete','/member/remove.do?id=${member.id}')">삭제</a>
											</td>
										</tr>
									</c:forEach>
	                </tbody>
	                <tfoot>
		                <tr>
		                  <th>#</th>
		                  <th>#</th>
		                  <th>아이디</th>
		                  <th>비밀번호</th>
		                  <th></th>
		                  <th></th>
		                </tr>
	                </tfoot>
	              </table>
	            </div><!-- /.card-body -->
	           	<div class="card-footer"></div><!-- /.card-footer -->
     				</div><!-- /.card -->
      		</div><!-- ./col -->
       	</div><!-- ./row -->
      </div><!-- /.container-fluid -->
    </div><!-- /.content -->
  </div><!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables  & Plugins -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jszip/jszip.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/pdfmake/pdfmake.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/pdfmake/vfs_fonts.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>
<!-- common util -->
<script src="<%= request.getContextPath() %>/resources/js/memberSystemUtil.js"></script>
<!-- Page specific script -->
<script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
//       "buttons": ["copy", "excel", "pdf", "print", "colvis"], //"csv"
      buttons: [
    	    {
    	        extend:    'copyHtml5',
    	        text:      'copy',
    	        titleAttr: 'Copy to Clipboard',
    	    },
    	    {
    	        extend:    'excel',
    	        text:      'excel',
    	        titleAttr: 'Download to Excel',
    	    },
    	    {
    	        extend:    'pdf',
    	        text:      'pdf',
    	        titleAttr: 'Download to pdf',
    	    },
    	    {
    	        extend:    'print',
    	        text:      'print',
    	        titleAttr: 'Print',
    	    },
    	    {
    	        extend:    'colvis',
    	        text:      'columns',
    	        titleAttr: 'Visible Columns',
    	        columns:   ':not(.noVis)',
    	    },
    	    {
    	        extend:    'pageLength',
    	        text:      'rows',
    	        titleAttr: 'Number of Rows',
    	    },
    	],
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)'); 
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
    });
    $('.curserStyle').css('cursor','pointer');
    $(":button").attr("aria-controls","example1").css("border","1px solid #56606a");
  });
  
  function goLocation(url){
		location.href = url;
	}
  
  function confirmMessage(type,url){
		if(type=="Admindelete"){
			if (confirm("정말 삭제하시겠습니까??") == true){
				goLocation(url);
			}else{
			    return;
			}
		}
	}
</script>
</body>
</html>