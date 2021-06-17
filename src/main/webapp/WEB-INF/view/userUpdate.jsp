<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>회원가입</title>
</head>
<script type="text/javascript">
    $(document).ready(function(){
        // 취소
        $(".cencle").on("click", function(){

            location.href = "/";

        })

        $("#submit").on("click", function(){
            if($("#userPass").val()==""){
                alert("비밀번호를 입력해주세요.");
                $("#userPass").focus();
                return false;
            }
            if($("#userName").val()==""){
                alert("성명을 입력해주세요.");
                $("#userName").focus();
                return false;
            }
        });



    })
</script>
<body>

<section id="container">
    <form action="/" method="get" name="updateForm" id="updateForm">
        <input type="hidden" name="_method" value="PUT"/>
        <div class="form-group has-feedback">
            <label class="control-label" for="name">아이디</label>
            <input class="form-control" type="text" id="name" name="name" value="<%=session.getAttribute("userId").toString() %>" readonly="readonly"/>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="password">패스워드</label>
            <input class="form-control" type="password" id="password" name="password" />
        </div>
        <div class="form-group has-feedback">
            <input class="btn btn-success" type="button" id="updateUser" value="회원정보수정"/>
        </div>
    </form>
    <form action="/" method="get" id="userDeleteForm">
        <div class="form-group has-feedback">
            <input class="btn btn-success" type="button" id="deleteUser" value="회원탈퇴"></input>
        </div>
    </form>
    <button class="cencle btn btn-danger" type="button">취소</button>
</section>
<script type="text/javascript">

    $('#updateUser').click(function (){
        var user={
            "name":document.updateForm.name.value,
            "password":document.updateForm.password.value
        }
        console.log(user);
        $.ajax({
            type : "PUT",                               //1
            url : "/userUpdate",                          //2
            dataType : 'type',                           //3
            contentType : 'application/json',
            data :JSON.stringify(user),                 //5
            complete  : function (resp){
                if(resp.responseText==="ok"){
                    alert("업데이트에 성공하였습니다..")
                    document.getElementById("updateForm").submit()
                    return false;
                }
                else{
                    alert("실패하였습니다.")
                }
            },
        });
    })
    $('#deleteUser').click(function (){
        $.ajax({
            type : "DELETE",                               //1
            url : "/userDelete/<%=session.getAttribute("userId").toString() %>",                          //2
            dataType : 'type',                           //3
            contentType : 'application/json',
            data :"",                 //5
            complete  : function (resp){
                if(resp.responseText==="ok"){
                    alert("삭제 성공하였습니다..")
                    document.getElementById("userDeleteForm").submit()
                    return false;
                }
            },
        });
    })

</script>
</body>

</html>