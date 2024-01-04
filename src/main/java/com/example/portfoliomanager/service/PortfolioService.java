package com.example.portfoliomanager.service;

import com.example.portfoliomanager.model.Portfolio;

import java.util.List;

public interface PortfolioService {
    Portfolio findPortfolioById(long portfolioId);
    List<Portfolio> findAllPortfolios();
    void addPortfolio(Portfolio portfolio);
    void updatePortfolio(Portfolio portfolio);
    void deletePortfolioById(long portfolioId);

    void deletePortfolio(Portfolio portfolio);

    void addPortfolio(Portfolio portfolio, Long investorId);

    void deleteAllPortfolios();
}
