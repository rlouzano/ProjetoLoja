package com.example.demo.service.service_implements;

import com.example.demo.model.*;
import com.example.demo.respository.CartaoRepository;
import com.example.demo.respository.PedidoRepository;
import com.example.demo.respository.VendaRepository;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.service.CartaoService;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    @Autowired
    private PedidoRepository repository;


    @Override
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(User usuario) {

    }

    @Override
    public Pedido buscaUm(Long id) {
        return this.repository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Pedido findById(Long id) {
        return this.repository.getOne(id);
    }
}
