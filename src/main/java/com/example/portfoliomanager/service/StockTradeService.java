package com.example.portfoliomanager.service;

import com.example.portfoliomanager.model.StockTrade;

import java.util.List;

public interface StockTradeService {
    StockTrade findStockTradeById(long traderId);
    List<StockTrade> findAllStockTrades();
    void addStockTrade(StockTrade stockTrader);
    void updateStockTrade(StockTrade stockTrader);
    void deleteStockTradeById(long traderId);

    void deleteAllStockTrades();
}
