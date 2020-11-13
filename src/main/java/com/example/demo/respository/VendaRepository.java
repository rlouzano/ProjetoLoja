package com.example.demo.respository;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    public List<Venda> findByClienteId(Long id);
}
