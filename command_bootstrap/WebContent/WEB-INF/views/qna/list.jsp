<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker}" />
<c:set var="cri" value="${dataMap.pageMaker.cri}" />
<c:set var="qnaList" value="${dataMap.qnaList}" />

<head></head>

<title>질문목록</title>

<body>
<!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>질문목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>질의응답(QnA)
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
	
	<!-- Main content -->
    <section class="content">		
		<div class="card">
			<div class="card-header with-border">
				<button type="button" class="btn btn-primary" id="registBtn" onclick="OpenWindow('registForm.do','공지등록',800,700);">질문등록</button>				
				<div id="keyword" class="card-tools" style="width:450px;">
					<div class="input-group row">
						<select class="form-control col-md-3" name="perPageNum" id="perPageNum"
					  		onchange="list_go();">
					  		<option value="10" >정렬개수</option>
					  		<option value="20" ${cri.perPageNum == 2 ? 'selected':''}>2개씩</option><!-- cri.perPageNum -->
					  		<option value="50" ${cri.perPageNum == 5 ? 'selected':''}>5개씩</option>
					  		<option value="100" ${cri.perPageNum == 10 ? 'selected':''}>10개씩</option>
					  		
					  	</select>						
						<select class="form-control col-md-4" name="searchType" id="searchType">
							<option value="tcw"  ${cri.searchType eq 'tw' ? 'selected':'' }>전 체</option><!-- cri.searchType -->
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
							<option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>작성자</option>
						</select>					
						<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" onclick="list_go(1);" 
							data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>						
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center" >					
					<tr style="font-size:0.95em;">
						<th style="width:10%;">번 호</th>
						<th style="width:50%;">제 목</th>
						<th style="width:15%;">작성자</th>
						<th>등록일</th>
						<th style="width:10%;">조회수</th>
					</tr>		
					<c:if test="${empty qnaList}">		
						<tr>
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${qnaList}" var="qna">
						<tr style='font-size:0.85em;'>
							<td>1</td>
							<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; 
												white-space: nowrap; text-overflow: ellipsis;">
												
							<a href="javascript:OpenWindow('detail.do?qno=1','상세보기',800,700);">
								${qna.title}      						
							</a>
							</td>
							<td>${qna.writer}</td>
							<td>
								<fmt:formatDate value="${qna.regdate}" pattern="yyyy-MM-dd" />
							</td>
							<td><span class="badge bg-red">${qna.viewcnt}</span></td>
						</tr>
					</c:forEach>
<!-- 					<span class="badge bg-success">답변완료</span>    -->
				</table>				
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagingtion.jsp" %>
			</div>
		</div>
		
    </section>
    <!-- /.content -->

<script src="/resources/js/common.js" ></script>
</body>