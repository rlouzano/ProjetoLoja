package com.example.demo.respository;

import com.example.demo.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    @Query("from Carrinho c inner join User u on c.user.id = u.id where c.user.id = :id")
    public Carrinho buscaPorId(Long id);

    @Query("from Carrinho c inner join User u on c.user.id = u.id where c.user.id = :id")
    public List<Carrinho> findId(Long id);
}
