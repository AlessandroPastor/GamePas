package org.example.tictactoe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerX; // Nombre del jugador X
    private String playerO; // Nombre del jugador O
    private String board; // Estado del tablero (XOXOXO___)
    private boolean isFinished; // Indica si la ronda ha terminado
    private int totalRounds; // Total de rondas en el match (por ejemplo, 3 o 5)
    private int roundsWonByPlayerX; // Número de rondas ganadas por playerX
    private int roundsWonByPlayerO; // Número de rondas ganadas por playerO
    private String matchWinner; // Quién ganó el match completo (playerX o playerO)
    @Enumerated(EnumType.STRING)
    private MatchStatus status; // Estado del match: JUGANDO, FINALIZADO
    public enum MatchStatus {
        JUGANDO,
        FINALIZADO
    }
}

