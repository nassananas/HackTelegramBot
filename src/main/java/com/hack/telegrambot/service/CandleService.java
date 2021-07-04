package com.hack.telegrambot.service;

import com.hack.telegrambot.model.Candle;
import com.hack.telegrambot.repo.CandleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandleService {
    public CandleRepository candleRepository;

    public CandleService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    public Candle findByFigi(String figi) { return candleRepository.findByFigi(figi); }
    public void addCandle(Candle candle) { candleRepository.save(candle); }
    public void updateCandle(Candle candle) { candleRepository.save(candle); }
    public List<Candle> findAllCandles() { return candleRepository.findAllCandles();}
}
