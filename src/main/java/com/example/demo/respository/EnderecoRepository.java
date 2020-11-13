package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("from Endereco e inner join Cliente c on e.cliente.id = c.id where e.cliente.id = :id")
    List<Endereco> findId(Long id);

    @Query("from Endereco e where e.cliente.id = :id")
    Endereco findUmId(Long id);

}
