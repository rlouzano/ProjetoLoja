package com.example.demo.service;


import com.example.demo.model.*;

import java.util.List;

public interface PedidoService {

    public List<Pedido> findAll();

    public void create(User usuario);

    public Pedido buscaUm(Long id);

    public void delete(Long id);

}
