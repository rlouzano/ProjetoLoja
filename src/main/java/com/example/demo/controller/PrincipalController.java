package com.example.demo.controller;

import com.example.demo.controller.dto.ProdutosDTO;
import com.example.demo.model.Carrinho;
import com.example.demo.model.Produto;
import com.example.demo.model.User;
import com.example.demo.respository.EnderecoRepository;
import com.example.demo.respository.RolesRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.service.CarrinhoService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RolesRepository rolesRepository;

    private User usuario = new User();

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoService produtoService;

    private void adicionar(double frete, User user) {
        ProdutosDTO dto = new ProdutosDTO();
        List<Produto> prod = dto.listarTodos();
        if (!prod.isEmpty()) {
            for (Produto pd : prod) {
                Produto produto = this.produtoService.listaPorUm(pd.getId());
                this.carrinhoService.adiciona(produto, frete, user);
            }
            if (!prod.isEmpty()) {
                for (int i = 0; i < prod.size(); i++) {
                    prod.remove(i);
                }
            }
        }
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        String email = usuario.getEmail();
        if (email == null) {
            return "index";
        } else {
            adicionar(20, usuario);
            System.out.println(usuario.getRoles().iterator().next().getName());
            model.addAttribute("role", usuario.getRoles().iterator().next().getName());
            model.addAttribute("usuario", usuario);
            model.addAttribute("logado", this.clienteService.listaPorUm(usuario.getCliente().getId()));
            return "indexAdmin";
        }
    }

    private void somaQtdCarrinho(Model model) {
        buscarUsuarioLogado();
        int total = 0;
        if (usuario.isEnabled()) {
            List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
            for (Carrinho c : carrinhos) {
                total = total += c.getQuantidade();
            }
            model.addAttribute("car", total);
        } else {
            model.addAttribute("car", null);
        }
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }
}
