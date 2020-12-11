package com.example.demo.service.service_implements;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.model.Produto;
import com.example.demo.model.User;
import com.example.demo.respository.CarrinhoRepository;
import com.example.demo.respository.ProdutoRepository;
import com.example.demo.respository.querys.ProdutoCustomRepository;
import com.example.demo.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public boolean adiciona(Produto produto, double frete, User user) {
        Carrinho carrinho = new Carrinho();
        List<Carrinho> carrinhos = this.carrinhoRepository.findAll();
        try {
            if (!carrinhos.isEmpty()) {
                for (Carrinho c : carrinhos) {
                    if (c.getCodigo().equals(produto.getCodigo())) {
                        System.out.println(c.getCodigo());
                        c.setId(c.getId());
                        c.setImg1(c.getImg1());
                        c.setNome(c.getNome());
                        c.setQuantidade(c.getQuantidade() + 1);
                        c.setCodigo(c.getCodigo());
                        c.setFrete(c.getFrete());
                        c.setValor(c.getValor());
                        c.setTotal(c.getQuantidade() * c.getValor());
                        c.setCodigoProduto(new Random());
                        c.setUser(c.getUser());
                        carrinhoRepository.save(c);
                        return true;
                    }
                }
                for (Carrinho car : carrinhos) {
                    if (!car.getCodigo().equals(produto.getCodigo())) {
                        carrinho.setImg1(produto.getImg1());
                        carrinho.setNome(produto.getNome());
                        carrinho.setQuantidade(1);
                        carrinho.setCodigo(produto.getCodigo());
                        carrinho.setFrete(frete);
                        carrinho.setValor(produto.getValor());
                        carrinho.setTotal(produto.getQuantidade() * produto.getValor());
                        carrinho.setCodigoProduto(new Random());
                        carrinho.setUser(user);
                        carrinhoRepository.save(carrinho);
                        return true;
                    }
                }
            }
            if (carrinhos.isEmpty()) {
                carrinho.setImg1(produto.getImg1());
                carrinho.setNome(produto.getNome());
                carrinho.setQuantidade(1);
                carrinho.setCodigo(produto.getCodigo());
                carrinho.setFrete(frete);
                carrinho.setValor(produto.getValor());
                carrinho.setTotal(produto.getQuantidade() * produto.getValor());
                carrinho.setCodigoProduto(new Random());
                carrinho.setUser(user);
                carrinhoRepository.save(carrinho);
                return true;
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public boolean update(Long id, Carrinho carrinho) {
        Carrinho cr = findById(id);
        if (!cr.equals(null)) {
            cr.setId(cr.getId());
            cr.setImg1(cr.getImg1());
            cr.setNome(cr.getNome());
            cr.setCodigo(cr.getCodigo());
            cr.setCodigoProduto(cr.getCodigoProduto());
            cr.setImg1(cr.getImg1());
            cr.setQuantidade(carrinho.getQuantidade());
            cr.setValor(cr.getValor());
            cr.setTotal(cr.getQuantidade() * cr.getValor());
            cr.setData(cr.getData());
            this.carrinhoRepository.save(cr);
            return true;
        }
        return false;
    }

    private Carrinho findById(Long id) {
        return this.carrinhoRepository.getOne(id);
    }

    @Override
    public Carrinho ListaUmPorId(Long id) {
        return carrinhoRepository.getOne(id);
    }

    @Override
    public List<Carrinho> findAll() {
        return this.carrinhoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.carrinhoRepository.deleteById(id);
    }
}
