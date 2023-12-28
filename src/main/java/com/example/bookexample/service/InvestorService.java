package com.example.bookexample.service;

import com.example.bookexample.model.Investor;
import java.util.List;

public interface InvestorService {
    List<Investor> findAllInvestors();
    Investor findInvestorById(long investorId);
    Investor findInvestorByEmail(String email);
    void addInvestor(Investor investor);
    void updateInvestor(Investor investor);
    void deleteInvestor(Investor investor);
    void deleteInvestorById(long investorId);
    void deleteAllInvestors();
    void associatePortfolioWithInvestor(Long investorId, Long portfolioId);
}
