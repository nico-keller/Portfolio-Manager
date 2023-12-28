package com.example.bookexample.presentation;

import com.example.bookexample.model.StockTrade;
import com.example.bookexample.service.StockTradeService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;


@SpringComponent
public class StockTradeCrudListener implements CrudListener<StockTrade> {

    private final StockTradeService stockTradeService;

    public StockTradeCrudListener(StockTradeService stockTradeService) {
        this.stockTradeService = stockTradeService;
    }

    @Override
    public Collection<StockTrade> findAll() {
        return stockTradeService.findAllStockTraders();
    }

    @Override
    public StockTrade add(StockTrade stockTrade) {
        stockTradeService.addStockTrader(stockTrade);
        return stockTrade;
    }

    @Override
    public StockTrade update(StockTrade stockTrade) {
        stockTradeService.updateStockTrader(stockTrade);
        return stockTrade;
    }

    @Override
    public void delete(StockTrade stockTrade) {
        stockTradeService.deleteStockTraderById(stockTrade.getTransactionId());
    }
}
