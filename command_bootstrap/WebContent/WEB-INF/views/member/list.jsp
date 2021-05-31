<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 마크업을 템플릿화 하였으므로 구분자만 삽입. -->
<head></head>

<title>회원 목록</title>

<body>

	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>회원관리
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	목록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	
	<!-- Main Content -->
	<section class="content">
		<div class="card">
			<div class="card-header with-border">
				<div id="keyword" class="card-tools" style="width:550px;">
					<div class="input-group row">
					<!-- jquery 라이브러리 위에 function 이 오기때문에 script에 jquery를 사용하여 function을 작성하여도 메서드 찾을 수 없음. -->
					<!-- function call을 사용해야 함. -->
						<select class="form-control col-md-3" name="perPageNum" id="perPageNum"
							onchange="list_go();"> 
							<option value="10">정렬개수</option>
							<option value="2">2개씩</option>
							<option value="3">3개씩</option>
							<option value="5">5개씩</option>
						</select>
					</div><!-- ./input-group row -->
				</div><!-- ./card-tools -->
			</div><!-- ./card-header with-border -->
			<div class="card-body" style="text-align:center;">
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-boardered">
							<thead>
								<tr>
									<th>아이디</th>
									<th>패스워드</th>
									<th>이메일</th>
									<th>전화번호</th>
									<th>등록날짜</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${memberList}" var="member">
									<tr>
										<td>${member.id}</td>
										<td>${member.pwd}</td>
										<td>${member.email}</td>
										<td>${member.phone}</td>
										<td>
											<fmt:formatDate value="${member.regdate }" pattern="yyyy-MM-dd"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
<!-- input Tag에 국한되지 말고 넘길 데이터를 따로 form 태그를 만들고 hidden 하여 jquery로 submit 처리 -->
<form id="jobForm">
	<input type="hidden" name="page" value="" />
	<input type="hidden" name="perPageNum" value="" />
</form>
	
<script>
//function call이기 때문에 javascript이므로 문서가ㅓ 모두 로드된 후에 호출된다.
// jquery는 function call한 이후에 호출하면 된다.
	function list_go(){
		alert($('select#perPageNum').val());
		$('input[name="perPageNum"]').val($('select#perPageNum').val());
		
		$('form#jobForm').attr({ // 페이지 번호가 없으면 페이지 번호는 1
			action:'list.do',
			method:'get'
		}).submit();
	}
</script>
</body>