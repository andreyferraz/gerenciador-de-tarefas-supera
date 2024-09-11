package com.supera.gerenciador_de_tarefas_supera.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supera.gerenciador_de_tarefas_supera.enums.Estado;
import com.supera.gerenciador_de_tarefas_supera.models.Item;
import com.supera.gerenciador_de_tarefas_supera.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class ItemController {
     @Autowired
    private ItemService itemService;

    @PostMapping("/lista/{listaId}")
    public ResponseEntity<Item> criarItem(@PathVariable UUID listaId, @RequestBody Item item) {
        Item novoItem = itemService.criarItem(listaId, item);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> recuperarItem(@PathVariable UUID id) {
        Item item = itemService.recuperarItem(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> recuperarTodosItens() {
        List<Item> itens = itemService.recuperarTodosItens();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> editarItem(@PathVariable UUID id, @RequestBody Item itemAtualizado) {
        Item item = itemService.editarItem(id, itemAtualizado);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItem(@PathVariable UUID id) {
        itemService.removerItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Item> atualizarEstado(@PathVariable UUID id, @RequestBody Estado novoEstado) {
        Item item = itemService.atualizarEstado(id, novoEstado);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}/prioridade")
    public ResponseEntity<Item> priorizarItem(@PathVariable UUID id, @RequestBody Integer novaPrioridade) {
        Item item = itemService.priorizarItem(id, novaPrioridade);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
