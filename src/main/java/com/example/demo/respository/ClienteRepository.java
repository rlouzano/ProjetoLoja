package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from User u inner join Cliente c on c.id = u.id where u.id = :id")
    Cliente findId(Long id);

}
