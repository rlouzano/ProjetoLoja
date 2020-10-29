package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.User;
import com.example.demo.respository.ClienteRepository;
import com.example.demo.respository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Endereco create(Endereco endereco, Cliente cliente) {
        endereco.setCliente(cliente);
        return this.repository.save(endereco);
    }

    @Override
    public Endereco cadastro(Endereco endereco, Cliente clientes, User user) {
        //clientes.setUser(user);
        endereco.setCliente(clientes);
        this.clienteRepository.save(clientes);
        return this.repository.save(endereco);
    }

    @Override
    public Endereco listaPorUm(Long id) {
        Endereco endereco = repository.getOne(id);
        return endereco;
    }

    @Override
    public boolean update(Long id, Endereco endereco) {
        Endereco end = findById(id);
        if (!end.equals(null)) {
            end.setId(endereco.getId());
            end.setCep(endereco.getCep());
            end.setNumero(endereco.getNumero());
            end.setBairro(endereco.getBairro());
            end.setComplemento(endereco.getComplemento());
            end.setLogradouro(endereco.getLogradouro());
            end.setLocalidade(endereco.getLocalidade());
            end.setUf(endereco.getUf());
            this.repository.save(end);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Endereco findById(Long id) {
        return this.repository.getOne(id);
    }
}
