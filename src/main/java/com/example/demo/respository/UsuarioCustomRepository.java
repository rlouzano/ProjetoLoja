package com.example.demo.respository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> getUsurioAtivo(Long id) {
        String jpql = "select p from User p inner join Cliente c on p.id = c.id inner join Endereco e on p.id = e.id where p.active = true and p.id != :pId";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }

    public List<User> getUsurioInativo(Long id) {
        String c = "!=";
        String jpql = "select p from User p where p.active = false and p.id != :pId";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }

    public List<Role> find(Long id) {
        String jpql = " select u.email, r.name from Role u inner join users_roles s on u.id = s.user_id inner join User r on s.role_id like r.id where u.id = :pId";
        TypedQuery<Role> query = em.createQuery(jpql, Role.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }


}
