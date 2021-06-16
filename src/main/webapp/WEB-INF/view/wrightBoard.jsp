<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 - 글쓰기</title>
</head>
<body>
<div class="container">
    <form action="/uploadBoard" method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <label for="inputTitle" class="col-sm-2 col-form-label"><strong>제목</strong></label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-control" id="inputTitle">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
            <div class="col-sm-10">
                <textarea type="text" name="content" class="form-control" id="inputContent"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputFile" class="col-sm-2 col-form-label"><strong>첨부 파일</strong></label>
            <div class="col-sm-10">
                <div class="custom-file" id="inputFile">
                    <input name="img" type="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">파일을 선택해 주세요.</label>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputFile" class="col-sm-2 col-form-label"><strong>첨부 파일</strong></label>
            <div class="col-sm-10">
                <div class="custom-file" id="inputFile">
                    <input name="video" type="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">파일을 선택해 주세요.</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-auto mr-auto"></div>
            <div class="col-auto">
                <input class="btn btn-primary" type="submit" role="button" value="글쓰기">
            </div>
        </div>
    </form>
</div>
</body>
</html>