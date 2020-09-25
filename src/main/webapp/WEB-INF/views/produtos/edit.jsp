<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
</br></br>
<div style="background-color: white;">
    </br></br>
    <form action="/produtos/edit/${prod.id}" method="POST">
        <input type="hidden" name="_method" value="PUT"/>
        <div class="container">
            <div class="rounded float-left" style="width: 275px;">
                <div>
                    <img src="/${prod.img1}" class="ui-item__image" width="224" height="224"/>
                </div>
                </br>
                <div>
                    <img src="/${prod.img2}" class="ui-item__image" width="100" height="100"/>
                    <img src="/${prod.img3}" class="ui-item__image" width="100" height="100"/>
                </div>
            </div>
            <input type="hidden" name="img1" value="${prod.img1}"/>
            <input type="hidden" name="img2" value="${prod.img2}"/>
            <input type="hidden" name="img3" value="${prod.img3}"/>
            <div class="form-group">
                <label>Nome: </label>
                <input class="form-control" style="width: 70%;" type="text" name="nome" value="${prod.nome}"/>
            </div>
            <div class="form-group">
                <label>Valor: </label>
                <input type="text" class="form-control" name="valor" style="width: 70%;"
                       value="${prod.valor}"" />"/>
            </div>
            <div class="form-group">
                <label>Quantidade: </label>
                <input class="form-control" style="width: 70%;" type="text" name="quantidade"
                       value="${prod.quantidade}"/>
            </div>

            <div class="form-group">
                <label>Sexo: </label>
                <input class="form-control" style="width: 70%;" type="text" name="sexo"
                       value="${prod.sexo}"/>
            </div>
            </br>
            <div class="form-group">
                <label>Modelo: </label>
                <input class="form-control" style="width: 95%;" type="text" name="modelo"
                       value="${prod.modelo}"/>
            </div>
            <div class="form-group">
                <label>Descrição: </label>
                <textarea class="form-control" name="descricao" style="width: 95%;">${prod.descricao}</textarea>
            </div>
            <div class="form-group">
                <label>Altura: </label>
                <input class="form-control" style="width: 95%;" type="text" name="altura"
                       value="${prod.altura}"/>
            </div>

            <div class="form-group">
                <label>Busto: </label>
                <input class="form-control" style="width: 95%;" type="text" name="busto"
                       value="${prod.busto}"/>
            </div>

            <div class="form-group">
                <label>Cintura: </label>
                <input class="form-control" style="width: 95%;" type="text" name="cintura"
                       value="${prod.cintura}"/>
            </div>

            <div class="form-group">
                <label>Quadril: </label>
                <input class="form-control" style="width: 95%;" type="text" name="quadril"
                       value="${prod.quadril}"/>
            </div>

            <div class="form-group">
                <label>Tamanho: </label>
                <input class="form-control" style="width: 95%;" type="text" name="tamanho"
                       value="${prod.tamanho}"/>
            </div>

            <div class="form-group">
                <label>Categoria: </label>
                <input class="form-control" style="width: 95%;" type="text" name="categoria"
                       value="${prod.categoria}"/>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
    </br></br>
    <div class="card" style="width: 95%;">
        <h5 class="card-header">Perguntas e Respostas</h5>
        <div class="card-body">
            <h5 class="card-title">De onde vem os produtos?</h5>
            <p class="card-text">Os produtos são entregues por nosso centro de distribuição localizado
                em São Paulo</p>
            <hr>
            </hr>
        </div>
        <div class="card-body">
            <h5 class="card-title">Posso retirar as peças ou entregam em alguma estação?</h5>
            <p class="card-text">Não, nós somos um e-commerce e não trabalhamos como loja fisica.</p>
            <hr>
            </hr>
        </div>
        <div class="card-body">
            <h5 class="card-title">Como posso realizar a troca do produto ?</h5>
            <p class="card-text">Peças intimas, não realizamos trocas.
                Para as demais peças o cliente tem até 7 dias úteis para
                comunicar sua troca na central de atendimento após o
                recebimento do produto</p>
            <hr>
            </hr>
        </div>
    </div>
</div>
</br></br>
</div>
<script src="../../../resources/jquery/jquery-3.5.1.min.js"></script>
</body>
</html>