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
    public StockTrade findStockTraderById(long traderId) {
        return stockTradeRepository.findById(traderId)
                .orElseThrow(() -> new RuntimeException("StockTrade not found"));
    }

    @Override
    public List<StockTrade> findAllStockTraders() {
        return (List<StockTrade>) stockTradeRepository.findAll();
    }

    @Override
    public void addStockTrader(StockTrade stockTrade) {
        stockTradeRepository.save(stockTrade);
    }

    @Override
    public void updateStockTrader(StockTrade stockTrade) {
        if (!stockTradeRepository.existsById(stockTrade.getTransactionId())) {
            throw new RuntimeException("StockTrade not found");
        }
        stockTradeRepository.save(stockTrade);
    }

    @Override
    public void deleteStockTraderById(long traderId) {
        stockTradeRepository.deleteById(traderId);
    }
}
