package com.example.bookexample.service;

import com.example.bookexample.model.Portfolio;
import com.example.bookexample.model.Stock;

import java.util.List;

public interface PortfolioService {
    Portfolio findPortfolioById(long portfolioId);
    List<Portfolio> findAllPortfolios();
    void addPortfolio(Portfolio portfolio);
    void updatePortfolio(Portfolio portfolio);
    void deletePortfolioById(long portfolioId);

    void addStockToPortfolio(long portfolioId, Stock stock);
    void removeStockFromPortfolio(long portfolioId, Stock stock);
    void addPortfolio(Portfolio portfolio, Long investorId);
}
