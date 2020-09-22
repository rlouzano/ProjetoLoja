<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<header>
    <nav class="navbar navbar-light bg-light">
        <form action="/produtos/estoque" method="GET" class="form-inline">
            <div>
                <input class="form-control mr-sm-2" type="search" name="nomeproduto" var="${param.pesquisa}"
                       placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </div>
        </form>
    </nav>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Loja Jokers</a>
        <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse"
                data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="#" style="color: aliceblue;">Sign out</a>
            </li>
        </ul>
    </nav>
</header>
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="sidebar-sticky pt-3">
                <ul class="nav flex-column">
                    <div style="background-color: #a38c6e;">
                        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
                            <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3">Deshboard</a>
                        </nav>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link active" href="/principal/menu">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-home">
                                    <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                                    <polyline points="9 22 9 12 15 12 15 22"></polyline>
                                </svg>
                                Menu <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link" href="/produtos/listar">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-file">
                                    <path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
                                    <polyline points="13 2 13 9 20 9"></polyline>
                                </svg>
                                Produtos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link" href="#">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-shopping-cart">
                                    <circle cx="9" cy="21" r="1"></circle>
                                    <circle cx="20" cy="21" r="1"></circle>
                                    <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6">
                                    </path>
                                </svg>
                                Carrinho
                            </a>
                        </li>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link" href="#">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-users">
                                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="9" cy="7" r="4"></circle>
                                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                                    <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                                </svg>
                                Login
                            </a>
                        </li>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link" href="#">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-bar-chart-2">
                                    <line x1="18" y1="20" x2="18" y2="10"></line>
                                    <line x1="12" y1="20" x2="12" y2="4"></line>
                                    <line x1="6" y1="20" x2="6" y2="14"></line>
                                </svg>
                                Relatórios
                            </a>
                        </li>
                        <li class="nav-item">
                            <a style="color: aliceblue;" class="nav-link" href="/produtos/estoque?nomeproduto=">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-layers">
                                    <polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
                                    <polyline points="2 17 12 22 22 17"></polyline>
                                    <polyline points="2 12 12 17 22 12"></polyline>
                                </svg>
                                Estoque
                            </a>
                        </li>
                    </div>
                    <hr>
                    <div style="text-align: center; background-color: #a38c6e">
                        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
                            <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3">Categorias</a>
                        </nav>
                        <hr>
                        <li>
                            <div>
                                <form action="/produtos/genero" method="GET">
                                    <input type="hidden" name="sexo" value="Masculino"/>
                                    <button type="submit" style="width: 97px"
                                            class="btn btn-primary">Masculino
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/genero" method="GET">
                                    <input type="hidden" name="sexo" value="Feminino"/>
                                    <button type="submit" style="width: 97px"
                                            class="btn btn-danger">Feminino
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/categoria" method="GET">
                                    <input type="hidden" name="categoria" value="Vestido"/>
                                    <button type="submit" style="width: 97px" class="btn btn-secondary">Vestidos
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/categoria" method="GET">
                                    <input type="hidden" name="categoria" value="Shorts"/>
                                    <button type="submit" style="width: 97px"
                                            class="btn btn-success">Shorts
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/categoria" method="GET">
                                    <input type="hidden" name="categoria" value="Blusa"/>
                                    <button type="submit" style="width: 97px"
                                            class="btn btn-warning">Blusas
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/categoria" method="GET">
                                    <input type="hidden" name="categoria" value="Máscara"/>
                                    <button type="submit" style="width: 97px" class="btn btn-info">Máscara</button>
                                </form>
                            </div>
                            <hr>
                        </li>
                    </div>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4" style="background-color: #a38c6e;">
            </br></br>
            <div class="container">
                </br>
                <div class="card">
                    <div>
                        <form action="/produtos/confirmar" method="get">
                            <div>
                                <div class="card" style="width: 18rem;">
                                    <div class="list-group list-group-flush">
                                        <div class="list-group-item">
                                            <img src="/${prod.img1}" class="card-img-top" alt="...">
                                        </div>
                                        <div class="" class="list-group-item">
                                            <img style="width: 90px; margin-left: 25px;" src="/${prod.img2}"
                                                 class="card-img-top" alt="...">
                                            <img style="width: 90px; margin-left: 54px;" src="/${prod.img3}"
                                                 class="card-img-top" alt="..."/>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div style="width: 683px; margin-left: 366px; margin-top: -352px;">
                                        <div class="form-group">
                                            <label for="nome">Nome</label>
                                            <p class="form-control" name="nome" id="nome">${prod.nome}</p>
                                        </div>
                                        <div class="form-group" style="">
                                            <label for="modelo">Modelo</label>
                                            <p class="form-control" id="modelo" name="modelo">${prod.modelo}</p>
                                        </div>
                                        <div class="form-group">
                                            <label for="descricao">Descrição</label>
                                            <textarea class="form-control" id="descricao"
                                                      name="descricao">${prod.descricao}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="descricao">Código</label>
                                            <p class="form-control" id="descricao" name="codigo"></p>
                                        </div>
                                    </div>


                                    <div style="width: 1041px; margin-left: 10px; margin-top: 52px;">
                                        <div class="form-row">
                                            <div class="form-group col-md-3">
                                                <label for="genero">Genero</label>
                                                <p class="form-control" name="genero" id="genero">${prod.sexo}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="altura">Altura</label>
                                                <p name="altura" class="form-control" id="altura">${prod.altura}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="busto">Busto</label>
                                                <p name="busto" class="form-control" id="busto">${prod.busto}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="cintura">Cintura</label>
                                                <p name="cintura" class="form-control" id="cintura">${prod.cintura}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="quadril">Quadril</label>
                                                <p name="quadril" class="form-control" id="quadril">${prod.quadril}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="tamanho">Tamanho</label>
                                                <p name="tamanho" class="form-control" id="tamanho">${prod.tamanho}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="categoria">Categoria</label>
                                                <p name="categoria" class="form-control"
                                                   id="categoria">${prod.categoria}</p>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <button type="button" style="margin-left: 45px; margin-top: 28px;"
                                                        class="btn btn-primary btn-sm"><a style="width: 70px;"
                                                                                          class="btn btn-primary"
                                                                                          href="/produtos/listar">Editar</a>
                                                </button>
                                                <button type="button" style=" margin-top: 28px;"
                                                        class="btn btn-secondary btn-sm"><a style="width: 70px;"
                                                                                            class="btn btn-secondary"
                                                                                            href="/produtos/listar">Menu</a>
                                                </button>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
            <canvas class="my-4 w-100 chartjs-render-monitor" id="myChart" width="1536" height="648"
                    style="display: block; height: 692px; width: 1639px;"></canvas>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.5/assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="/docs/4.5/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<script src="dashboard.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>

</html>