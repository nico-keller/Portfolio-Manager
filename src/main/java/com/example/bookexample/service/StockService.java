package com.example.bookexample.service;

import com.example.bookexample.model.Stock;

import java.util.ArrayList;
import java.util.List;

public interface StockService {
    List<Stock> findAllStocks();
    Stock findStockByTickerSymbol (String tickerSymbol);
    void addStock(Stock S);
    void updateStock (Stock S);
    void deleteStock(Stock S);
    void deleteStockByTickerSymbol(String tickerSymbol);
    void deleteAllStocks();
}
