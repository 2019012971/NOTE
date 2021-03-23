<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author yeeku.H.lee kongyeeku@163.com 公众号: fkbooks
version 1.0
Copyright (C), 2001-2020, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date:
--%>

<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script>
        $(document).ready(function () {
            bsCustomFileInput.init()
        });
    </script>
    <title> 添加图书 </title>
</head>
<body>
<div class="container">
    <h3 class="text-info">添加图书</h3>
    <c:if test="${not empty tip}">
        <div class="toast" role="alert" aria-live="assertive"
             aria-atomic="true" data-delay="12000">
            <div class="toast-header">
                <strong class="mr-auto">提示</strong>
                <button type="button" class="ml-2 mb-1 close"
                        data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                    ${tip}
            </div>
        </div>
    </c:if>
    <form:form method="post" action="addBook" modelAttribute="book" enctype="multipart/form-data">
        <div class="form-group row">
            <form:label path="title" class="col-sm-2 col-form-label">图书名：</form:label>
            <div class="col-sm-7">
                <form:input type="text" id="title" path="title"
                            class="form-control" placeholder="请输入图书名"/>
            </div>
            <div class="col-sm-3 text-danger">
                <form:errors path="title"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="author" class="col-sm-2 col-form-label">作者：</form:label>
            <div class="col-sm-7">
                <form:input type="text" id="author" path="author"
                            class="form-control" placeholder="请输入作者名"/>
            </div>
            <div class="col-sm-3 text-danger">
                <form:errors path="author"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="price" class="col-sm-2 col-form-label">价格：</form:label>
            <div class="col-sm-7">
                <form:input type="number" id="price" path="price"
                            class="form-control" placeholder="请输入价格" />
            </div>
            <div class="col-sm-3 text-danger">
                <form:errors path="price"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="cover" class="col-sm-2 col-form-label">封面：</form:label>
            <div class="col-sm-7">
                <div class="custom-file">
<%--                    <form:input type="file" id="file" path="file"--%>
<%--                                class="custom-file-input"/>--%>
                    <input type="file" name="file" id="file" class="custom-file-input">
                    <label class="custom-file-label" for="file">选择文件</label>
                </div>
            </div>
            <div class="col-sm-3 text-danger">
                <form:errors path="cover"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm text-center">
                <form:button type="submit" class="btn btn-success">添加</form:button>
                <form:button type="reset" class="btn btn-danger">重设</form:button>
                <a href="${pageContext.request.contextPath}/books"
                   class="btn btn-primary">返回</a>
            </div>
        </div>
    </form:form>
</div>
<script type="text/javascript">
    $('.toast').toast("show");
</script>
</body>
</html>
