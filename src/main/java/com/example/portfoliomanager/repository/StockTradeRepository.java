package com.example.portfoliomanager.repository;

import com.example.portfoliomanager.model.StockTrade;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface StockTradeRepository extends CrudRepository<StockTrade, Long> {
    Set<StockTrade> findByPortfolio_PortfolioId(long portfolioId);
}
