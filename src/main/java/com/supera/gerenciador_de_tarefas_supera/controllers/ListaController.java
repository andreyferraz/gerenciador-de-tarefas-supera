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

import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.services.ListaService;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @PostMapping
    public ResponseEntity<Lista> criarLista(@RequestBody Lista lista) {
        Lista novaLista = listaService.criarLista(lista);
        return new ResponseEntity<>(novaLista, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> recuperarLista(@PathVariable UUID id) {
        Lista lista = listaService.recuperarLista(id);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Lista>> recuperarTodasListas() {
        List<Lista> listas = listaService.recuperarTodasListas();
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lista> editarLista(@PathVariable UUID id, @RequestBody Lista listaAtualizada) {
        Lista lista = listaService.editarLista(id, listaAtualizada);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLista(@PathVariable UUID id) {
        listaService.removerLista(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
