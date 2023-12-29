package com.example.bookexample.service;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.repository.InvestorRepository;
import com.example.bookexample.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("InvestorService")
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    private PortfolioRepository portfolioRepository;

    @Override
    public List<Investor> findAllInvestors() {
        return (List<Investor>) investorRepository.findAll();
    }

    @Override
    public Investor findInvestorById(long id) {
        return investorRepository.findByInvestorId(id);
    }

    @Override
    public Investor findInvestorByEmail(String email) {
        return investorRepository.findByEmail(email);
    }

    @Override
    public void addInvestor(Investor investor) {
        investorRepository.save(investor);
    }

    @Override
    public void updateInvestor(Investor investor) {
        if (!investorRepository.existsById(investor.getInvestorId())) {
            throw new RuntimeException("Investor not found");
        }
        investorRepository.save(investor);
    }

    @Override
    public void deleteInvestor(Investor investor){
        investorRepository.delete(investor);}

    public InvestorRepository getInvestorRepository() {
        return investorRepository;
    }

    @Override
    public void deleteInvestorById(long id) {
        investorRepository.deleteById(id);
    }

    @Override
    public void deleteAllInvestors() {
        investorRepository.deleteAll();
    }
    @Override
    public void associatePortfolioWithInvestor(Long investorId, Long portfolioId) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        investor.getPortfolios().add(portfolio);
        portfolio.setInvestor(investor);
        investorRepository.save(investor);
    }
}
