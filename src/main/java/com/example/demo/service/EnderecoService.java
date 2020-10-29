package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;

import java.util.List;

public interface EnderecoService {

    public Endereco create(Endereco endereco, Cliente cliente);

    public Endereco cadastro(Endereco endereco, Cliente clientes, User user);

    public Endereco listaPorUm(Long id);

    public boolean update(Long id, Endereco endereco);

    public void delete(Long id);

}
