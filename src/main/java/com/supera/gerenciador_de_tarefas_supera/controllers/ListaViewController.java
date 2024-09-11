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

import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.services.ListaService;

@Controller
@RequestMapping("/listas")
public class ListaViewController {
    @Autowired
    private ListaService listaService;

    // Exibir todas as listas
    @GetMapping
    public String listarListas(Model model) {
        List<Lista> listas = listaService.recuperarTodasListas();
        model.addAttribute("listas", listas);
        return "listas/lista";
    }

    // Exibir formulário para criar uma nova lista
    @GetMapping("/nova")
    public String exibirFormularioNovaLista(Model model) {
        model.addAttribute("lista", new Lista());
        return "listas/formulario";
    }

    // Processar criação de nova lista
    @PostMapping("/salvar")
    public String salvarLista(@ModelAttribute("lista") Lista lista) {
        listaService.criarLista(lista);
        return "redirect:/listas";
    }

    // Exibir formulário para editar uma lista existente
    @GetMapping("/editar/{id}")
    public String exibirFormularioEditarLista(@PathVariable UUID id, Model model) {
        Lista lista = listaService.recuperarLista(id);
        model.addAttribute("lista", lista);
        return "listas/formulario";
    }

    // Processar atualização da lista
    @PostMapping("/atualizar/{id}")
    public String atualizarLista(@PathVariable UUID id, @ModelAttribute("lista") Lista listaAtualizada) {
        listaService.editarLista(id, listaAtualizada);
        return "redirect:/listas";
    }

    // Remover uma lista
    @GetMapping("/remover/{id}")
    public String removerLista(@PathVariable UUID id) {
        listaService.removerLista(id);
        return "redirect:/listas";
    }
}
