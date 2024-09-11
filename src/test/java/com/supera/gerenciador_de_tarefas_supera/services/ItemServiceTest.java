package com.supera.gerenciador_de_tarefas_supera.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import com.supera.gerenciador_de_tarefas_supera.enums.Estado;
import com.supera.gerenciador_de_tarefas_supera.models.Item;
import com.supera.gerenciador_de_tarefas_supera.models.Lista;
import com.supera.gerenciador_de_tarefas_supera.repository.ItemRepository;
import com.supera.gerenciador_de_tarefas_supera.repository.ListaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

    private ItemService itemService;

    private ItemRepository itemRepository;
    private ListaRepository listaRepository;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        listaRepository = mock(ListaRepository.class);
        itemService = new ItemService();
        itemService.itemRepository = itemRepository;
        itemService.listaRepository = listaRepository;
    }

    @Test
    void testCriarItem() {
        UUID listaId = UUID.randomUUID();
        Lista lista = new Lista();
        Item item = new Item();
        when(listaRepository.findById(listaId)).thenReturn(Optional.of(lista));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        Item resultado = itemService.criarItem(listaId, item);

        verify(listaRepository).findById(listaId);
        verify(itemRepository).save(item);
        assertNotNull(resultado);
    }

    @Test
    void testEditarItem() {
        UUID id = UUID.randomUUID();
        Item itemExistente = new Item();
        Item itemAtualizado = new Item();
        when(itemRepository.findById(id)).thenReturn(Optional.of(itemExistente));
        when(itemRepository.save(any(Item.class))).thenReturn(itemAtualizado);

        Item resultado = itemService.editarItem(id, itemAtualizado);

        verify(itemRepository).findById(id);
        verify(itemRepository).save(itemExistente);
        assertEquals(itemAtualizado, resultado);
    }

    @Test
    void testRemoverItem() {
        UUID id = UUID.randomUUID();

        itemService.removerItem(id);

        verify(itemRepository).deleteById(id);
    }

    @Test
    void testAtualizarEstado() {
        UUID id = UUID.randomUUID();
        Item item = new Item();
        Estado novoEstado = Estado.CONCLUIDO; // Certifique-se de que esse valor está definido na enumeração
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);
    
        Item resultado = itemService.atualizarEstado(id, novoEstado);
    
        verify(itemRepository).findById(id);
        verify(itemRepository).save(item);
        assertEquals(novoEstado, resultado.getEstado()); // Verifica se o estado foi atualizado corretamente
    }
    

    @Test
    void testPriorizarItem() {
        UUID id = UUID.randomUUID();
        Item item = new Item();
        Integer novaPrioridade = 1;
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        Item resultado = itemService.priorizarItem(id, novaPrioridade);

        verify(itemRepository).findById(id);
        verify(itemRepository).save(item);
        assertEquals(novaPrioridade, resultado.getPrioridade());
    }

    @Test
    void testRecuperarItem() {
        UUID id = UUID.randomUUID();
        Item item = new Item();
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item resultado = itemService.recuperarItem(id);

        verify(itemRepository).findById(id);
        assertEquals(item, resultado);
    }

    @Test
    void testRecuperarTodosItens() {
        itemService.recuperarTodosItens();

        verify(itemRepository).findAll();
    }
}
