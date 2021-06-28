<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<body>

<div class="collapse navbar-collapse" id="navbar-collapse2">
    <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="/exhibitionBoardList">Posts</a></li>
        <% if(session.getAttribute("admin")!=null){%>
        <li><a href="/admin/userApplyList" role="button">사용자 글 허가</a></li>
        <% }%>
        <% if(session.getAttribute("userId")==null){ //세션이 설정되지 않을 경우 %>
        <li><a href="/login" role="button">로그인</a></li>
        <li><a href="/registration" role="button">회원가입</a></li>
        <% }else{ %>
        <li><a href="/user/update" role="button">개인정보수정</a></li>
        <li><a href="/user/uploadBoard" role="button"><b>글적기</b></a></li>
        <li><a href="/logout" role="button">로그아웃</a></li>
        <% } %>




    </ul>
</div>
</body>
</html>