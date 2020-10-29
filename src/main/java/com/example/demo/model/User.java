package com.example.demo.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Email(message = "Email invalido")
    @NotEmpty(message = "Não pode ser vazio")
    private String email;

    @Column
    @NotEmpty(message = "Não pode ser vazio")
    @Length(min = 5, message = "O password deverá ter 5 digitos")
    private String password;
    @Column
    private Boolean active = true;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",  /*Aqui é um foregn key, onde crio uma tabela com os dois id de cada tabela: User e Role*/
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne
    @JoinColumn
    private Cliente cliente;

    public User() {
    }

    public User(@Email(message = "Por favor, preencher o email") @NotEmpty(message = "Não pode ser vazio") String email, @NotEmpty(message = "Não pode ser vazio") @Length(min = 5, message = "O password deverá ter 5 digitos") String password, Boolean active, Set<Role> roles, Cliente cliente) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.cliente = cliente;
    }

    public User(Long id, @Email(message = "Por favor, preencher o email") @NotEmpty(message = "Não pode ser vazio") String email, @NotEmpty(message = "Não pode ser vazio") @Length(min = 5, message = "O password deverá ter 5 digitos") String password, Boolean active, Set<Role> roles, Cliente cliente) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
