package org.example.tictactoe.repository;

import org.example.tictactoe.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MatchRepository extends JpaRepository<Match, Long> {
}
