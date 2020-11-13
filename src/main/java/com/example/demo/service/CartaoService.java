package com.example.demo.service;


import com.example.demo.model.Cartao;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;

import java.util.List;

public interface CartaoService {

    public List<Cartao> findAll();

    public Cartao create(Cartao cartao, Cliente cliente);

    public Cartao buscaUm(Long id);

    public void delete(Long id);

}
