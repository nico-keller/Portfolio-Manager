package com.example.bookexample.controller;

import com.example.bookexample.model.Portfolio;
import com.example.bookexample.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(value = "/findall", produces = "application/json")
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        return new ResponseEntity<>(portfolioService.findAllPortfolios(), HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable long id) {
        Portfolio portfolio = portfolioService.findPortfolioById(id);
        if (portfolio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Portfolio> addPortfolio(@RequestBody Portfolio portfolio) {
        portfolioService.addPortfolio(portfolio);
        return new ResponseEntity<>(portfolio, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable long id, @RequestBody Portfolio portfolioDetails) {
        Portfolio portfolio = portfolioService.findPortfolioById(id);
        if (portfolio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        portfolioService.updatePortfolio(portfolioDetails); // Assuming portfolioDetails includes the ID and it matches the path variable
        return new ResponseEntity<>(portfolioDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable long id) {
        Portfolio portfolio = portfolioService.findPortfolioById(id);
        if (portfolio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        portfolioService.deletePortfolioById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteall")
    public ResponseEntity<Void> deleteAllPortfolios() {
        portfolioService.deleteAllPortfolios();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
