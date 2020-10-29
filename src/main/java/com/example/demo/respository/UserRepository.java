package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("from User u inner join Role r on u.id = r.id where email=?1")
    public List<User> buscaClienteEmail(String email);

    @Query("select u from User u where u.active = true")
    public List<User> findAtivo();


    @Query("select u from User u where u.active = false")
    public List<User> findInativo();


}
