package com.example.demo.service;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Produto;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrinhoService {

    public Carrinho adiciona(Produto produto, double frete, User user);

    public boolean update(Long id, Carrinho carrinho);

    public List<Carrinho> findAll();

    public Carrinho ListaUmPorId(Long id);

    public void delete(Long id);
}
