<%@page import="kr.or.ddit.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
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
	            <h1 class="m-0">회원 등록</h1>
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
	       			<!-- left col -->
	       			<div class="col-sm-2"></div><!-- ./col-sm-2 -->
	       			<div class="col-sm-8">
	       				<!-- jquery validation -->
	       				<div class="card card-secondary">
	              	<div class="card-header">
	                	<h3 class="card-title">회원 등록</h3>
	              	</div><!-- /.card-header -->
	              	<!-- form start -->
	              	<form id="fm" method="post" action="/member/regist.do">
	                	<div class="card-body">
	                		<div class="form-group">
	                    	<label for="id">아이디</label>
	                    	<div class="input-group">
		                    	<input type="text" name="id" class="form-control" id="id" placeholder="아이디를 입력하세요.">
		                    	<div class="input-group-append">
	                        	<span class="input-group-text" id="idchkSpan" onclick="validationId('<%=request.getContextPath() %>')">중복확인</span>
	                      	</div>
	                    	</div>
	                  	</div>
	                  <div class="form-group">
	                    <label for="pwd">비밀번호</label>
	                    <input type="password" name="pwd" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요.">
	                  </div>
	                  <div class="form-group">
	                    <label for="phone">휴대전화</label>
	                    <input type="text" name="phone" class="form-control" id="phone" placeholder="휴대전화 번호를 입력하세요.">
	                  </div>
	                  <div class="form-group">
	                    <label for="email">이메일</label>
	                    <input type="email" name="email" class="form-control" id="email" placeholder="이메일을 입력하세요.">
	                  </div>
	                  <div class="form-group mb-0">
	                    <div class="custom-control custom-checkbox">
	                      <input type="checkbox" name="terms" class="custom-control-input" id="exampleCheck1">
	                      <label class="custom-control-label" for="exampleCheck1">I agree to the <a href="#">terms of service</a>.</label>
	                    </div>
	                  </div>
	                </div><!-- /.card-body -->
	                
	                <div class="card-footer">
	                  <button type="submit" class="btn btn-secondary btn-sm">등록</button>
	                  <input type="text" name="idchk" id="idchk" hidden/>
	                </div><!-- /.card-footer -->
	              </form>
	            </div>
	            <!-- /.card card-secondary -->
	            </div><!--/.col-sm-8 -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div><!-- ./content -->
	  </div><!-- /.content-wrapper -->
	
	  
	</div>
	<!-- ./wrapper -->
	
	<!-- REQUIRED SCRIPTS -->
	
	<!-- jQuery -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jquery-validation -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery-validation/additional-methods.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>
	<!-- Page specific script -->
	<script>
	$(function () {
		  $.validator.setDefaults({
		    submitHandler: function () {
		    	var flag = $("#idchk").text();
		    	console.log("zdqeqweqwe"+flag);
		    	if(!flag){
		    		alert("아이디 중복검사를 진행해주세요.");
		    		return false;
		    	}else{
		    		return true;
		    	}
		    }
		  });
		  
		  $.validator.addMethod("englishLetters", function(value, element){
			  return this.optional(element) || value.match(/^[a-zA-Z0-9\.\,\s]+( [a-zA-Z0-9\.\,\s]+)*$/);
			}, "영어로 입력 해주세요.");
	
			$.validator.addMethod("koreanLetters", function(value, element){
				return this.optional(element) || value.match(/^[가-힣]+$/);
			}, "한글로 입력 해주세요.");
		  
			$.validator.addMethod("hasBlank", function(value, element) {
				return this.optional(element) || !value.match(" ");
			}, "빈 칸은 입력하실 수 없습니다.");
			
		  $.validator.addMethod("idValidator", function(value, element){
		  	return this.optional(element) || value.match(/^(?=.*[a-z])[a-z0-9]{4,12}$/);
		  }, "4~12자 영문 소문자, 숫자를 사용하세요. 영문 소문자는 필수입니다.");
			
			$.validator.addMethod("phoneValidator", function(phone_number, element) {
				phone_number = phone_number.replace(/\s+/g, "");
				return this.optional(element) || phone_number.match(/^\d{3}\d{3,4}\d{4}$/);
			}, "잘못된 휴대폰 번호입니다. 01012345678 형식으로 입력하세요.");
			
		  
		  $('#fm').validate({
		    rules: {
		    	id: {
		    		required: true,
		    		hasBlank: true
		    	},
		      pwd: {
		        required: true,
		        englishLetters: true,
		        hasBlank: true
		      },
		      phone: {
		    	  required: true,
		    	  phoneValidator: true,
		    	  hasBlank: true
		      },
		      email: {
			        required: true,
			        hasBlank: true
			      },
		      terms: {
		        required: true
		      },
		    },
		    messages: {
		    	id:{
		    			required: "아이디를 입력해 주세요.",
		    			englishLetters: "4~12자 영문 소문자, 숫자를 사용하세요. 영문 소문자는 필수입니다."
		    	},
		    	pwd: {
			        required: "비밀번호를 입력해 주세요.",
			      },
		    	phone: {
			    	  required: "휴대폰 번호를 입력해 주세요.",
			    	  phoneNumbers: "올바른 휴대전화 번호를 입력하여주세요. (01012345678)"
			      },
		    	email: {
		        required: "이메일을 입력해 주세요.",
		        email: "올바른 이메일 주소를 입력해주세요. test@test.com"
		      },
		      terms: "정보 제공 동의 체크해주세요."
		    },
		    errorElement: 'span',
		    errorPlacement: function (error, element) {
		      error.addClass('invalid-feedback');
		      element.closest('.form-group').append(error);
		    },
		    highlight: function (element, errorClass, validClass) {
		      $(element).addClass('is-invalid');
		    },
		    unhighlight: function (element, errorClass, validClass) {
		      $(element).removeClass('is-invalid');
		    }
		    
		  });
		});
		
