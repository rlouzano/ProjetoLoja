package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.respository.ProdutoCustomRepository;
import com.example.demo.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoCustomRepository produtoCustomRepository;

    @GetMapping(value = "/listar")
    public ModelAndView listar(Produto produto, Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/estoque")
    public ModelAndView listarEstoque(Produto produto, String nomeproduto) {
        ModelAndView mv = new ModelAndView("produtos/estoque");
        List<Produto> produtos = produtoCustomRepository.getProduto(nomeproduto+"%");
        mv.addObject("produtos", produtos);
        return mv;
    }


    @GetMapping("/create")
    public ModelAndView BuscaCadastro(Produto produto) {
        ModelAndView mv = new ModelAndView("produtos/create");
        return mv;
    }

    @PostMapping("/create")
    public String create(Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView BuscaEdit(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("produtos/edit");
        mv.addObject("prod", produtoRepository.getOne(id));
        return mv;
    }

    @PutMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Produto produto) {
        Produto prod = produtoRepository.getOne(id);
        if (prod != null) {
            prod.setId(prod.getId());
            prod.setQuantidade(produto.getQuantidade());
            prod.setValor(prod.getValor());
            prod.setImg1(prod.getImg1());
            prod.setImg2(prod.getImg2());
            prod.setImg3(prod.getImg3());//100% algodão
            prod.setModelo(prod.getModelo());
            prod.setDescricao(prod.getDescricao());
            prod.setAltura(prod.getAltura());
            prod.setBusto(prod.getBusto());
            prod.setCintura(prod.getCintura());
            prod.setQuadril(prod.getQuadril());
            prod.setTamanho(prod.getTamanho());
            prod.setCategoria(prod.getCategoria());
            produtoRepository.save(prod);
        }
        return "redirect:/produtos/listar";
    }

    @PutMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Integer id, Produto produto){
        Produto prod = produtoRepository.getOne(id);
        if (prod != null) {
            prod.setId(prod.getId());
            prod.setQuantidade(produto.getQuantidade());
            prod.setValor(prod.getValor());
            prod.setImg1(prod.getImg1());
            prod.setImg2(prod.getImg2());
            prod.setImg3(prod.getImg3());//100% algodão
            prod.setModelo(prod.getModelo());
            prod.setDescricao(prod.getDescricao());
            prod.setAltura(prod.getAltura());
            prod.setBusto(prod.getBusto());
            prod.setCintura(prod.getCintura());
            prod.setQuadril(prod.getQuadril());
            prod.setTamanho(prod.getTamanho());
            prod.setCategoria(prod.getCategoria());
            produtoRepository.save(prod);
        }
        return "redirect:/produtos/estoque?nomeproduto=";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id){
        produtoRepository.deleteById(id);
        return "redirect:/produtos/estoque?nomeproduto=";
    }

    @GetMapping("/categoria")
    public ModelAndView buscaPorCategoria(String categoria, Produto produto){
        ModelAndView mv = new ModelAndView("/produtos/lista");
        System.out.println(categoria);
        List<Produto> prodCategoria = produtoCustomRepository.getProdutoPorCategoria(categoria);
        mv.addObject("lista", prodCategoria);
        return mv;
    }

    @GetMapping("/genero")
    public ModelAndView buscaPorGenero(String sexo, Produto produto){
        ModelAndView mv = new ModelAndView("/produtos/lista");
        List<Produto> prodGenero = produtoCustomRepository.getProdutoPorGenero(sexo);
        mv.addObject("lista", prodGenero);
        return mv;
    }

    @GetMapping("/tamanho")
    public ModelAndView buscaPorTamanho(String tamanho, Produto produto){
        ModelAndView mv = new ModelAndView("/produtos/lista");
        List<Produto> prodTamanho = produtoCustomRepository.getProdutoPorTamanho(tamanho);
        mv.addObject("lista", prodTamanho);
        return mv;
    }

}
