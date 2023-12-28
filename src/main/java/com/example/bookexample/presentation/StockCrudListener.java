package com.example.bookexample.presentation;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Stock;
import com.example.bookexample.service.InvestorService;
import com.example.bookexample.service.StockService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;



@SpringComponent
public class StockCrudListener implements CrudListener<Stock> {


    private final StockService stockService;

    public StockCrudListener(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public Collection<Stock> findAll() {
        return stockService.findAllStocks();
    }

    @Override
    public Stock add(Stock stock) {
        stockService.addStock(stock);
        return stock;
    }

    @Override
    public Stock update(Stock stock) {
        stockService.updateStock(stock);
        return stock;
    }

    @Override
    public void delete(Stock stock) {
        stockService.deleteStockByTickerSymbol(stock.getTickerSymbol());
    }
}
