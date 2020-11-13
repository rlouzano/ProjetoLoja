package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface ProdutoService {

    public boolean adicionar(Produto produto) ;

    public List<Produto> listarTodos() ;

    public Produto listaPorUm(Integer id);

    public List<Produto> listarTodosId(Integer id);

    public boolean excluir(Integer id);

    public boolean update(Integer id, Produto produto);

    public boolean updateQuantidade(Integer id, Produto produto);

}
