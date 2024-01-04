package com.example.portfoliomanager.service;

import com.example.portfoliomanager.model.Stock;
import com.example.portfoliomanager.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> findAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        stockRepository.findAll().forEach(stocks::add);
        return stocks;
    }

    @Override
    public Stock findStockByTickerSymbol(String tickerSymbol) {
        return stockRepository.findByTickerSymbol(tickerSymbol);
    }

    @Override
    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void updateStock(Stock stock) {
        if (stockRepository.existsByTickerSymbol(stock.getTickerSymbol())) {
            stockRepository.save(stock);
        } else {
            throw new RuntimeException("Stock not found");
        }
    }

    @Override
    public void deleteStockByTickerSymbol(String tickerSymbol) {
        Stock stock = stockRepository.findByTickerSymbol(tickerSymbol);
        if (stock != null) {
            stockRepository.delete(stock);
        }
    }

    @Override
    public void deleteAllStocks() {
        stockRepository.deleteAll();
    }

    @Override
    public void deleteStock(Stock stock){
        stockRepository.delete(stock);
    }
}
