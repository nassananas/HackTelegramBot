package com.hack.telegrambot.repo;

import com.hack.telegrambot.model.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Integer> {
    @Query("SELECT c FROM Candle c ORDER BY c.id DESC")
    List<Candle> findAllCandles();

    //List<Candle> findAllBy

    Candle findByFigi(String figi);

}
