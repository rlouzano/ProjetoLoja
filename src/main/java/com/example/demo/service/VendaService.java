package com.example.demo.service;


import com.example.demo.model.*;

import java.util.List;

public interface VendaService {

    public void create(Venda venda, User usuario, Cartao cartao, Endereco enderecos, List<Carrinho> carrinhos);

    public void update(Long id, String status);

}
