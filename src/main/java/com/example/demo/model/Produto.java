package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_increment
    private Integer id;
    private String sexo;
    private String nome;
    private int quantidade;
    private double valor;
    private String img1;
    private String img2;
    private String img3; //100% algodão
    private String modelo;
    private String descricao;
    private String altura;
    private String busto;
    private String cintura;
    private String quadril;
    private String tamanho;
    private String categoria;

    public Produto() {
    }

    public Produto(Integer id, String sexo, String nome, int quantidade, double valor, String img1, String img2, String img3, String modelo, String descricao, String altura, String busto, String cintura, String quadril, String tamanho, String categoria) {
        this.id = id;
        this.sexo = sexo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.modelo = modelo;
        this.descricao = descricao;
        this.altura = altura;
        this.busto = busto;
        this.cintura = cintura;
        this.quadril = quadril;
        this.tamanho = tamanho;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getBusto() {
        return busto;
    }

    public void setBusto(String busto) {
        this.busto = busto;
    }

    public String getCintura() {
        return cintura;
    }

    public void setCintura(String cintura) {
        this.cintura = cintura;
    }

    public String getQuadril() {
        return quadril;
    }

    public void setQuadril(String quadril) {
        this.quadril = quadril;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
