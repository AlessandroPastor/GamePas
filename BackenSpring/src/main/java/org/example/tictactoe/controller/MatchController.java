package org.example.tictactoe.controller;

import org.example.tictactoe.entity.Match;
import org.example.tictactoe.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")  // Todas las rutas para el match estarán bajo /matches
public class MatchController {

    @Autowired
    private MatchService matchService;

    // Crear un nuevo Match
    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(
            @RequestParam String playerX,
            @RequestParam String playerO,
            @RequestParam int totalRounds) {
        Match match = matchService.createMatch(playerX, playerO, totalRounds);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    // Obtener un Match por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    // Actualizar el ganador de una ronda en un Match
    @PutMapping("/{id}/winner")
    public ResponseEntity<Match> updateMatchWinner(
            @PathVariable Long id,
            @RequestParam String winner) throws Exception {
        Match updatedMatch = matchService.updateMatchWinner(id, winner);
        return ResponseEntity.ok(updatedMatch);
    }

    // Eliminar un Match por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los Matches jugados por un jugador
    @GetMapping("/player/{playerName}")
    public ResponseEntity<List<Match>> getMatchesByPlayer(@PathVariable String playerName) {
        List<Match> matches = matchService.getMatchesByPlayer(playerName);
        return ResponseEntity.ok(matches);
    }


}
