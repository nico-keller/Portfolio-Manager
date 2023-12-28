package com.example.bookexample.service;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.model.Stock;
import com.example.bookexample.repository.InvestorRepository;
import com.example.bookexample.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    private final InvestorRepository investorRepository;


    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Portfolio findPortfolioById(long portfolioId) {
        return portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
    }

    @Override
    public List<Portfolio> findAllPortfolios() {
        return (List<Portfolio>) portfolioRepository.findAll();
    }

    @Override
    public void addPortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    @Override
    public void updatePortfolio(Portfolio portfolio) {
        if (!portfolioRepository.existsById(portfolio.getPortfolioId())) {
            throw new RuntimeException("Portfolio not found");
        }
        portfolioRepository.save(portfolio);
    }
    @Override
    public void addPortfolio(Portfolio portfolio, Long investorId) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));
        portfolio.setInvestor(investor);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolioById(long portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }

    @Override
    public void addStockToPortfolio(long portfolioId, Stock stock) {
        Portfolio portfolio = findPortfolioById(portfolioId);
        portfolio.getStocks().add(stock);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void removeStockFromPortfolio(long portfolioId, Stock stock) {
        Portfolio portfolio = findPortfolioById(portfolioId);
        portfolio.getStocks().remove(stock);
        portfolioRepository.save(portfolio);
    }
}
