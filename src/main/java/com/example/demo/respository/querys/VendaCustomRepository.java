package com.example.demo.respository.querys;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VendaCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Venda> getVendas(Long id) {
        String jpql = "select v from Venda v inner join Cliente u on v.cliente.id = u.id where u.id = :pId group by v.codigo_pedido";
        TypedQuery<Venda> query = em.createQuery(jpql, Venda.class);
        query.setParameter("pId", id);
        return query.getResultList();
    }

    public void vendaCarrinho(List<Produto> produto, List<Carrinho> carrinhos) {
        try {
            for (Produto prod : produto) {
                em.merge(prod.getQuantidade());
            }
            em.remove(carrinhos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
