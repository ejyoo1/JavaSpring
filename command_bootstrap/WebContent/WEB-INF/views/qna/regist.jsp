<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head></head>

<title>질문등록</title>

<section class="content-header">
	<div class="container-fluid">
		<div class="row md-2">
			<div class="col-sm-6">
				<h1>Qna</h1>
			</div><!-- ./col-sm-6 -->
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item">
						<a href="list.do">
							<i class="fa fa-dashboard"></i>Qna
						</a>
					</li>
					<li class="breadcrumb-item active">
					등록
					</li>
				</ol>
			</div><!-- ./col-sm-6 -->
		</div><!-- ./row md-2 -->
	</div><!-- ./container-fluid -->
</section>
<!-- Main content -->
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-9" style="max-width:960px;">
			<div class="card card-outline card-primary">
				<div class="card-header">
					<h3 class="card-title p-1">Qna등록</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary" id="cancelBtn" onclick="CloseWindow();">취소</button>
					</div><!-- ./card-tools -->
				</div><!-- ./card-header -->
				<div class="card-body pad">
					<form role="form" method="post" action="regist.do" name="registForm">
						<div class="form-group">
							<label for="title">제목</label>
							<input type="text" id="title" name="title" class="form-control" placeholder="제목을 쓰세요" />
						</div><!-- ./form-group -->
						
						<div class="form-group">
							<label for="writer">작성자</label>
							<input type="text" id="writer" 
										 name="writer" class="form-control" value="${loginUser.id}" readonly />
						</div><!-- ./form-group -->
						
						<div class="form-group">
							<label for="content">내용</label>
							<textarea class="textarea" name="content" id="content" rows="20" placeholder="1000자 내외로 작성하세요."
							          style="display: none;"></textarea>
						</div><!-- ./form-group -->
					</form>
				</div><!-- ./card-body pad -->
				<div class="card-footer" style="display:none">
				</div><!-- ./card-footer -->
			</div><!-- ./card card-outline card-info -->
		</div><!-- ./col-md-9 -->
	</div><!-- ./row justify-content-center -->
</section>

<script>
window.onload=function(){
	Summernote_start($('#content'));
	
	function Summernote_start(targetObj){
		targetObj.summernote({
			placeholder:'여기에 내용을 적으세요.',
			height:250,
			disableResizeEditor:true,
			callbacks:{
				onImageUpload : function(files, editor, welEditable){
					for(var file of files){
						if(file.size > 1024*1024*5){
							alert("이미지는 5MB 미만입니다.");
							return;
						}
						if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
							alert("JPG 이미지 형식만 가능합니다.");
							return;
						}
					}
					
					for(var file of files){
						sendFile(file,this);
					}
				},
				onMediaDelete : function(target){
					
				}
			}
		});
	}// Summernote_start
}

function sendFile(file,el){
	var form_data = new FormData();
	form_data.append("file",file);
	$.ajax({
		data : form_data,
		type : "POST",
		url : '<%=request.getContextPath()%>/uploadImg.do',
		cache : false,
		contentType : false,
		processData : false,
		success : function(img_url){
			$(el).summernote('editor.insertImage',img_url);
		},
		error:function(){
			alert("이미지 업로드에 실패했습니다.");
		}
	});
}

function regist_go(){
	var form = document.registForm;
	if(form.title.value==""){
		alert("제목은 필수입니다.");
		return;
	}
	if(form.content.value==""){
		alert("내용은 필수입니다.");
		return;
	}
	form.submit();
}
</script>

