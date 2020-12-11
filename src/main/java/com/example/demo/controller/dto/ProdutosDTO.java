package com.example.demo.controller.dto;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProdutosDTO {


    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produto.setQuantidade(1);
        this.produtos.add(produto);
    }

    public List<Produto> listarTodos() {
        return this.produtos;
    }

    public void remover(Integer id) {
        for (int i = 0; i < this.produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                this.produtos.remove(i);
            }
        }
    }

    private void alterarQuantidade(Produto produto, Integer id) {
        for (Produto prod : this.produtos) {
            if (prod.getId().equals(id)) {
                prod.setQuantidade(produto.getQuantidade());
            }
        }
    }


}
