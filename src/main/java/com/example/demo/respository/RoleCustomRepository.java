package com.example.demo.respository;

import com.example.demo.model.Produto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> getRoleId(long id) {
        String jpql = "select p.name, r.name from User p inner join users_roles s on p.id = s.user_id inner join Role r on s.role_id = r.id where p.id = :pId";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }


}
