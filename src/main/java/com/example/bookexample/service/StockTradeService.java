package com.example.bookexample.service;

import com.example.bookexample.model.StockTrade;

import java.util.List;

public interface StockTradeService {
    StockTrade findStockTraderById(long traderId);
    List<StockTrade> findAllStockTraders();
    void addStockTrader(StockTrade stockTrader);
    void updateStockTrader(StockTrade stockTrader);
    void deleteStockTraderById(long traderId);
}
