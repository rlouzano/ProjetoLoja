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
                            <a style="color: aliceblue;" class="nav-link" href="/produtos/listar">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-users">
                                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="9" cy="7" r="4"></circle>
                                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                                    <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                                </svg>
                                Administrador
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
                                    <button type="submit" style="width: 97px" class="btn btn-primary">Masculino
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/genero" method="GET">
                                    <input type="hidden" name="sexo" value="Feminino"/>
                                    <button type="submit" style="width: 97px" class="btn btn-danger">Feminino
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
                                    <button type="submit" style="width: 97px" class="btn btn-success">Shorts
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div>
                                <form action="/produtos/categoria" method="GET">
                                    <input type="hidden" name="categoria" value="Blusa"/>
                                    <button type="submit" style="width: 97px" class="btn btn-warning">Blusas
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
            <div class="container" style="background-color: white;">
                <div>
                    </br></br>
                    <form enctype="multipart/form-data">
                        <div class="alert alert-success" role="alert">
                            <h4 class="alert-heading">DETALHES DO PRODUTO</h4>
                        </div>

                        <div class="jumbotron">
                            <div class="col-md-4 order-md-2 mb-4" style="margin-left: 583px; margin-top: 7px;">

                                <ul class="list-group mb-3" style=" width: 396px;">
                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                        <div>
                                            <h6 class="my-0">Nome do produto</h6>
                                            <small class="text-muted">${prod.nome}</small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                        <div>
                                            <h6 class="my-0">Quantidade</h6>
                                            <small class="text-muted">${prod.quantidade}</small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                        <div>
                                            <h6 class="my-0">Genero</h6>
                                            <small class="text-muted">${prod.sexo}</small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                        <div class="text-success">
                                            <h6 class="my-0">Código Produto</h6>
                                            <small>${prod.codigo}</small>
                                        </div>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between">
                                        <span>Total (Real)</span>
                                        <strong>R$ ${prod.valor}</strong>
                                    </li>
                                </ul>
                            </div>
                            <div style="margin-top: -341px;margin-left: 149px;">
                                <div class="form-group">
                                    <img name="img1" style="width: 242px;" src="/${prod.img1}">
                                </div>
                                <div class="form-group">
                                    <img name="img2" style="width: 107px;margin-left: 272px; margin-top: -431px;"
                                         src="/${prod.img2}">
                                </div>
                                <div class="form-group">
                                    <img name="img3" style="width: 104px;margin-left: 275px; margin-top: -246px;"
                                         src="/${prod.img3}">
                                </div>
                            </div>


                            <div class="jumbotron">
                                <div class="col-md-4 order-md-2 mb-4" style="margin-left: 30px; margin-top: -51px;">
                                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                                        <span class="text-muted">Detalhe do produto</span>
                                    </h4>
                                    <ul class="list-group mb-3" style=" width: 915px;">
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Descrição</h6>
                                                <small class="text-muted">${prod.descricao}</small>
                                            </div>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Modelo</h6>
                                                <small class="text-muted">${prod.modelo}</small>
                                            </div>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Altura</h6>
                                                <small class="text-muted">${prod.altura}</small>
                                            </div>
                                        </li>

                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Busto</h6>
                                                <small class="text-muted">${prod.busto}</small>
                                            </div>
                                        </li>

                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Cintura</h6>
                                                <small class="text-muted">${prod.cintura}</small>
                                            </div>
                                        </li>

                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Quadril</h6>
                                                <small class="text-muted">${prod.quadril}</small>
                                            </div>
                                        </li>

                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Tamanho</h6>
                                                <small class="text-muted">${prod.tamanho}</small>
                                            </div>
                                        </li>

                                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                                            <div>
                                                <h6 class="my-0">Categoria</h6>
                                                <small class="text-muted">${prod.categoria}</small>
                                            </div>
                                        </li>
                                    </ul>


                                    <h5>Perguntas e respostas</h5>


                                    <div class="jumbotron">
                                        <div class="col-md-4 order-md-2 mb-4" style="margin-left: -109px; margin-top: -51px;">
                                            <ul class="list-group mb-3" style=" width: 1030px;">
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div>
                                                        <h6 class="my-0">Este produto pode ir na maquina de lavar ?</h6>
                                                        <small class="text-muted">${prod.pergunta1}</small>
                                                    </div>
                                                </li>
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div>
                                                        <h6 class="my-0">Este produto pode ir na maquina secar ?</h6>
                                                        <small class="text-muted">${prod.pergunta2}</small>
                                                    </div>
                                                </li>
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div>
                                                        <h6 class="my-0">Pode usar amaciante para lavar este produto ?</h6>
                                                        <small class="text-muted">${prod.pergunta3}</small>
                                                    </div>
                                                </li>
                                            </ul>
                                </div>
                                <canvas class="my-4 w-100 chartjs-render-monitor" id="myChart" width="1536" height="648"
                                        style="display: block; height: 692px; width: 1639px;"></canvas>


                            </div>


                    </form>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="../../resources/jquery/jquery-3.5.1.min.js"></script>
</body>
</html>