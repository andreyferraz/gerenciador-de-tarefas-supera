package com.supera.gerenciador_de_tarefas_supera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.gerenciador_de_tarefas_supera.models.Lista;

import java.util.UUID;
public interface ListaRepository extends JpaRepository<Lista, UUID> {

}
