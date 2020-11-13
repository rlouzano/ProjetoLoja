package com.example.demo.respository;

import com.example.demo.model.Cartao;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {


    public Cartao findByClienteId(Long id);

    @Query("select c from Cartao c where c.id = :id")
    public List<Cartao> listaPorId(Long id);

}
