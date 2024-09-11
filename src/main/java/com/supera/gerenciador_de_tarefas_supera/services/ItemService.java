package com.supera.gerenciador_de_tarefas_supera.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.gerenciador_de_tarefas_supera.enums.Estado;
import com.supera.gerenciador_de_tarefas_supera.models.Item;
import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.repository.ItemRepository;
import com.supera.gerenciador_de_tarefas_supera.repository.ListaRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ListaRepository listaRepository;
    
    @Transactional
    public Item criarItem(UUID listaId, Item item){
        Lista lista = listaRepository.findById(listaId)
        .orElseThrow(() -> new RuntimeException("Lista não encontrada com o ID: " + listaId));
        item.setLista(lista);
        return itemRepository.save(item);
    }

    @Transactional
    public Item editarItem(UUID id, Item itemAtualizado){
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()){
            Item itemExistente = itemOptional.get();
            itemExistente.setTitulo(itemAtualizado.getTitulo());
            itemExistente.setDescricao(itemAtualizado.getDescricao());
            itemExistente.setEstado(itemAtualizado.getEstado());
            itemExistente.setPrioridade(itemAtualizado.getPrioridade());
            return itemRepository.save(itemExistente);
        }
        throw new RuntimeException("Item não encontrado com o ID: " + id);
    }

    @Transactional
    public void removerItem(UUID id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item atualizarEstado(UUID id, Estado novoEstado) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));
        item.setEstado(novoEstado);
        return itemRepository.save(item);
    }

    @Transactional
    public Item priorizarItem(UUID id, Integer novaPrioridade) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));
        item.setPrioridade(novaPrioridade);
        return itemRepository.save(item);
    }

    public Item recuperarItem(UUID id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));
    }

    public List<Item> recuperarTodosItens() {
        return itemRepository.findAll();
    }
}