</script>
<%-- <script src="<%= request.getContextPath() %>/js/memberSystemUtil.js"></script> --%>
<script>
//ID 유효성 검사
function validationId(reqContextPath){
	var id = $("#id").val();
	
	// 빈 값 체크
	if(isEmpty(id)){
		alert("아이디가 입력되지 않았습니다.");
		$("#id").focus();
	    return;
	}
	
	// 공백 체크
	if(hasBlank(id)) {
		alert("아이디에 빈 칸은 입력하실 수 없습니다.");
		$("#id").focus();
		return false;
	}
	
    var regExp = /^(?=.*[a-z])[a-z0-9]{4,12}$/;
    if(!regExp.test(id)){ // 정규식 값에 맞는지 확인함.
    	alert("4~12자 영문 소문자, 숫자를 사용하세요. 영문 소문자는 필수입니다.");
    	$("#id").focus();
    	return;
    }
    
    chkId(id,reqContextPath);
}

//ajax chkId
function chkId(id,reqContextPath){
	idFlag = false;
//	runIdCheckFlag = true;
	
	$.ajax({
		url : reqContextPath+"/member/list.do?action=search&id="+id
		, type : "post"
		, async : false
		, success : function(data){
			console.log(data);
			if(data > 0){
				idFlag = false;
				alert("중복된 아이디가 존재합니다.");
		    	$("#id").focus();
		    	$("#id").attr("class","form-control is-invalid")
		    			.attr("aria-describedby","id-error")
		    			.attr("aria-invalid","true");
		    	$("#idchk").text(idFlag);
		    	return;
			}else{
				idFlag = true;
				alert("사용가능한 아이디입니다.");
				$("#idchk").text(idFlag);
			}
		}
		, error : function(xhr){
			console.log(xhr);
			alert("ID 중복 검사 중 오류가 발생했습니다. 관리자에게 문의하세요.");
		}
	});
}

//빈 값 체크
function isEmpty(val) {
	if(val == undefined) return true;
	if(val == null) return true;
	if(val == "null") return true;
	
	val = jQuery.trim(val);
	if(val.length == 0) return true;
	
	return false;
}

//공백 체크
function hasBlank(str) {
	return str.match(" ");
}
</script>
</body>
</html>