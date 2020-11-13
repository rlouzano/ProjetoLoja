package com.example.demo.model;

import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {


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
    private List<Role> roles;

    @OneToOne
    @JoinColumn
    private Cliente cliente;

    @OneToOne
    private Carrinho carrinho;

    public User() {
    }

    public User(@Email(message = "Email invalido") @NotEmpty(message = "Não pode ser vazio") String email, @NotEmpty(message = "Não pode ser vazio") @Length(min = 5, message = "O password deverá ter 5 digitos") String password, Boolean active, List<Role> roles, Cliente cliente) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.cliente = cliente;
    }

    public User(Long id, @Email(message = "Email invalido") @NotEmpty(message = "Não pode ser vazio") String email, @NotEmpty(message = "Não pode ser vazio") @Length(min = 5, message = "O password deverá ter 5 digitos") String password, Boolean active, List<Role> roles, Cliente cliente) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
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
