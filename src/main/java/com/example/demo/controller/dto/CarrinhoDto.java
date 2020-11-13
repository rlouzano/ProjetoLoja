package com.example.demo.controller.dto;

import com.example.demo.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDto {

    private List<Produto> produtos = new ArrayList<>();

   /* public boolean adiciona(Produto produto) {
        List<Produto> prod = produtos;
        prod.add(produto);
        return true;
    }*/

    public List<Produto> listar() {
        return this.produtos;
    }

    public void remover(Integer id) {
        for (int i = 0; i < this.produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtos.remove(i);
            }

        }
    }


}
