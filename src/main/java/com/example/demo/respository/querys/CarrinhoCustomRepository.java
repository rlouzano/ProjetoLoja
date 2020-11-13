package com.example.demo.respository.querys;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CarrinhoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Carrinho> getCarrinhoId(Long id) {
        String jpql = "select c from Carrinho c inner join User u on c.user.id = u.id where c.user.id = :pId";
        TypedQuery<Carrinho> query = em.createQuery(jpql, Carrinho.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }
}
