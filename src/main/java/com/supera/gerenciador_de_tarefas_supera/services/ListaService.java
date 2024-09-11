package com.supera.gerenciador_de_tarefas_supera.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.repository.ListaRepository;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Transactional
    public Lista criarLista(Lista lista) {
        return listaRepository.save(lista);
    }

    @Transactional
    public Lista editarLista(UUID id, Lista listaAtualizada) {
        Optional<Lista> listaOptional = listaRepository.findById(id);
        if (listaOptional.isPresent()) {
            Lista listaExistente = listaOptional.get();
            listaExistente.setNome(listaAtualizada.getNome());
            return listaRepository.save(listaExistente);
        }
        throw new RuntimeException("Lista não encontrada com o ID: " + id);
    }

    @Transactional
    public void removerLista(UUID id) {
        listaRepository.deleteById(id);
    }

    public Lista recuperarLista(UUID id) {
        return listaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada com o ID: " + id));
    }

    public List<Lista> recuperarTodasListas(){
        return listaRepository.findAll();
    }

}
