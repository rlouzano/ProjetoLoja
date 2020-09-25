package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/registration")
                .permitAll()
                .antMatchers("/roles/new")
                .permitAll()
                .antMatchers("/**")
                .hasAnyAuthority("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?errors=true")
                .defaultSuccessUrl("/users")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied");

                /*
                .authorizeRequests()
                .antMatchers("/login").permitAll()                      //TODOS TEM ACESSO
                .antMatchers("/registration").permitAll()               //TODOS TEM ACESSO
                .antMatchers("/**").hasAnyAuthority("ADMIN", "USER")    //AUTORIZADO SOMENTE OS PERFIS CITADOS
                 */
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers(
                        "/static/**",
                        "/js/**",
                        "/css/**",
                        "/videos/**",
                        "/images/**",
                        "/resources/**"
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(" select u.email, u.password, u.active from users u where u.email like ? and u.active = 1")
                .authoritiesByUsernameQuery(" select u.email, r.name from users u inner join users_roles s on u.id = s.user_id inner join roles r on s.role_id like r.id where u.email = ? and u.active = 1")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
