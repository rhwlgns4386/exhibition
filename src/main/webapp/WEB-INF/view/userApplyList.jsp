<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <meta charset="UTF-8">
</head>
<body>

<div class="container mb-3">

    <h2 class="mb-3">Board</h2>

    <table class="board-table table table-sm border-bottom">

        <thead class="thead-light">
        <tr>
            <th scope="col">제목</th>
            <th scope="col" class="date">작성자</th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td>
                <c:forEach items="${boardList}" var="node">
                    <a href="/selectApply/${node.id}"><div class="ellipsis">${node.title}</div></a>
                </c:forEach>
            </td>
            <td class="date">
                <c:forEach items="${boardList}" var="node">
                    <div data-date="">${node.author.name}</div>
                </c:forEach>
                 <!-- 1 -->
            </td>
        </tr>
        </tbody>

    </table>
</div>
</body>
</html>