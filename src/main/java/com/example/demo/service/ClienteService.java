package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;

import java.util.List;

public interface ClienteService {

    public Cliente create(Cliente cliente, Endereco endereco, User user);

    public Cliente cadastro(Cliente cliente, User users);

    public Cliente listaPorUm(Long id);

    public boolean update(Long id, Cliente cli);

    public List<Cliente> find();

}
