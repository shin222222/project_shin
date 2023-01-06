<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertFrom.jsp</title>

<script type="text/javascript">

var checkResult = false;
var checkPassResult = false;

function checkPass(pass) {
	// 패스워드 검사를 위한 정규표현식 패턴 작성 및 검사 결과에 따른 변수값 변경
	var span = document.getElementById('checkPassResult');
	
	// 정규표현식 패턴 정의
// 	var lengthRegex = ''^[A-Za-z0-9!@#$%]{8,16}$'; // 길이 및 사용 가능 문자 규정
	var lengthRegex = /^[A-Za-z0-9!@#$%]{8,16}$/; // 길이 및 사용 가능 문자 규정
	var engUpperRegex = /[A-Z]/; // 대문자 규칙
	var engLowerRegex = /[a-z]/; // 소문자 규칙
	var numRegex = /[0-9]/ 		 // 숫자 규칙
	var specRegex = /[!@#$%]/	 // 특수문자 규칙
	
	var count = 0; // 각 규칙별 검사 결과를 카운팅 할 변수
	
	if(lengthRegex.exec(pass)){
		
		checkResult = true;
		
// 		console.log(10);
		span.innerHTML = '사용가능한 패스워드';
		span.style.color = 'GREEN';
		
		if(engUpperRegex.exec(pass)) count ++;
		if(engLowerRegex.exec(pass)) count ++;
		if(numRegex.exec(pass)) count ++;
		if(specRegex.exec(pass)) count ++;
		
		switch (count) {
		case 4: // 보안강도 우수
			span.innerHTML = '보안 강도 : 우수';
			span.style.color = 'GREEN';
			break;
		case 3: // 보안강도 보통
			span.innerHTML = '보안 강도 : 보통';
			span.style.color = 'YELLOW';
			break;
		case 2: // 보안강도 위험
			span.innerHTML = '보안 강도 : 위험';
			span.style.color = 'ORANGE';
			break;
		}
		
		
// 		console.log(count);
		
// 		debugger;
		
	} else {
		checkResult = false;
// 		span.innerHTML = '사용 불가능한 패스워드';
		span.innerHTML = '8 ~ 16 자리 영문자, 숫자, 특수문자(!@#$%) 필수!';
		span.style.color = 'RED';
	}
// 	debugger;
}

function checkRetypePass(){
	var span = document.getElementById('checkRetypePassResult');
	
	var pass = document.getElementById('pass').value;
	var pass2 = document.getElementById('pass2').value;
	
	if(pass == pass2) {
		span.innerHTML = '패스워드 일치';
		span.style.color = 'GREEN';
		checkPassResult = true;
	} else {
		span.innerHTML = '패스워드 불일치';
		span.style.color = 'RED';
		checkPassResult = false;
	}
	
}

function replacePhone() {
	var phone = document.getElementById('phone').value
	var phoneElem = document.getElementById('phone')
	phoneElem.value = phone.replaceAll('-', "")
}
</script>

</head>
<body>
<form action="MemberInsertPro.me" method="get">
<h1>회원가입</h1>
아이디	: <input type="text" name="id"><br>
비밀번호	: <input type="password" id="pass" name="pass" onkeyup="checkPass(this.value)"><br>
<span id="checkPassResult"><!-- 패스워드 규칙 판별 결과 표시 영역 --></span><br>
비밀번호 확인: <input type="password" id="pass2" name="pass2" onkeyup="checkRetypePass()"><br>
<span id="checkRetypePassResult"><!-- 패스워드 규칙 판별 결과 표시 영역 --></span><br>
이름		: <input type="text" name="name"><br>
<input type="submit" value="회원가입">

전화번호: <input type="text" name="phone" id="phone" onkeyup="replacePhone()" onkeydown="replacePhone()"><br>

</form>
</body>
</html>