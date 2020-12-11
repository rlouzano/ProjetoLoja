package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.respository.*;
import com.example.demo.respository.querys.CarrinhoCustomRepository;
import com.example.demo.respository.querys.ProdutoCustomRepository;
import com.example.demo.respository.querys.VendaCustomRepository;
import com.example.demo.service.CartaoService;
import com.example.demo.service.EnderecoService;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.VendaService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private CarrinhoCustomRepository carrinhoCustomRepository;

    @Autowired
    private VendaCustomRepository vendaCustomRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    private User usuario = new User();

    @GetMapping("/finalizar_pedido/{id}")
    public ModelAndView finalizarPedido(@PathVariable("id") Long id) {
        buscarUsuarioLogado();
        double valor = 0, total = 0;
        ModelAndView mv = new ModelAndView("carrinho/finalizarPedido");
        Cartao cartao = this.cartaoRepository.findByClienteId(usuario.getCliente().getId());
        Endereco endereco = this.enderecoService.listaPorUm(id);
        List<Carrinho> carrinho = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        for (Carrinho c : carrinho) {
            valor = valor += c.getValor();
            total = total += c.getTotal();
        }
        mv.addObject("endereco", endereco);
        mv.addObject("carrinho", carrinho);
        mv.addObject("cartao", cartao);
        mv.addObject("subtotal", valor);
        mv.addObject("frete", carrinho.iterator().next().getFrete());
        mv.addObject("total", total);
        return mv;
    }

    private boolean validaQtd(Produto p) {
        List<Carrinho> car = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        for (Carrinho carrinho : car) {
            if (p.getCodigo().equals(carrinho.getCodigo())) {
                if (p.getQuantidade() - carrinho.getQuantidade() < 0) {
                    System.out.println(p.getNome() + " Qtd " + p.getQuantidade());
                    return false;
                } else {
                    p.setId(p.getId());
                    p.setImg1(p.getImg1());
                    p.setImg2(p.getImg2());
                    p.setImg3(p.getImg3());
                    p.setNome(p.getNome());
                    p.setQuantidade(p.getQuantidade() - carrinho.getQuantidade());
                    p.setValor(p.getValor());
                    p.setModelo(p.getModelo());
                    p.setDescricao(p.getDescricao());
                    p.setAltura(p.getAltura());
                    p.setBusto(p.getBusto());
                    p.setCintura(p.getCintura());
                    p.setQuadril(p.getQuadril());
                    p.setTamanho(p.getTamanho());
                    p.setCor(p.getCor());
                    p.setCategoria(p.getCategoria());
                    p.setPergunta1(p.getPergunta1());
                    p.setResposta1(p.getResposta1());
                    p.setPergunta2(p.getPergunta2());
                    p.setResposta2(p.getResposta2());
                    p.setPergunta3(p.getPergunta3());
                    p.setResposta3(p.getResposta3());
                    p.setActive(p.isActive());
                    this.produtoRepository.save(p);
                    return true;
                }
            }
        }
        return true;
    }

    @PostMapping("/cadastro_pedido")
    @Transactional
    public ModelAndView cadastroPedido(Long id_endereco, Long id_cartao, RedirectAttributes redirectAtt) {
        Endereco endereco = this.enderecoService.listaPorUm(id_endereco);
        Cartao cartao = this.cartaoService.buscaUm(id_cartao);
        List<Produto> pr = this.produtoRepository.findAll();
        for (Produto p : pr) {
            if (!validaQtd(p)) {
                redirectAtt.addFlashAttribute("msg", "Lamento, temos apenas " + p.getQuantidade() + " unidades no estoque do produto : " + p.getNome() + " codigo: " + p.getCodigo());
                return new ModelAndView("redirect:/carrinho/listar");
            } else {
                CadastraVenda(endereco, cartao);
                return new ModelAndView("redirect:/pedidos/numero_pedido");
            }
        }
        return new ModelAndView("redirect:/pedidos/numero_pedido");

    }

    @GetMapping("/numero_pedido")
    public ModelAndView numeroPedido(Venda venda, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("carrinho/numeroPedido");
        List<Venda> vendas = this.vendaRepository.findByClienteId(usuario.getCliente().getId());
        System.out.println(vendas.iterator().next().getCodigo_pedido());
        mv.addObject("vendas", vendas.iterator().next().getCodigo_pedido());
        return mv;
    }

    @GetMapping("/pedidos_estoquista")
    public ModelAndView pedidosEstoquista(String status) {
        buscarUsuarioLogado();
        ModelAndView mv = new ModelAndView("estoque/pedidos");
        List<Venda> vendas = this.vendaCustomRepository.getTodos(status);
        mv.addObject("vendas", vendas);
        return mv;
    }

    @RequestMapping("/cad_endereco_carrinho/{id}")
    public ModelAndView CadastroEnderecoCarrinho(@PathVariable("id") Long id, Long cartao_id) {
        buscarUsuarioLogado();
        Endereco enderecos = this.enderecoService.listaPorUm(id);
        Cartao cartao1 = this.cartaoService.buscaUm(cartao_id);
        Venda venda = new Venda();
        CadastraVenda(enderecos, cartao1);
        return new ModelAndView("redirect:/carrinho/numero_pedido");
    }

    @GetMapping("/status_pedido")
    public ModelAndView StatusPedidoEstoquista(Venda venda, String status, Model model) {
        buscarUsuarioLogado();
        somaQtdCarrinho(model);
        ModelAndView mv = new ModelAndView("estoque/status");
        List<Venda> vendas = this.vendaCustomRepository.getTodos(status);
        mv.addObject("v", vendas);
        return mv;
    }

    @PutMapping("/editar_status/{id}")
    public ModelAndView Editar(@PathVariable("id") Long id, String codigo_pedido) {
        this.vendaService.update(id, codigo_pedido);
        return new ModelAndView("redirect:/pedidos/pedidos_estoquista?status=Aguardando+Pagamento");
    }

    public void CadastraVenda(Endereco enderecos, Cartao cartao1) {
        buscarUsuarioLogado();
        Venda venda = new Venda();
        //List<Produto> prod = this.produtoService.listarTodos();
        List<Carrinho> carrinhos = this.carrinhoCustomRepository.getCarrinhoId(usuario.getId());
        this.vendaService.create(venda, usuario, cartao1, enderecos, carrinhos);
        this.carrinhoRepository.deleteAll();
    }

    private void buscarUsuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String email = autenticado.getName();
            this.usuario = userRepository.buscaClienteEmail(email).get(0);
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


}
