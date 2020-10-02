package com.example.demo.respository;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProdutoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Produto> getProdutoAtivo(){
        String jpql = "select p from Produto p where p.active = true";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        return query.getResultList();
    }
    public List<Produto> getProdutoInativo(){
        String jpql = "select p from Produto p where p.active = false";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        return query.getResultList();
    }



    public List<Produto> getProdutoPorFiltros(String categoriaProduto){
        String jpql = "select p from Produto p where p.nome like :pNome";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("pNome", categoriaProduto);
        return query.getResultList();
    }

    public List<Produto> getProdutoPorFiltrosCodigo(Integer numero){
        String jpql = "select p from Produto p where p.codigo = :pCodigo";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("pCodigo", numero);
        return query.getResultList();
    }


    public List<Produto> getProdutoPorCategoria(String categoriaProduto){
        String jpql = "select p from Produto p where p.categoria like :pCategoria";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("pCategoria", categoriaProduto);
        return query.getResultList();
    }

    public List<Produto> getProdutoPorGenero(String nomeSexo){
        String jpql = "select p from Produto p where p.sexo like :pSexo";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("pSexo", nomeSexo);
        return query.getResultList();
    }


    public List<Produto> getProdutoPorTamanho(String tamanho){
        String jpql = "select p from Produto p where p.tamanho like :pTamanho";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("pTamanho", tamanho);
        return query.getResultList();
    }

}
