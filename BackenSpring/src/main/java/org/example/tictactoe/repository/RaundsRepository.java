package org.example.tictactoe.repository;

import org.example.tictactoe.entity.Raunds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaundsRepository extends JpaRepository<Raunds, Long> {
    List<Raunds> findByPlayerXOrPlayerO(String playerX, String playerO); // Buscar partidos por jugador
}
