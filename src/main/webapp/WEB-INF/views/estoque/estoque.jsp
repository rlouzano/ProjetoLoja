<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.min.css"/>
</head>
<div>
    </br></br>

    <table class="table" style="text-align: center;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#ID</th>
            <th scope="col">IMG</th>
            <th scope="col">MODELO</th>
            <th scope="col">NOME</th>
            <th scope="col">QUANTIDADE</th>
            <th scope="col">SEXO</th>
            <th scope="col">TAMANHO</th>
            <th scope="col">VALOR</th>
        </tr>
        </thead>
        <tbody style="background-color: white;">
        <c:forEach items="${produtos}" var="produto">
            <tr>
                <td style="width: 100px;">${produto.id}</td>
                <td style="width: 100px;"><img style="margin: 25px auto 30px; width: 45%; margin-top: -9px;"
                                               src="/${produto.img1}"></td>
                <td style="width: 100px;">${produto.modelo}</td>
                <td style="width: 100px;">${produto.nome}</td>
                <td style="width: 50px;">
                    <ul>
                        <form method="post" action="/produtos/alterar/${produto.id}">
                            <input type="hidden" name="_method" value="put"/>
                            <div>
                                <div class="" container>
                                    <input style="width: 47px;" class="form-control" type="text" name="quantidade" value="${produto.quantidade}"/>
                                    <button style="margin-top: -65px;margin-left: 37px;" class="btn btn-outline-primary" type="submit">OK</button>
                                </div>
                            </div>
                        </form>
                    </ul>
                </td>
                <td style="width: 100px;">${produto.sexo}</td>
                <td style="width: 100px;">${produto.tamanho}</td>
                <td style="width: 100px;">
                    <fmt:formatNumber value="${produto.valor}" type="currency"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
<script src="../../../resources/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    function confirmaExclusao(id) {
        if (window.confirm('Tem certeza que deseja excluir')) {
            location.href = "/produtos/excluir/" + id
        }
    }</script>
</body>
</html>