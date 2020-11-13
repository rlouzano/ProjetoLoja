package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select us.* from users us inner join users_roles ur on us.id = ur.user_id where ur.role_id = :role_id and us.id not in(:user_id)", nativeQuery = true)
    public List<User> findAllWhereRoleEquals(@Param("role_id") Long role_id, @Param("user_id") Long user_id);

    public User findByEmail(String email);

    @Query("from User u inner join Role r on u.id = r.id where email=?1")
    public List<User> buscaClienteEmail(String email);

    @Query("select u from User u where u.active = true")
    public List<User> findAtivo();


    @Query("select u from User u where u.active = false")
    public List<User> findInativo();


}
