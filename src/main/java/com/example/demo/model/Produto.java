package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_increment
    private Integer id;
    private String img1;
    private String img2;
    private String img3; //100% algodão
    private String nome;
    private String cor;
    private String descricao;
    private Integer codigo;
    private int quantidade;
    private double valor;
    private String sexo;
    private String modelo;
    private String altura;
    private String busto;
    private String cintura;
    private String quadril;
    private String tamanho;
    private String categoria;
    private String pergunta1;
    private String pergunta2;
    private String pergunta3;
    private boolean active = true;

    public Produto() {
    }

    public Produto(String img1, String img2, String img3, String nome, String cor, String descricao, Integer codigo, int quantidade, double valor, String sexo, String modelo, String altura, String busto, String cintura, String quadril, String tamanho, String categoria, String pergunta1, String pergunta2, String pergunta3, boolean active) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.nome = nome;
        this.cor = cor;
        this.descricao = descricao;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.sexo = sexo;
        this.modelo = modelo;
        this.altura = altura;
        this.busto = busto;
        this.cintura = cintura;
        this.quadril = quadril;
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.active = active;
    }

    public Produto(Integer id, String img1, String img2, String img3, String nome, String cor, String descricao, Integer codigo, int quantidade, double valor, String sexo, String modelo, String altura, String busto, String cintura, String quadril, String tamanho, String categoria, String pergunta1, String pergunta2, String pergunta3, boolean active) {
        this.id = id;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.nome = nome;
        this.cor = cor;
        this.descricao = descricao;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.sexo = sexo;
        this.modelo = modelo;
        this.altura = altura;
        this.busto = busto;
        this.cintura = cintura;
        this.quadril = quadril;
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public String getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(String pergunta3) {
        this.pergunta3 = pergunta3;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}