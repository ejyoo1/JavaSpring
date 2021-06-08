<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div  style="max-width:800px;min-width:420px;margin:0 auto;min-height:812px;">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1>질의응답</h1>
				</div><!-- ./col-sm-6 -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do">
								<i class="fa fa-dashboard"></i>질의응답
							</a>
						</li>
						<li class="breadcrumb-item active">
							상세보기
						</li>
					</ol>
				</div><!-- ./col-sm-6 -->
			</div><!-- ./container-fluid -->
		</div><!-- ./container-fluid -->
	</section>
	
	<!-- Main content -->
	<section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-primary">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
						<div class="card-tools">
							<button type="button" id="modifyBtn" class="btn btn-primary" onclick="modify_go();">수정</button>
							<button type="button" id="modifyBtn" class="btn btn-primary" onclick="remove_go();">삭제</button>
							<button type="button" id="modifyBtn" class="btn btn-primary" onclick="CloseWindow();">닫기</button>
						</div><!-- ./card-tools -->
					</div><!-- ./card-header -->
					<div class="card-body">
						<div class="form-group col-sm-12">
							<label for="title">제목</label>
							<input type="text" class="form-control" id="title" readonly disabled value="${qna.title}" />
						</div><!-- ./form-group col-sm-12 -->
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="writer">작성자</label>
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-4">
							 <label for="regDate">작성일</label>
							 <input type="text" class="form-control" id="regDate" value="<fmt:formatData value="${qna.regdate}" pattern="yyyy-MM-dd" />" readonly />
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-4" >
							 <label for="viewcnt">조회수</label>
							 <input type="text" class="form-control" id="viewcnt" readonly value="${qna.viewcnt }"/>
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-12">
                <label for="content">내 용</label>
                <div id="content">${notice.content }</div>  
              </div><!-- ./form-group col-sm-4 -->
							
						</div><!-- ./row -->
					</div><!-- ./card-body -->
				</div><!-- ./card card-outline card-primary -->
			</div><!-- ./col-md-12 -->
		</div><!-- ./row -->
	</section>
</div><!-- ./style div -->

<form role="form">
  <input type="hidden" name="nno" value="${qna.qno}" />
</form>


<script>
	function modify_go(){
		var formObj = $("form[role='form']");
		formObj.attr({
		      'action':'modifyForm.do',
		      'method':'post'
    });
		formObj.submit();
	}
	
	function remove_go(){
		alert("remove_go");
	}
</script>