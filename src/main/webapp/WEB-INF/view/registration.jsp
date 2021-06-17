<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<html>
<head>
    <title>회원가입 화면</title>

    <!-- css 파일 분리 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">

        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
            if(!document.userInfo.name.value){
                alert("아이디를 입력하세요.");
                return false;
            }

            if(!document.userInfo.password.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }

            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(document.userInfo.password.value != document.userInfo.passwordcheck.value ){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
        }

        // 취소 버튼 클릭시 로그인 화면으로 이동
        function goLoginForm() {
            location.href="LoginForm.jsp";
        }
    </script>

</head>
<body>

<!-- div 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
<div id="wrap">
    <br><br>
    <b><font size="6" color="gray">회원가입</font></b>
    <br><br><br>


    <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
    <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
    <form method="get" action="/"  name="userInfo" id="userInfo">
        <table>
            <tr>
                <td id="title">아이디</td>
                <td>
                    <input type="text" name="name" id="name" maxlength="50">
                    <input type="button" value="중복확인" id="searchId">
                </td>
            </tr>

            <tr>
                <td id="title">비밀번호</td>
                <td>
                    <input type="password" name="password" maxlength="50">
                </td>
            </tr>

            <tr>
                <td id="title">비밀번호 확인</td>
                <td>
                    <input type="password" name="passwordcheck" maxlength="50">
                </td>
            </tr>

        </table>
        <br>
        <input type="button" value="가입" id="set" class="no" onclick="return checkValue()"/>
        <a href="/" type="button">취소</a>
    </form>
</div>
<script type="text/javascript">

    $('#searchId').click(function () {
        var url = '/findUser/' + document.getElementById("name").value
        $.ajax({
            type: "GET",                               //1
            url: url,                          //2
            dataType: 'json',                           //3
            contentType: 'application/json',            //4
            data: "",                 //5
            complete: function (resp) {
                console.log(resp)
                if (resp.responseText == "ok") {
                    alert("아이디를 사용할수 있습니다.")
                    document.getElementById("set").className="ok"
                    return false;
                } else {
                    alert("아이디를 사용할수 없습니다.")
                    return false;
                }

            },                       //6             //7
        });
    });

    $('#set').click(function (){
        if(document.getElementById("set").className=="ok"){
            var user={
                "name":document.userInfo.name.value,
                "password":document.userInfo.password.value,
                "admin":"NoN"
            }
            $.ajax({
                type : "POST",                               //1
                url : "/registration",                          //2
                dataType : 'json',                           //3
                contentType : 'application/json',            //4
                data :JSON.stringify(user),                 //5
                complete : function (resp){
                    console.log(resp)
                    if(resp.responseText=="ok"){
                        alert("가입에 성공하였습니다.")
                        document.getElementById("userInfo").submit();
                        return false;
                    }
                    else{
                        alert("가입에 실패하였습니다.")
                        return false;
                    }

                },                       //6             //7
            });
        }

    });

</script>
</body>
</html>
