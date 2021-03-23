<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/22
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="author" content="Yeeku.H.Lee(CrazyIt.org)" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/bootstrap-4.3.1/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1/css/bootstrap.min.css">--%>
    <script src="${pageContext.request.contextPath}/res/jquery-3.4.1.min.js">
    </script>
    <script src="${pageContext.request.contextPath}/res/bootstrap-4.3.1/js/bootstrap.min.js">
    </script>
    <script src="${pageContext.request.contextPath}/res/bs-custom-file-input.js">
    </script>
    <style type="text/css">
        #cover {
            width: 50px;
            height: 50px;
        }
    </style>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">书名</th>
                <th scope="col">作者</th>
                <th scope="col">价格</th>
                <th scope="col">操作</th>
                <th scope="col">封面</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${books}">
                <tr>
                    <td>${item.title}</td>
                    <td>${item.author}</td>
                    <td>${item.price}</td>
                    <td><img src="${pageContext.request.contextPath}/uploads/${item.cover}" alt="没了呀" id="cover"></td>
                    <td><a href="${pageContext.request.contextPath}/updateForm?id=${item.id}"
                           class="badge badge-warning">修改</a>
                    <a href="${pageContext.request.contextPath}/deleteBook?id=${item.id}" onclick="return confirm('请您确认是否真的删除');"
                       class="badge badge-danger">删除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
