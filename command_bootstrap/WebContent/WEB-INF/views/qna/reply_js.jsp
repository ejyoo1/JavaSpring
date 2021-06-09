<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.min.js"></script> <!-- handlebars cdn -->
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi" >
	<i class="fas fa-comments bg-yellow"></i>
 	<div class="timeline-item" >
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regdate}}
	 		<a class="btn btn-primary btn-xs" id="modifyReplyBtn" data-rno={{rno}}
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body" id="{{rno}}-replytext">{{replytext}} </div>
	</div>
</div>
{{/each}}	
</script>
<script> //댓글리스트
var replyPage = 1; // 무조건 1번 페이지 보이게 함.

window.onload=function(){ // jquery 라이브러리 로드 전에 실행되므로 onload 함수 감싸줌.
	getPage("<%=request.getContextPath()%>/replyqna/list.do?qno=${qna.qno}&page="+replyPage);
}

// 위의 Handle bars 템플릿에서 설정한 {{prettifyDate 값}} 에 대한 함수를 js에서 등록함.
Handlebars.registerHelper("prettifyDate",function(timeValue){ // Handlbars에 날짜 출력 함수 등록
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	return year + "/" + month + "/" + date;
	
});


var printData = function(replyArr, target, templateObject){ // jsonArrayData, 어디에 뿌릴지, 템플릿 데이터
	var template = Handlebars.compile(templateObject.html()); // reply-list-template
	var html = template(replyArr);
	$('.replyLi').remove();
	target.after(html);
}

function getPage(pageInfo){ // 비동기 호출
	$.getJSON(pageInfo, function(data){
		printData(data.replyList,$('#repliesDiv'),$('#reply-list-template'));
	});
}
</script>