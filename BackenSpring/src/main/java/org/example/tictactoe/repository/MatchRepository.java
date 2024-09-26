package org.example.tictactoe.repository;

import org.example.tictactoe.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // Métodos personalizados si es necesario
    List<Match> findByPlayerXOrPlayerO(String playerX, String playerO); // Buscar partidos por jugador
}
