package com.supera.gerenciador_de_tarefas_supera.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.gerenciador_de_tarefas_supera.models.Item;
public interface ItemRepository extends JpaRepository<Item, UUID> {

}
