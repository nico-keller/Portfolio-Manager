package com.example.portfoliomanager.controller;

import com.example.portfoliomanager.model.StockTrade;
import com.example.portfoliomanager.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocktrades")
public class StockTradeController {

    @Autowired
    private StockTradeService stockTradeService;

    @GetMapping(value = "/findall", produces = "application/json")
    public ResponseEntity<List<StockTrade>> getAllStockTrades() {
        return new ResponseEntity<>(stockTradeService.findAllStockTrades(), HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<StockTrade> getStockTradeById(@PathVariable long id) {
        StockTrade stockTrade = stockTradeService.findStockTradeById(id);
        if (stockTrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockTrade, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<StockTrade> addStockTrade(@RequestBody StockTrade stockTrade) {
        stockTradeService.addStockTrade(stockTrade);
        return new ResponseEntity<>(stockTrade, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<StockTrade> updateStockTrade(@PathVariable long id, @RequestBody StockTrade stockTradeDetails) {
        StockTrade stockTrade = stockTradeService.findStockTradeById(id);
        if (stockTrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stockTradeService.updateStockTrade(stockTradeDetails); // Assuming stockTradeDetails includes the ID and it matches the path variable
        return new ResponseEntity<>(stockTradeDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteStockTrade(@PathVariable long id) {
        StockTrade stockTrade = stockTradeService.findStockTradeById(id);
        if (stockTrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stockTradeService.deleteStockTradeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteall")
    public ResponseEntity<Void> deleteAllStockTrades() {
        stockTradeService.deleteAllStockTrades();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
