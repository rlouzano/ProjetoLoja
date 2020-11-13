package com.example.demo.service.service_implements;

import com.example.demo.model.*;
import com.example.demo.respository.CartaoRepository;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.CartaoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartaoServiceImpl implements CartaoService {


    @Autowired
    private CartaoRepository repository;


    @Override
    public List<Cartao> findAll() {
        return repository.findAll();
    }

    @Override
    public Cartao create(Cartao cartao, Cliente cliente) {
        cartao.setCliente(cliente);
        return repository.save(cartao);
    }

    @Override
    public Cartao buscaUm(Long id){
        return this.repository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Cartao findById(Long id) {
        return this.repository.getOne(id);
    }
}
