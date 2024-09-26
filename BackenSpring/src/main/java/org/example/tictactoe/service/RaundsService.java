package org.example.tictactoe.service;

import org.example.tictactoe.entity.Raunds;
import org.example.tictactoe.repository.RaundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaundsService {

    @Autowired
    private RaundsRepository raundsRepository;

    // Crear un nuevo match (mejor de 3, 5, etc.)
    public Raunds createMatch(String playerX, String playerO, int totalRounds) {
        Raunds match = new Raunds();
        match.setPlayerX(playerX);
        match.setPlayerO(playerO);
        match.setTotalRounds(totalRounds);
        match.setRoundsWonByPlayerX(0);
        match.setRoundsWonByPlayerO(0);
        match.setStatus(Raunds.MatchStatus.JUGANDO);
        return raundsRepository.save(match);
    }

    // Obtener un match por su ID
    public Raunds getMatchById(Long id) {
        return raundsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match no encontrado"));
    }

    // Actualizar el puntaje de las rondas ganadas por cada jugador y determinar si el match ha finalizado
    public Raunds updateMatchWinner(Long matchId, String winner, boolean isCancelled) throws Exception {
        Raunds match = getMatchById(matchId);

        // Verificar si el partido ha sido anulado
        if (isCancelled) {
            match.setMatchWinner(null);  // No hay ganador si el match es anulado
            match.setStatus(Raunds.MatchStatus.ANULADO);  // Cambiar el estado a "ANULADO"
            match.setFinished(true);  // Marcamos el partido como terminado
            return raundsRepository.save(match);
        }

        // Actualizar las rondas ganadas según el ganador de la ronda
        if (winner.equals(match.getPlayerX())) {
            match.setRoundsWonByPlayerX(match.getRoundsWonByPlayerX() + 1);
        } else if (winner.equals(match.getPlayerO())) {
            match.setRoundsWonByPlayerO(match.getRoundsWonByPlayerO() + 1);
        }

        // Verificar si alguien ha ganado el match (mayoría de rondas ganadas)
        if (match.getRoundsWonByPlayerX() > match.getTotalRounds() / 2) {
            match.setMatchWinner(match.getPlayerX());
            match.setStatus(Raunds.MatchStatus.FINALIZADO);
            match.setFinished(true);  // El match ha terminado
        } else if (match.getRoundsWonByPlayerO() > match.getTotalRounds() / 2) {
            match.setMatchWinner(match.getPlayerO());
            match.setStatus(Raunds.MatchStatus.FINALIZADO);
            match.setFinished(true);  // El match ha terminado
        }

        return raundsRepository.save(match);
    }

    // Eliminar un match por su ID
    public void deleteMatch(Long id) {
        Raunds match = getMatchById(id);
        raundsRepository.delete(match);
    }

    // Obtener todos los matches de un jugador
    public List<Raunds> getMatchesByPlayer(String playerName) {
        return raundsRepository.findByPlayerXOrPlayerO(playerName, playerName);
    }
}
