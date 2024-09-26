package org.example.tictactoe.controller;

import org.example.tictactoe.entity.Game;
import org.example.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Crear un nuevo juego
    @PostMapping
    public ResponseEntity<Game> createNewGame(@RequestBody Game game) {
        game.setStatus(Game.GameStatus.JUGANDO);  // Establecer el estado inicial como "JUGANDO"
        Game newGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    // Obtener un juego por ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        try {
            Game game = gameService.getGameById(id);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Manejo de excepciones si el juego no se encuentra
        }
    }

    // Realizar un movimiento en el juego
    @PutMapping("/{id}/move")
    public ResponseEntity<Game> makeMove(@PathVariable Long id, @RequestParam int position, @RequestParam String player) {
        try {
            Game updatedGame = gameService.makeMove(id, position, player);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);  // Si hay un error, retorna 400 Bad Request
        }
    }

    // Eliminar un juego por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.noContent().build();  // Retorna 204 No Content al eliminar exitosamente
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Manejo de excepciones si el juego no se encuentra
        }
    }

    // Método para actualizar el ganador o anular el juego
    @PutMapping("/{id}/winner")
    public ResponseEntity<Game> updateWinner(
            @PathVariable Long id,
            @RequestParam String winner,
            @RequestParam(required = false, defaultValue = "false") boolean isCancelled) {
        try {
            Game updatedGame = gameService.updateWinner(id, winner, isCancelled);
            return ResponseEntity.ok(updatedGame);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
