package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;
import com.example.demo.respository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente create(Cliente cliente, Endereco endereco, User user) {
        List<Endereco> end = new ArrayList<>();
        end.add(endereco);
        cliente.setEndereco(end);
        return this.repository.save(cliente);
    }

    @Override
    public List<Cliente> find(){
        return this.repository.findAll();
    }
    @Override
    public Cliente cadastro(Cliente cliente, User users) {
        return this.repository.save(cliente);
    }

    @Override
    public Cliente listaPorUm(Long id) {
        Cliente cliente = repository.getOne(id);
        return cliente;

    }

    @Override
    public boolean update(Long id, Cliente cli) {
        Cliente cliente = findById(id);
        if (!cliente.equals(null)) {
            cliente.setId(cli.getId());
            cliente.setNome(cli.getNome());
            cliente.setCpf(cli.getCpf());
            this.repository.save(cliente);
            return true;
        }
        return false;
    }

    private Cliente findById(Long id) {
        return this.repository.getOne(id);
    }

}
