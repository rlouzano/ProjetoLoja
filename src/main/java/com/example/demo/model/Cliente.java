package com.example.demo.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Não pode ser vazio")
    @Length(min = 5)
    private String nome;

    @CPF
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> endereco = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Cliente() {
    }

    public Cliente(@NotEmpty(message = "Não pode ser vazio") @Length(min = 5) String nome, @CPF String cpf, List<Endereco> endereco, User user) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.user = user;
    }

    public Cliente(Long id, @NotEmpty(message = "Não pode ser vazio") @Length(min = 5) String nome, @CPF String cpf, List<Endereco> endereco, User user) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
