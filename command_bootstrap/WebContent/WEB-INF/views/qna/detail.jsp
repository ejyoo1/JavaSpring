<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
								<input type="text" class="form-control" id="writer" readonly value="${qna.writer }"/>
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-4">
							 <label for="regDate">작성일</label>
							 <input type="text" class="form-control" id="regDate" readonly 
									value="<fmt:formatDate value="${qna.regdate }" pattern="yyyy-MM-dd" />" />
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-4" >
							 <label for="viewcnt">조회수</label>
							 <input type="text" class="form-control" id="viewcnt" readonly value="${qna.viewcnt }"/>
							</div><!-- ./form-group col-sm-4 -->
							
							<div class="form-group col-sm-12">
                <label for="content">내 용</label>
                <div id="content">${qna.content }</div>  
              </div><!-- ./form-group col-sm-4 -->
							
						</div><!-- ./row -->
					</div><!-- ./card-body -->
				</div><!-- ./card card-outline card-primary -->
			</div><!-- ./col-md-12 -->
		</div><!-- ./row -->
	</section>
	
	<section class="content container-fluid">
	    	<!-- reply component start --> 
			<div class="row">
				<div class="col-md-12">
					<div class="card card-info">					
						<div class="card-body">
							<!-- The time line -->
							<div class="timeline">
								<!-- timeline time label -->
								<div class="time-label" id="repliesDiv">
									<span class="bg-green">Replies List </span>							
								</div>
							</div>
							<div class='text-center'>
								<ul id="pagination" class="pagination justify-content-center m-0">
									
								</ul>
							</div>
						</div>
						<c:if test="${loginUser.id eq 'super'}">
							<div class="card-footer">
								<label for="newReplyWriter">Writer</label>
								<input class="form-control" type="hidden" placeholder="USER ID"	 id="newReplyWriter" readonly value="${loginUser.id }"> 
								<label for="newReplyText">Reply Text</label>
								<input class="form-control" type="text"	placeholder="REPLY TEXT" id="newReplyText">
								<br/>
								<button type="button" class="btn btn-primary" id="replyAddBtn" onclick="replyRegist_go();">ADD REPLY</button>
							</div>		
						</c:if>		
					</div>			
					
				</div><!-- end col-md-12 -->
			</div><!-- end row -->
    </section>
</div><!-- ./style div -->

<!-- Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button> 
			</div><!-- ./modal-header -->
			<div class="modal-body">
				<p><input type="text" id="replytext" class="form-control"></p>
			</div><!-- ./modal-body -->
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="replyModBtn" onclick="replyModify_go();">Modify</button>
				<button type="button" class="btn btn-danger" id="replyDelBtn" onclick="replyRemove_go();">DELETE</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div><!-- ./modal-footer -->
		</div><!-- ./modal-content -->
	</div><!-- ./modal-dialog -->
</div><!-- ./modal modal-default fade -->

<form role="form">
  <input type="hidden" name="qno" value="${qna.qno}" />
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
		var formObj = $("form[role='form']");
		//alert("click remove btn");
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer){		
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	}
</script>

<%@include file="./reply_qna_js.jsp" %>