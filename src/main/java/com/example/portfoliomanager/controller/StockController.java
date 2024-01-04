package com.example.portfoliomanager.controller;

import com.example.portfoliomanager.model.Stock;
import com.example.portfoliomanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/findall", produces = "application/json")
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(stockService.findAllStocks(), HttpStatus.OK);
    }

    @GetMapping(value = "/findbyticker/{tickerSymbol}", produces = "application/json")
    public ResponseEntity<Stock> getStockByTickerSymbol(@PathVariable String tickerSymbol) {
        Stock stock = stockService.findStockByTickerSymbol(tickerSymbol);
        if (stock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{tickerSymbol}", consumes = "application/json")
    public ResponseEntity<Stock> updateStock(@PathVariable String tickerSymbol, @RequestBody Stock stockDetails) {
        Stock stock = stockService.findStockByTickerSymbol(tickerSymbol);
        if (stock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stockService.updateStock(stockDetails); // Assuming stockDetails includes the ticker symbol and it matches the path variable
        return new ResponseEntity<>(stockDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{tickerSymbol}")
    public ResponseEntity<Void> deleteStock(@PathVariable String tickerSymbol) {
        Stock stock = stockService.findStockByTickerSymbol(tickerSymbol);
        if (stock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stockService.deleteStock(stock);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteall")
    public ResponseEntity<Void> deleteAllStocks() {
        stockService.deleteAllStocks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
