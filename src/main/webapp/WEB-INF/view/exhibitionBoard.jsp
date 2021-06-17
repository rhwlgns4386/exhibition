<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Insert title here</title>
</head>
<body>
<form >
    <table border="1" summary="게시판 수정폼">
        <caption><b>게시판 수정폼</b></caption>
        <colgroup>
            <col width=100/>
            <col width=500/>
        </colgroup>

        <tbody>
        <tr>
            <th>제목</th>
            <td>
                ${board.title}
            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${board.author.name}</td>
        </tr>
        <tr>
            <td colspan="2">
                <img src="${imgUrl}" width="300px" height="300px">
                <video id="myVideo" src= "${videoUrl}" width="300px" height="300px"  controls autoplay></video>

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea rows=15 cols=85 name="contents" id="contents">${board.content}</textarea>
            </td>
        </tr>
        </tbody>
    </table>
    <p>
        <a href="/" type="button">취소</a>

    </p>
</form>
<script>


    $("#playBtn").on("click", function() {

        $("#myVideo").trigger("play");

    });


    $("#fullBtn").on("click", function() {

        var elem = document.getElementById("myVideo");

        if(elem.requestFullscreen) {

            elem.requestFullscreen();

        } else if(elem.mozRequestFullScreen) {

            elem.mozRequestFullScreen();

        } else if (elem.webkitRequestFullscreen) {

            elem.webkitRequestFullscreen();

        } else if (elem.msRequestFullscreen) {

            elem.msRequestFullscreen();

        }

    });

    $(document).ready(function() {

        $("#myVideo").on("ended", function() {

            console.log("Video Finished");

        });

    });

</script>
</body>
</html>