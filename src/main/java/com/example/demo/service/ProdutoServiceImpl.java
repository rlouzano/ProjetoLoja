package com.example.demo.service;

import com.example.demo.infra.FileSever;
import com.example.demo.model.Produto;
import com.example.demo.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FileSever fileSever;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.produtoRepository = produtoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean adicionar(Produto produto) {
        produtoRepository.save(produto);
        return true;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

    @Override
    public Produto listaPorUm(Integer id) {
        Produto produto = produtoRepository.getOne(id);
        return produto;
    }

    @Override
    public boolean excluir(Integer id) {
        produtoRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Integer id, Produto produto) {
        Produto prod = findById(id);
        if (!prod.equals(null)) {
            prod.setId(produto.getId());
            prod.setImg1(prod.getImg1());
            prod.setImg2(prod.getImg2());
            prod.setImg3(prod.getImg3());
            prod.setNome(produto.getNome());
            prod.setQuantidade(produto.getQuantidade());
            prod.setValor(produto.getValor());
            prod.setModelo(produto.getModelo());
            prod.setDescricao(produto.getDescricao());
            prod.setAltura(produto.getAltura());
            prod.setBusto(produto.getBusto());
            prod.setCintura(produto.getCintura());
            prod.setQuadril(produto.getQuadril());
            prod.setTamanho(produto.getTamanho());
            prod.setCor(produto.getCor());
            prod.setCategoria(produto.getCategoria());
            prod.setPergunta1(produto.getPergunta1());
            prod.setResposta1(produto.getResposta1());
            prod.setPergunta2(produto.getPergunta2());
            prod.setResposta2(produto.getResposta2());
            prod.setPergunta3(produto.getPergunta3());
            prod.setResposta3(produto.getResposta3());
            prod.setActive(produto.isActive());
            this.produtoRepository.save(prod);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateQuantidade(Integer id, Produto produto) {

        Produto prod = findById(id);
        if (!prod.equals(null)) {
            prod.setId(prod.getId());
            prod.setImg1(prod.getImg1());
            prod.setImg2(prod.getImg2());
            prod.setImg3(prod.getImg3());
            prod.setNome(prod.getNome());
            prod.setQuantidade(produto.getQuantidade());
            prod.setValor(prod.getValor());
            prod.setModelo(prod.getModelo());
            prod.setDescricao(prod.getDescricao());
            prod.setAltura(prod.getAltura());
            prod.setBusto(prod.getBusto());
            prod.setCintura(prod.getCintura());
            prod.setQuadril(prod.getQuadril());
            prod.setTamanho(prod.getTamanho());
            prod.setCor(prod.getCor());
            prod.setCategoria(prod.getCategoria());
            prod.setPergunta1(produto.getPergunta1());
            prod.setResposta1(produto.getResposta1());
            prod.setPergunta2(produto.getPergunta2());
            prod.setResposta2(produto.getResposta2());
            prod.setPergunta3(produto.getPergunta3());
            prod.setResposta3(produto.getResposta3());
            prod.setActive(prod.isActive());
            this.produtoRepository.save(prod);
            return true;
        }
        return false;
    }


    private Produto findById(Integer id) {
        return this.produtoRepository.getOne(id);
    }
}
