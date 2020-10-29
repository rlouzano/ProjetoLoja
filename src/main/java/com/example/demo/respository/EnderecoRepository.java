package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.cliente.id = :id")
    List<Endereco> findId(Long id);

}
