package com.example.demo.controller;

import com.example.demo.infra.FileSever;
import com.example.demo.model.Produto;
import com.example.demo.respository.ProdutoCustomRepository;
import com.example.demo.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private FileSever fileSever;

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
    public String create(MultipartFile sumario1, MultipartFile sumario2,MultipartFile sumario3, Produto produto) {
        String path1 = fileSever.write("arquivos-sumario", sumario1);
        String path2 = fileSever.write("arquivos-sumario", sumario2);
        String path3 = fileSever.write("arquivos-sumario", sumario3);
        produto.setImg1(path1);
        produto.setImg2(path2);
        produto.setImg3(path3);
        produtoRepository.save(produto);
        return "redirect:/produtos/confirmar/"+produto.getId();
    }

    @GetMapping("/confirmar/{id}")
    public ModelAndView ConfirmaInclusão(@PathVariable("id") Integer id, Produto produto){
        ModelAndView mv = new ModelAndView("/administrador/ok");
        mv.addObject("prod", produtoRepository.getOne(id));
        return mv;
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
