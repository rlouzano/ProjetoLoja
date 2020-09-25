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
            </br>
            <div class="container" style="background-color: white;">
                <div>
                    </br></br>
                    <form action="/produtos/create" method="POST"
                          enctype="multipart/form-data">
                        <div class="alert alert-success" role="alert">
                            <h4 class="alert-heading">CADASTRO DE PRODUTO</h4>
                            <p>TODOS OS CAMPOS SÃO OBRIGATÓRIO PARA INCLUIR UM PRODUTO</p>
                        </div>
                        <div>
                            <div class="form-group">
                                <label>Imagem 1</label>
                                <input type="file" class="form-control-file" name="sumario1">
                            </div>
                            <div class="form-group">
                                <label>Imagem 2</label>
                                <input type="file" class="form-control-file" name="sumario2">
                            </div>
                            <div class="form-group">
                                <label>Imagem 3</label>
                                <input type="file" class="form-control-file" name="sumario3">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Nome: </label>
                            <input class="form-control" type="text" placeholder="Digite o nome do produto" name="nome"
                                   required/>
                        </div>
                        <div class="form-group">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="sexo" value="Masculino">
                                <label class="form-check-label">Masculino</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="sexo" value="Feminino">
                                <label class="form-check-label">Feminino</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Código: </label>
                            <input class="form-control" placeholder="00000000000" type="text" name="codigo" required/>
                        </div>
                        <div class="form-group">
                            <label>Descrição: </label>
                            <textarea class="form-control" type="text" name="descricao"
                                      placeholder="Digite a descrição do produto" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Valor: </label>
                            <input type="text" class="form-control" name="valor" placeholder="00.00" required/>
                        </div>
                        <div class="form-group">
                            <label>Quantidade: </label>
                            <input class="form-control" type="text" placeholder="00" required name="quantidade"/>
                        </div>
                        <div class="form-group">
                            <label>Modelo: </label>
                            <input class="form-control" type="text" name="modelo"
                                   placeholder="Digite o modelo do produto"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Altura: </label>
                            <input class="form-control" type="text" name="altura"
                                   placeholder="Digite a altura do produto"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Busto: </label>
                            <input class="form-control" type="text" placeholder="Digite o tamanho do busto" name="busto"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Cintura: </label>
                            <input class="form-control" type="text" placeholder="Digite o tamanho da cintura"
                                   name="cintura"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Quadril: </label>
                            <input class="form-control" type="text" placeholder="digite o tamanho do quadril"
                                   name="quadril"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Tamanho: </label>
                            <input class="form-control" type="text" placeholder="Digite o tamanho do produto"
                                   name="tamanho"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Categoria</label>
                            <select name="categoria" class="form-control" required>
                                <option>Calça</option>
                                <option>Vestido</option>
                                <option>Shorts</option>
                                <option>Blusa</option>
                                <option>Mascara</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Perguntas: Este produto pode ir na maquina de lavar ?"</label>
                            <input class="form-control" type="text" placeholder="Responde a pergunta" name="pergunta1"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Perguntas: Este produto pode ir na maquina secar ?</label>
                            <input class="form-control" type="text" placeholder="Responde a pergunta" name="pergunta2"
                                   required/>
                        </div>
                        <div class="form-group">
                            <label>Perguntas: Pode usar amaciante para lavar este produto ?</label>
                            <input class="form-control" type="text" placeholder="Responde a pergunta" name="pergunta3"
                                   required/>
                        </div>
                        <button style="
            width: 1000px;
            width: 1000px;
            height: 39px;
            padding-left: 15px;
            padding-right: 15px;
            margin-left: 60px;" type="submit" class="btn btn-primary">
                            Save
                        </button>
                        </br></br>
                    </form>
                </div>
            </div>
            <canvas class="my-4 w-100 chartjs-render-monitor" id="myChart" width="1536" height="648"
                    style="display: block; height: 692px; width: 1639px;"></canvas>
        </main>
        <script src="../../resources/jquery/jquery-3.5.1.min.js"></script>
</body>

</html>