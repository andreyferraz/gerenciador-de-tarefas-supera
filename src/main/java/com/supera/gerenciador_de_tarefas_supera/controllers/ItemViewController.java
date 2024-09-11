package com.supera.gerenciador_de_tarefas_supera.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.supera.gerenciador_de_tarefas_supera.models.Item;
import com.supera.gerenciador_de_tarefas_supera.services.ItemService;
import com.supera.gerenciador_de_tarefas_supera.services.ListaService;

@Controller
@RequestMapping("/itens")
public class ItemViewController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ListaService listaService;

    // Exibe a lista de itens na página HTML
    @GetMapping
    public String listarItens(Model model) {
        List<Item> itens = itemService.recuperarTodosItens();
        model.addAttribute("itens", itens);
        return "itens/listar";
    }

    // Exibe o formulário para criar um novo item
    @GetMapping("/novo")
    public String exibirFormularioCriacao(@RequestParam(value = "listaId", required = false) UUID listaId,
            Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("listas", listaService.recuperarTodasListas());
        if (listaId != null) {
            model.addAttribute("listaSelecionada", listaService.recuperarLista(listaId));
        }
        return "itens/formulario";
    }

    // Processa o formulário de criação de um novo item
    @PostMapping("/novo")
    public String criarItem(@ModelAttribute Item item, @RequestParam("listaId") UUID listaId) {
        itemService.criarItem(listaId, item);
        return "redirect:/itens";
    }

    // Exibe o formulário para editar um item existente
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable UUID id, Model model) {
        Item item = itemService.recuperarItem(id);
        model.addAttribute("item", item);
        model.addAttribute("listas", listaService.recuperarTodasListas());
        return "itens/formulario";
    }

    // Processa o formulário de edição
    @PostMapping("/editar/{id}")
    public String editarItem(@PathVariable UUID id, @ModelAttribute Item itemAtualizado,
            @RequestParam("listaId") UUID listaId) {
        itemAtualizado.setLista(listaService.recuperarLista(listaId));
        itemService.editarItem(id, itemAtualizado);
        return "redirect:/itens";
    }

    // Deleta um item pelo ID
    @GetMapping("/deletar/{id}")
    public String deletarItem(@PathVariable UUID id) {
        itemService.removerItem(id);
        return "redirect:/itens";
    }
}
