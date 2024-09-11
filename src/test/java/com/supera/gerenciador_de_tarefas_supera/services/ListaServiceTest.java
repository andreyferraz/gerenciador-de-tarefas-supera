package com.supera.gerenciador_de_tarefas_supera.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.repository.ListaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ListaServiceTest {

    private ListaService listaService;

    private ListaRepository listaRepository;

    @BeforeEach
    void setUp() {
        listaRepository = mock(ListaRepository.class);
        listaService = new ListaService();
        listaService.listaRepository = listaRepository;
    }

    @Test
    void testCriarLista() {
        Lista lista = new Lista();
        when(listaRepository.save(any(Lista.class))).thenReturn(lista);

        Lista resultado = listaService.criarLista(lista);

        verify(listaRepository).save(lista);
        assertNotNull(resultado);
    }

    @Test
    void testEditarLista() {
        UUID id = UUID.randomUUID();
        Lista listaExistente = new Lista();
        Lista listaAtualizada = new Lista();
        when(listaRepository.findById(id)).thenReturn(Optional.of(listaExistente));
        when(listaRepository.save(any(Lista.class))).thenReturn(listaAtualizada);

        Lista resultado = listaService.editarLista(id, listaAtualizada);

        verify(listaRepository).findById(id);
        verify(listaRepository).save(listaExistente);
        assertEquals(listaAtualizada, resultado);
    }

    @Test
    void testRemoverLista() {
        UUID id = UUID.randomUUID();

        listaService.removerLista(id);

        verify(listaRepository).deleteById(id);
    }

    @Test
    void testRecuperarLista() {
        UUID id = UUID.randomUUID();
        Lista lista = new Lista();
        when(listaRepository.findById(id)).thenReturn(Optional.of(lista));

        Lista resultado = listaService.recuperarLista(id);

        verify(listaRepository).findById(id);
        assertEquals(lista, resultado);
    }

    @Test
    void testRecuperarTodasListas() {
        List<Lista> listas = List.of(new Lista(), new Lista());
        when(listaRepository.findAll()).thenReturn(listas);

        List<Lista> resultado = listaService.recuperarTodasListas();

        verify(listaRepository).findAll();
        assertEquals(listas, resultado);
    }
}
