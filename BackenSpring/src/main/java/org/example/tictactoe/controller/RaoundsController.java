package org.example.tictactoe.controller;

import org.example.tictactoe.entity.Raunds;
import org.example.tictactoe.service.RaundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raunds")
public class RaoundsController {

    @Autowired
    private RaundsService raundsService;

    // Crear un nuevo Match
    @PostMapping("/create")
    public ResponseEntity<Raunds> createMatch(
            @RequestParam String playerX,
            @RequestParam String playerO,
            @RequestParam int totalRounds) {
        Raunds match = raundsService.createMatch(playerX, playerO, totalRounds);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    // Obtener un Match por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Raunds> getMatch(@PathVariable Long id) {
        Raunds match = raundsService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    // Actualizar el ganador de una ronda en un Match o anular el Match
    @PutMapping("/{id}/winner")
    public ResponseEntity<Raunds> updateMatchWinner(
            @PathVariable Long id,
            @RequestParam String winner,
            @RequestParam(required = false, defaultValue = "false") boolean isCancelled) throws Exception {
        // Llamamos al servicio, pasando el parámetro isCancelled
        Raunds updatedMatch = raundsService.updateMatchWinner(id, winner, isCancelled);
        return ResponseEntity.ok(updatedMatch);
    }

    // Eliminar un Match por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        raundsService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los Matches jugados por un jugador
    @GetMapping("/player/{playerName}")
    public ResponseEntity<List<Raunds>> getMatchesByPlayer(@PathVariable String playerName) {
        List<Raunds> matches = raundsService.getMatchesByPlayer(playerName);
        return ResponseEntity.ok(matches);
    }
}
