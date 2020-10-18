package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty(message = "Não pode ser vazio")
    private String cep;
    @Column
    @NotEmpty(message = "Não pode ser vazio")
    private String logradouro;

    private String complemento;
    @Column
    @NotEmpty(message = "Não pode ser vazio")
    private String bairro;
    @Column
    @NotEmpty(message = "Não pode ser vazio")
    private String localidade;
    @Column
    @NotEmpty(message = "Não pode ser vazio")
    private String uf;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Endereco() {
    }


    public Endereco(@NotEmpty(message = "Não pode ser vazio") String cep, @NotEmpty(message = "Não pode ser vazio") String logradouro, String complemento, @NotEmpty(message = "Não pode ser vazio") String bairro, @NotEmpty(message = "Não pode ser vazio") String localidade, @NotEmpty(message = "Não pode ser vazio") String uf, Cliente cliente) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.cliente = cliente;
    }

    public Endereco(Long id, @NotEmpty(message = "Não pode ser vazio") String cep, @NotEmpty(message = "Não pode ser vazio") String logradouro, String complemento, @NotEmpty(message = "Não pode ser vazio") String bairro, @NotEmpty(message = "Não pode ser vazio") String localidade, @NotEmpty(message = "Não pode ser vazio") String uf, Cliente cliente) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
