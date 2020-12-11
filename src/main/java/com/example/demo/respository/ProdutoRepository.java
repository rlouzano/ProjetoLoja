package com.example.demo.respository;

import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findBySexo(String nomeSexo);

    public Produto findByCodigo(Integer codigo);

    public Produto findAllByCodigo(Integer codigo);



}
