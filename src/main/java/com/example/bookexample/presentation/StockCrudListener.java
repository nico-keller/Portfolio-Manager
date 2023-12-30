package com.example.bookexample.presentation;

import com.example.bookexample.model.Stock;
import com.example.bookexample.model.StockTrade;
import com.example.bookexample.service.StockService;
import com.example.bookexample.service.StockTradeService;
import com.example.bookexample.viewmodel.StockViewModel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringComponent
public class StockCrudListener implements CrudListener<StockViewModel> {


    private final StockService stockService;

    private final StockTradeService stockTradeService;

    public StockCrudListener(StockService stockService, StockTradeService stockTradeService) {
        this.stockService = stockService;
        this.stockTradeService = stockTradeService;
    }

    @Override
    public Collection<StockViewModel> findAll() {
        List<Stock> stockList = stockService.findAllStocks();
        List<StockViewModel> stockViewModelList = new ArrayList<>();
        for (Stock stock : stockList) {
            stockViewModelList.add(new StockViewModel(stock.getTickerSymbol(), stock.getCompanyName(), stock.getCurrentPrice(), stock.getSector()));
        }
        return stockViewModelList;
    }

    @Override
    public StockViewModel add(StockViewModel stock) {
        List<Stock> stockList = stockService.findAllStocks();
        boolean exists = false;
        for (Stock s : stockList) {
            if (s.getTickerSymbol().equals(stock.getTickerSymbol())) {
                Notification.show("Stock with this ticker symbol already exists!", 3000, Notification.Position.MIDDLE);
                exists = true;
            }
        }
        if (!exists) {
            stockService.addStock(new Stock(stock.getTickerSymbol(), stock.getCompanyName(), stock.getCurrentPrice(), stock.getSector()));
        }
        return stock;
    }

    @Override
    public StockViewModel update(StockViewModel stock) {
        List<Stock> stockList = stockService.findAllStocks();
        for (Stock s : stockList) {
            if (s.getTickerSymbol().equals(stock.getTickerSymbol())) {
                s.setCompanyName(stock.getCompanyName());
                s.setCurrentPrice(stock.getCurrentPrice());
                s.setSector(stock.getSector());
                stockService.updateStock(s);
            }
        }
        return stock;
    }

    @Override
    public void delete(StockViewModel stock) {
        List<Stock> stockList = stockService.findAllStocks();
        for (Stock s : stockList) {
            if (s.getTickerSymbol().equals(stock.getTickerSymbol())) {
                stockService.deleteStock(s);
            }
        }
    }
}
