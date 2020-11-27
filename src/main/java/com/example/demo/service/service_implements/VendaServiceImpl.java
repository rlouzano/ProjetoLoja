package com.example.demo.service.service_implements;

import com.example.demo.model.*;
import com.example.demo.respository.CartaoRepository;
import com.example.demo.respository.PedidoRepository;
import com.example.demo.respository.VendaRepository;
import com.example.demo.respository.querys.ProdutoCustomRepository;
import com.example.demo.respository.querys.VendaCustomRepository;
import com.example.demo.service.CartaoService;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ProdutoCustomRepository produtoCustomRepository;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public void create(Venda venda, User usuario, Cartao cartao, Endereco enderecos, List<Carrinho> carrinhos) {
        Random aleatorio = new Random();
        int valor = aleatorio.nextInt();
        for (Carrinho c : carrinhos) {
            venda.setId(c.getId());
            venda.setImg(c.getImg1());
            venda.setCodigo(c.getCodigo());
            venda.setValor(c.getValor());
            venda.setFrete(c.getFrete());
            venda.setQuantidade(c.getQuantidade());
            venda.setTotal(c.getTotal());
            venda.setStatus("Aguardando Pagamento");
            venda.setCodigo_pedido(valor);
            venda.setLogradouro(enderecos.getLogradouro());
            venda.setBairro(enderecos.getBairro());
            venda.setCep(enderecos.getCep());
            venda.setComplemento(enderecos.getComplemento());
            venda.setLocalidade(enderecos.getLocalidade());
            venda.setNumero(enderecos.getNumero());
            venda.setTipo(enderecos.getTipo());
            venda.setUf(enderecos.getUf());
            venda.setBandeira(cartao.getBandeira());
            venda.setCodigo_cartao(cartao.getCodigo());
            venda.setNomeTitular(cartao.getNomeTitular());
            venda.setVencimento(cartao.getVencimento());
            venda.setParcelas(cartao.getParcelas());
            venda.setCliente(usuario.getCliente());
            this.repository.save(venda);
        }
    }



    @Override
    public void update(Long id, String status) {
        Venda venda = new Venda();
        Venda c = findById(id);
        if (!c.equals(null)) {
            venda.setId(c.getId());
            venda.setImg(c.getImg());
            venda.setCodigo(c.getCodigo());
            venda.setValor(c.getValor());
            venda.setFrete(c.getFrete());
            venda.setQuantidade(c.getQuantidade());
            venda.setTotal(c.getTotal());
            venda.setStatus(status);
            venda.setCodigo_pedido(c.getCodigo_pedido());
            venda.setLogradouro(c.getLogradouro());
            venda.setBairro(c.getBairro());
            venda.setCep(c.getCep());
            venda.setComplemento(c.getComplemento());
            venda.setLocalidade(c.getLocalidade());
            venda.setNumero(c.getNumero());
            venda.setTipo(c.getTipo());
            venda.setUf(c.getUf());
            venda.setBandeira(c.getBandeira());
            venda.setCodigo_cartao(c.getCodigo_cartao());
            venda.setNomeTitular(c.getNomeTitular());
            venda.setVencimento(c.getVencimento());
            venda.setParcelas(c.getParcelas());
            venda.setCliente(c.getCliente());
            this.repository.save(venda);
        }
    }
    private Venda findById(Long id){
        return this.repository.getOne(id);
    }

}
