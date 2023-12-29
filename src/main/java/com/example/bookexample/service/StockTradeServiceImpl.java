package com.example.bookexample.service;

import com.example.bookexample.model.StockTrade;
import com.example.bookexample.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockTradeServiceImpl implements StockTradeService {

    private final StockTradeRepository stockTradeRepository;

    @Autowired
    public StockTradeServiceImpl(StockTradeRepository stockTradeRepository) {
        this.stockTradeRepository = stockTradeRepository;
    }

    @Override
    public StockTrade findStockTradeById(long traderId) {
        return stockTradeRepository.findById(traderId)
                .orElseThrow(() -> new RuntimeException("StockTrade not found"));
    }

    @Override
    public List<StockTrade> findAllStockTrades() {
        return (List<StockTrade>) stockTradeRepository.findAll();
    }

    @Override
    public void addStockTrade(StockTrade stockTrade) {
        stockTradeRepository.save(stockTrade);
    }

    @Override
    public void updateStockTrade(StockTrade stockTrade) {
        if (!stockTradeRepository.existsById(stockTrade.getTransactionId())) {
            throw new RuntimeException("StockTrade not found");
        }
        stockTradeRepository.save(stockTrade);
    }

    @Override
    public void deleteStockTradeById(long traderId) {
        stockTradeRepository.deleteById(traderId);
    }

    @Override
    public void deleteAllStockTrades(){stockTradeRepository.deleteAll();};
}
