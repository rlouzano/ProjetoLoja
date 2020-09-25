<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
</head>
<body>
</br></br>
<div style="background-color: white;">
    </br></br>
    <div class="container">
        <div class="container" style="background-color: white;">
            <c:forEach items="${lista}" var="produto">
                <div>
                    <div class="rounded float-left" style="width: 255px; text-align: center;">
                        <div>
                            <img src="/${produto.img1}" class="ui-item__image" width="224" height="224">
                        </div>
                        <div style="text-align: center;">
                            <a href="/produtos/edit/${produto.id}">${produto.nome}</a>
                            <p style="color: #007bff" ;>
                                    <fmt:formatNumber value="${produto.valor}" type="currency"/></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <script src="../../resources/jquery/jquery-3.5.1.min.js"></script>
    </div>
</div>
</body>
</html>