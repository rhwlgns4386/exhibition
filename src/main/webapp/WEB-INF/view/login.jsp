<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 예제</title>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">

        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue() {
            if (!document.userInfo.name.value) {
                alert("아이디를 입력하세요.");
                return false;
            }

            if (!document.userInfo.password.value) {
                alert("비밀번호를 입력하세요.");
                return false;
            }
        }
    </script>
</head>
<body>
<% if(session.getAttribute("userId")==null){ //세션이 설정되지 않을 경우 %>
<form method="get" action="/" name="userInfo" id="form" >
    <label for="id">아이디</label>
    <input  type="text" name="name" id="id" placeholder="아이디" required />
    <label for="pass">비밀번호</label>
    <input type="password" name="password" id="pass" placeholder="비밀번호" required />
    <input type="button" value="로그인" id='login' onclick="return checkValue()"/>
</form>
<script type="text/javascript">

    $('#login').click(function (){
        var user={
            "name":document.userInfo.name.value,
            "password":document.userInfo.password.value
        }
        $.ajax({
            type : "post",                               //1
            url : "/login",                          //2
            dataType : 'type',                           //3
            contentType : 'application/json',
            data :JSON.stringify(user),                 //5
            complete  : function (resp){
                if(resp.responseText==="idErr"){
                    alert("아이디를 찾을수 없습니다.")
                    return false;
                }
                if(resp.responseText==="pwErr"){
                    alert("비밀번호가 잘못되었습니다.")
                    return false;
                }
                if(resp.responseText==="ok"){

                    document.getElementById("form").submit()
                    return false;
                }
            },
        });
    })

</script>
<% } %>
</body>
</html>