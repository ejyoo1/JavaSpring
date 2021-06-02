<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head></head>

<title>회원등록</title>

<body>

   <!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" >
  
   <!-- Main content -->
   <section class="content register-page">
      <div class="register-box">
         <div class="login-logo">
             <a href="<%=request.getContextPath()%>/member/registForm.do"><b>회원 등록</b></a>
           </div>
         <!-- form start -->
         <div class="card">            
            <div class="register-card-body">
               <form role="form" class="form-horizontal" action="regist.do" method="post">                  
                  <input type="hidden" name="picture" />
                  <div class="input-group mb-3">
                     <div class="mailbox-attachments clearfix" style="text-align: center;">
                        <div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>
                        <div class="mailbox-attachment-info">
                           <div class="input-group input-group-sm">
                              <label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon">파일선택</label><!-- 버튼 효과를 내줌 -->
                              <input id="inputFileName" class="form-control" type="text" name="tempPicture" disabled/>
                              <span class="input-group-append-sm">                                 
                                 <button type="button" class="btn btn-info btn-sm btn-append" onclick="upload_go();">업로드</button>                                 
                              </span>
                           </div>
                        </div>
                     </div>
                     <br />
                    </div>   
                    <div class="form-group row">
                      <label for="id" class="col-sm-3" style="font-size:0.9em;" >
                         <span style="color:red;font-weight:bold;">*</span>아이디</label>   
                     <div class="col-sm-9 input-group input-group-sm">
                        <input name="id" 
                           onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
                        type="text" class="form-control" id="id" placeholder="13글자 영문자,숫자 조합">
                        <span class="input-group-append-sm">   
                           <button type="button" onclick=""  class="btn btn-info btn-sm btn-append">중복확인</button>
                        </span>                        
                     </div>                        
                  </div>
                  <div class="form-group row">
                     <label for="pwd" class="col-sm-3" style="font-size:0.9em;">
                        <span style="color:red;font-weight:bold;">*</span>패스워드</label>
                     <div class="col-sm-9 input-group-sm">                        
                        <input class="form-control" name="pwd" type="password" class="form-control" id="pwd"
                              placeholder="20글자 영문자,숫자,특수문자 조합" />
                     </div>
                     
                  </div>
                  <div class="form-group row">
                     <label for="name" class="col-sm-3" style="font-size:0.9em;">
                        <span style="color:red;font-weight:bold;">*</span>이 름</label>
                     <div class="col-sm-9 input-group-sm">                        
                        <input class="form-control" name="name" type="text" class="form-control" id="name"
                              placeholder="이름을 입력하세요" />
                     </div>
                     
                  </div>      
                  <div class="form-group row">
                     <label for="authority" class="col-sm-3" style="font-size:0.9em;" >권 한</label>
                     <div class="col-sm-9">
                        <select name="authority" class="form-control" style="font-size:0.9em;">
                           <option value="ROLE_USER">사용자</option>
                           <option value="ROLE_MANAGER">운영자</option>
                           <option value="ROLE_ADMIN">관리자</option>
                        </select>
                     </div>
                  </div>               
                  <div class="form-group row">
                     <label for="email" class="col-sm-3" style="font-size:0.9em;">이메일</label>
                     <div class="col-sm-9 input-group-sm">
                        <input name="email" type="email" class="form-control" id="email"
                              placeholder="example@naver.com">
                     </div>
                  </div>
                  <div class="form-group row">
                     <label for="phone" class="col-sm-3 control-label">전화번호</label>
                     <div class="col-sm-9">   
                        <div class="input-group-sm">
                           <select style="width:75px;" name="phone" id="phone" class="form-control float-left">
                              <option value="">-선택-</option>
                              <option value="010">010</option>
                              <option value="011">011</option>
                              <option value="017">017</option>
                              <option value="018">018</option>
                           </select>                     
                           <label class="float-left" style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>                              
                           <input style="width:68px;" name="phone" type="text" class="form-control float-left" />
                           <label class="float-left" style="padding: 0; text-align: center;">&nbsp;-</label>
                           <input style="width:68px;" name="phone" type="text" class="form-control float-right" />                  
                        </div>
                     </div>
                  </div>
                  
                  <div class="card-footer">
                     <div class="row">                        
                        <div class="col-sm-6">
                           <button type="button" id="registBtn" onclick="" class="btn btn-info">가입하기</button>
                         </div>
                         
                         <div class="col-sm-6">
                           <button type="button" id="cancelBtn" onclick="CloseWindow('/index.do?mCode=M010100')"
                              class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
                        </div>
                     
                     </div>
                  </div>
               </form>               
            </div><!-- register-card-body -->
         </div>
      </div>
   </section>      <!-- /.content -->
</div>
<!-- /.content-wrapper -->


<form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data"><!-- 화면이 새로고침되게이 submit은 불가능 -->
<!-- 템플릿으로 인해서 Jquery가 들어오는 시기는 Jquery include 위에 들어옴.   decorator:body  ==> function call 해야 함. 또는 윈도으ㅜ 업로드 펑션쓰던가. -->
   <input id="inputFile" name="pictureFile" type="file" class="form-control" onchange="picture_go();" style="display:none;">
   <input id="oldFile" type="hidden" name="oldPicture" value="" />
   <input type="hidden" name="checkUpload" value="0" />   
</form>

<script src="/resources/js/common.js"></script>
<script>
var formData = "";
function picture_go(){
	formData = new FormData($('form[role="imageForm"]')[0]); 
	var form = $('form[role="imageForm"]');
	var picture = form.find('[name=pictureFile]')[0];
	
	// 업로드 확인 변수 초기화
	form.find('[name="checkUpload"]').val(0);
	var fileFormat = 
		picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
	
	// 이미지 확장자 jpg 확인
	if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
		alert("이미지는 jpg/jpeg 형식만 가능합니다.");
		picture.val("");
		return;
	}
	
	// 이미지 파일 용량 체크
	if(picture.files[0].size>1024*1024*1){ 
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	}
	
// 	alert("성공");
	document.getElementById('inputFileName').value=picture.files[0].name; 
	
	if(picture.files && picture.files[0]){ 
		var reader = new FileReader(); 
		reader.onload = function (e) { 
			$('div#pictureView')
				.css({'background-image':'url('+e.target.result+')',
							'background-position':'center',
							'background-size':'cover',
							'background-repeat':'no-repeat'
						});
		}
		reader.readAsDataURL(picture.files[0]); 
	}
}

function upload_go(){
// 	alert("upload btn click");
	if($('input[name="pictureFile"]').val()==""){
		alert("사진을 선택하세요.");
		$('input[name="pictureFile"]').click();
		return;
	}
	
	$.ajax({
		url : "<%=request.getContextPath()%>/member/picture.do",
		data : formData,
		type : 'post',
		processData : false,
		contentType : false,
		success : function(data){
			// 업로드 확인 변수 세팅
			$('input[name="checkUpload"]').val(1);
			
			// 저장된 파일명 저장.
			$('input#oldFile').val(data); 
			$('form[role="form"] input[name="picture"]').val(data);
			
			alert("사진이 업로드 되었습니다.");
		},
		error : function(error){
			alert("현재 사진 업로드가 불가합니다. \n 관리자에게 연락바랍니다.");
		}
	});
}
</script>

</body>










