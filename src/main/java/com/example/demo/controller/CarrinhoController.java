package com.example.demo.controller;

import com.example.demo.controller.dto.ProdutosDTO;
import com.example.demo.model.*;
import com.example.demo.respository.*;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.respository.querys.ProdutoCustomRepository;
import com.example.demo.respository.querys.VendaCustomRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private VendaCustomRepository vendaCustomRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ProdutoCustomRepository produtoCustomRepository;

    private User usuario = new User();

    private ProdutosDTO dto = new ProdutosDTO();

    private List<Produto> produtos = dto.listarTodos();
    //private List<Produto> produtos = new ArrayList<>();

    @GetMapping("/listar")
    public ModelAndView ListaCarrinho(Model model, Produto prod, RedirectAttributes redirectAtt) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        RemoverCartao();
        double total = 0;
        double frete = 20.00;
        ModelAndView mv = new ModelAndView("carrinho/carrinho");
        List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        if (usuario.getId() != null) {
            adicionar(frete, usuario);
            ListaUsuariologadoVazio(model, carrinhos);
            ListaUsuarioLogadoCheio(model, carrinhos);
            return mv;
        } else {
            double valor = calculaTotal(total);
            ListaUsuarioDeslogadoVazio(model, valor, frete);
            ListaUsuarioDeslogadoCheio(model, valor, frete);
            return mv;
        }
    }

    @GetMapping("/confirma_endereco/{id}")
    public ModelAndView ConfirmaEndereco(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("carrinho/EnderecoCarrinho");
        List<Endereco> endereco = this.enderecoRepository.findId(usuario.getCliente().getId());
        List<Carrinho> carrinho = carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        Endereco endereco1 = enderecoService.listaPorUm(id);
        Cartao cartao = this.cartaoRepository.findByClienteId(usuario.getCliente().getId());
        mv.addObject("cartao", cartao);
        mv.addObject("endereco", endereco);
        mv.addObject("role", "null");
        mv.addObject("frete", carrinho.iterator().next().getFrete());
        mv.addObject("subtotal", carrinho.iterator().next().getValor());
        mv.addObject("total", carrinho.iterator().next().getTotal());
        mv.addObject("cfd", endereco1);
        return mv;
    }

    @GetMapping("/carrinho_endereco")
    public ModelAndView CarrinhoEndereco(Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("carrinho/EnderecoCarrinho");
        List<Endereco> endereco = this.enderecoRepository.findId(usuario.getCliente().getId());
        List<Carrinho> carrinho = carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        Endereco endereco1 = enderecoService.listaPorUm(endereco.get(0).getId());
        Cartao cartao = this.cartaoRepository.findByClienteId(usuario.getCliente().getId());
        mv.addObject("cartao", cartao);
        mv.addObject("endereco", endereco);
        mv.addObject("role", "null");
        mv.addObject("frete", carrinho.iterator().next().getFrete());
        mv.addObject("subtotal", carrinho.iterator().next().getValor());
        mv.addObject("total", carrinho.iterator().next().getTotal());
        mv.addObject("cfd", endereco1);
        return mv;
    }

    @GetMapping("/forma_pagamento_cartao")
    public ModelAndView formaPagamentoCartao(Cartao cartao, Model model) {
        ModelAndView mv = new ModelAndView("carrinho/formaPagamentoCartao");
        somaQtdCarrinho(model);
        List<Carrinho> carrinho = carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        mv.addObject("role", "null");
        mv.addObject("frete", carrinho.iterator().next().getFrete());
        mv.addObject("subtotal", carrinho.iterator().next().getValor());
        mv.addObject("total", carrinho.iterator().next().getTotal());
        return mv;
    }

    @GetMapping("/forma_pagamento_boleto")
    public ModelAndView formaPagamentoBoleto(Cartao cartao, Model model) {
        ModelAndView mv = new ModelAndView("carrinho/formaPagamentoBoleto");
        somaQtdCarrinho(model);
        List<Carrinho> carrinho = carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        mv.addObject("role", "null");
        mv.addObject("frete", carrinho.iterator().next().getFrete());
        mv.addObject("subtotal", carrinho.iterator().next().getValor());
        mv.addObject("total", carrinho.iterator().next().getTotal());
        return mv;
    }

    @GetMapping("/tipo_pagamento")
    public ModelAndView tipoPagamento(Model model) {
        ModelAndView mv = new ModelAndView("carrinho/tipoPagamento");
        somaQtdCarrinho(model);
        List<Carrinho> c = carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        mv.addObject("role", "null");
        mv.addObject("frete", c.iterator().next().getFrete());
        mv.addObject("subtotal", c.iterator().next().getValor());
        mv.addObject("total", c.iterator().next().getTotal());
        return mv;
    }

    @PostMapping("/cadastro_boleto")
    public ModelAndView cadastroBoleto() {
        buscarUsuarioLogado();
        Cartao cartao = new Cartao();
        cartao.setCodigo(" ");
        cartao.setNomeTitular(usuario.getCliente().getNome());
        cartao.setParcelas("A Vista");
        cartao.setNumeroCartao(" ");
        cartao.setVencimento("00/00/0000");
        cartao.setBandeira("Boleto");
        this.cartaoService.create(cartao, usuario.getCliente());
        return new ModelAndView("redirect:/carrinho/carrinho_endereco");
    }

    @GetMapping("/status")
    public ModelAndView PedidoStatus(Venda venda, Model model, String pagto) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("carrinho/status");
        List<Venda> vendas = this.vendaCustomRepository.getVendas(usuario.getCliente().getId());
        mv.addObject("v", vendas);
        mv.addObject("numero", vendas.iterator().next().getCodigo_pedido());
        mv.addObject("total", vendas.iterator().next().getTotal());
        mv.addObject("vendas", vendas.iterator().next().getStatus());
        mv.addObject("aprovado", vendas.iterator().next().getAprovado());
        return mv;
    }

    @PutMapping("/alterar_quantidade/{id}")
    public ModelAndView alterarQuantidade(@PathVariable("id") Integer id, Produto produto) {
        alterarQuantidade(produto, id);
        return new ModelAndView("redirect:/carrinho/listar");
    }

    @PostMapping("/forma_pagamento_cartao")
    public String formaPagamentoCartao(@Valid @ModelAttribute Cartao cartao, BindingResult bindingResult, String parcela) {
        buscarUsuarioLogado();
        if (bindingResult.hasErrors()) {
            return "carrinho/formaPagamentoCartao";
        }
        this.cartaoService.create(cartao, usuario.getCliente());
        return "redirect:/carrinho/carrinho_endereco";
    }

    @PostMapping("/forma_pagamento_boleto")
    public String formaPagamentoBoleto(@Valid @ModelAttribute Cartao cartao, BindingResult bindingResult) {
        buscarUsuarioLogado();
        if (bindingResult.hasErrors()) {
            return "carrinho/formaPagamentoBoleto";
        }
        this.cartaoService.create(cartao, usuario.getCliente());
        return "redirect:/carrinho/status_pagamento";
    }

    @GetMapping("/status_pagamento")
    public ModelAndView statusPagamento() {
        return new ModelAndView("");
    }

    @PutMapping("/alterar_quantidade_logado/{id}")
    public ModelAndView alterarQuantidadeLogado(@PathVariable("id") Long id, int quantidade) {
        ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
        Carrinho carrinho = new Carrinho();
        carrinho.setQuantidade(quantidade);
        this.carrinhoService.update(id, carrinho);
        return mv;
    }

    @PostMapping("/adiciona_carrinho/{id}")
    public String adicionarCarrinho(@PathVariable("id") Integer id, Model model) {
        buscarUsuarioLogado();
        if (usuario.getId() == null) {
            //model.addAttribute("role", "null");
            //int aux = (1 + produto.getQuantidade());
            if (this.produtos.size() == 0) {
                adicionarEmMemoria(id);
                return "redirect:/produtos/detalhes/" + id;
            }
            for (int i = 0; i < this.produtos.size(); i++) {
                if (produtos.get(i).getId().equals(id)) {
                    produtos.get(i).setQuantidade(produtos.get(i).getQuantidade() + 1);
                    return "redirect:/produtos/detalhes/" + id;
                }
            }
            //   alterarQuantidadeEmMemoria(produto, id, 1);
            for (Produto produtos : produtos) {
                if (!produtos.getId().equals(id)) {
                    adicionarEmMemoria(id);
                    return "redirect:/produtos/detalhes/" + id;
                }
            }
            return "redirect:/produtos/detalhes/" + id;
        } else {
            Produto produto = produtoService.listaPorUm(id);
            model.addAttribute("role", "null");
            System.out.println("Entrou rsrs");
            Produto produto1 = this.produtoService.listaPorUm(id);
            List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
            this.carrinhoService.adiciona(produto1, 20, usuario);
            return "redirect:/produtos/detalhes/" + id;
        }
    }


    @PostMapping("/finalizar_carrinho/{id}")
    public String finalizarCarrinho(@PathVariable("id") Integer id, Model model) {
        buscarUsuarioLogado();
        if (usuario.getId() == null) {
            if (this.produtos.size() == 0) {
                adicionarEmMemoria(id);
                return "redirect:/carrinho/listar/";
            }
            /* SE O PRODUTO NO CARRINHO FOR IGUAL AO QUE O USUÁRIO DESEJA INCLUIR, SOMA + 1*/
            for (int i = 0; i < this.produtos.size(); i++) {
                if (produtos.get(i).getId().equals(id)) {
                    produtos.get(i).setQuantidade(produtos.get(i).getQuantidade() + 1);
                    return "redirect:/carrinho/listar/";
                }
            }
            /* SE O PRODUTO QUE O USUARIO DESEJA INCLUIR FOR DIFERENTE DOS PRODUTOS NO CARRINHO ADICIONA*/
            for (Produto produtos : produtos) {
                if (!produtos.getId().equals(id)) {
                    adicionarEmMemoria(id);
                    return "redirect:/carrinho/listar/";
                }
            }
            return "redirect:/produtos/detalhes/" + id;
        } else {
            Produto produto = produtoService.listaPorUm(id);
            model.addAttribute("role", "null");
            System.out.println("Entrou rsrs");
            Produto produto1 = this.produtoService.listaPorUm(id);
            List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
            this.carrinhoService.adiciona(produto1, 20, usuario);
            return "redirect:/carrinho/listar/";
        }
    }

    @RequestMapping(value = "/remover_carrinho/{id}", method = RequestMethod.POST)
    public ModelAndView removerCarrinho(@PathVariable("id") Integer id, Produto produto) throws
            ConcurrentModificationException, TemplateInputException {
        ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
        for (int i = 0; i < this.produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                removerCarrinho(i);
            }
        }
        return mv;
    }


    @PutMapping(value = "/remover_carrinho_logado/{id}")
    public ModelAndView removerCarrinhoLogado(@PathVariable("id") Long id, Carrinho carrinho) throws
            ConcurrentModificationException, TemplateInputException {
        ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
        carrinhoService.delete(id);
        return mv;
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

    private void adicionarEmMemoria(Integer id) {
        Produto produto = produtoService.listaPorUm(id);
        produto.setQuantidade(1);
        this.produtos.add(produto);
    }

    private void adicionar(double frete, User user) {

        for (Produto pd : this.produtos) {
            Produto produto = this.produtoService.listaPorUm(pd.getId());
            produto.setQuantidade(pd.getQuantidade());
            this.carrinhoService.adiciona(produto, frete, user);
        }
        if (!produtos.isEmpty()) {
            for (int i = 0; i < this.produtos.size(); i++) {
                this.produtos.remove(i);
            }
        }
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
        }
    }

    private void ListaUsuarioDeslogadoVazio(Model model, Double total, Double frete) {
        buscarUsuarioLogado();
        if (total == 0) {
            model.addAttribute("frete", frete);
            model.addAttribute("produto", this.produtos);
            model.addAttribute("role", "null");
            model.addAttribute("subtotal", 0);
            model.addAttribute("total", 0);
        }
    }

    private void removerCarrinho(int i) {
        this.produtos.remove(i);
    }

    private void alterarQuantidade(Produto produto, Integer id) {
        for (Produto prod : this.produtos) {
            if (prod.getId().equals(id)) {
                prod.setQuantidade(produto.getQuantidade());
            }
        }
    }

    private void alteraProduto(User usuario) {
        List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        for (int i = 0; i < carrinhos.size(); i++) {
            List<Produto> produtos = produtoCustomRepository.getProdutoPorCodigo(carrinhos.get(i).getCodigo());
            produtoService.updateQuantidade(produtos.get(i).getId(), produtos.get(i));
        }
    }

    public void CadastraVenda(Endereco enderecos, Cartao cartao1) {
        buscarUsuarioLogado();
        Venda venda = new Venda();
        List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        List<Venda> vendas = new ArrayList<>();
        this.vendaService.create(venda, usuario, cartao1, enderecos, carrinhos);
        this.carrinhoRepository.deleteAll();
    }

    private void AtualizarProduto(Integer codigo) {
        List<Produto> produtos = this.produtoCustomRepository.getProdutoPorCodigo(codigo);
        for (Produto prod : produtos) {
            this.produtoService.update(prod.getId(), prod);
        }
    }

    public boolean RemoverCartao() {
        this.cartaoRepository.deleteAll();
        return true;
    }

    private void ListaUsuarioDeslogadoCheio(Model model, Double total, Double frete) {
        buscarUsuarioLogado();
        if (total != 0) {
            model.addAttribute("frete", frete);
            model.addAttribute("produto", this.produtos);
            model.addAttribute("role", "null");
            model.addAttribute("subtotal", total);
            model.addAttribute("total", (total + 20.00));
        }
        /*
        if (total != 0) {
            model.addAttribute("frete", frete);
            model.addAttribute("produto", this.produtos);
            model.addAttribute("role", "null");
            model.addAttribute("subtotal", total);
            model.addAttribute("total", (total + 20.00));
        }*/
    }

    private void ListaUsuariologadoVazio(Model model, List<Carrinho> carrinhos) {
        buscarUsuarioLogado();
        if (carrinhos.isEmpty()) {
            model.addAttribute("frete", 0);
            model.addAttribute("produto", carrinhos);
            model.addAttribute("role", usuario.getRoles().iterator().next().getName());
            model.addAttribute("subtotal", 0);
            model.addAttribute("total", 0);
        }
    }

    private void ListaUsuarioLogadoCheio(Model model, List<Carrinho> carrinhos) {
        buscarUsuarioLogado();
        double subtotal = 0;
        double total = 0;
        if (!carrinhos.isEmpty()) {
            for (Carrinho c : carrinhos) {
                subtotal = subtotal += (c.getQuantidade() * c.getValor());
                total = total += (c.getQuantidade() * c.getValor()) + c.getFrete();
            }

            model.addAttribute("frete", carrinhos.iterator().next().getFrete());
            model.addAttribute("produto", carrinhos);
            model.addAttribute("role", usuario.getRoles().iterator().next().getName());
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("total", total);
        }
    }

    private double calculaTotal(Double total) {
        for (Produto pd : this.produtos) {
            total = total += (pd.getQuantidade() * pd.getValor());
        }
        return total;
    }
}
