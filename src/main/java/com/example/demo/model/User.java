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
    @Email(message = "Por favor, preencher o email")
    @NotEmpty(message = "Não pode ser vazio")
    private String email;

    @Column
    @NotEmpty(message = "Não pode ser vazio")
    @Length(min = 5)
    private String name;

    @CPF(message = "cpf inválido")
    private String cpf;

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

    public User(String email, String name,  String cpf, String password, Boolean active, Set<Role> roles) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(Long id, String email, String name, String cpf, String password, Boolean active, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.active = active;
        this.roles = roles;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
