package com.example.portfoliomanager.presentation;

import com.example.portfoliomanager.model.Investor;
import com.example.portfoliomanager.model.Portfolio;
import com.example.portfoliomanager.service.InvestorService;
import com.example.portfoliomanager.service.PortfolioService;
import com.example.portfoliomanager.viewmodel.PortfolioViewModel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.vaadin.crudui.crud.CrudListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringComponent
public class PortfolioCrudListener implements CrudListener<PortfolioViewModel> {

    private final PortfolioService portfolioService;

    private final InvestorService investorService;

    public PortfolioCrudListener(PortfolioService portfolioService, InvestorService investorService) {
        this.portfolioService = portfolioService;
        this.investorService = investorService;
    }

    @Override
    public Collection<PortfolioViewModel> findAll() {
        List<Portfolio> portfolioList = portfolioService.findAllPortfolios();
        List<PortfolioViewModel> portfolioViewModelList = new ArrayList<>();
        for (Portfolio portfolio : portfolioList) {
            portfolioViewModelList.add(new PortfolioViewModel(portfolio.getPortfolioId(), portfolio.getOpeningDate(), portfolio.getPortfolioName(), portfolio.getInvestor().getInvestorId()));
        }
        return portfolioViewModelList;
    }

    @Override
    public PortfolioViewModel add(PortfolioViewModel portfolio) {
        List<Portfolio> portfolioList = portfolioService.findAllPortfolios();
        boolean exists = false;
        for (Portfolio p : portfolioList) {
            if (p.getPortfolioId() == portfolio.getPortfolioId()) {
                Notification.show("Portfolio with this ID already exists!", 3000, Notification.Position.MIDDLE);
                exists = true;
            }
        }
        if (!exists) {
            List<Investor> investorList = investorService.findAllInvestors();
            Investor investor = null;
            for (Investor i : investorList) {
                if (i.getInvestorId() == portfolio.getInvestorId()) {
                    investor = i;
                }
            }
            if (investor == null) {
                Notification.show("Investor with this ID does not exist!", 3000, Notification.Position.MIDDLE);
            } else {
                Portfolio p = new Portfolio(portfolio.getPortfolioId(), portfolio.getOpeningDate(), portfolio.getPortfolioName(), investor);
                portfolioService.addPortfolio(p);
            }
        }

        return portfolio;
    }

    @Override
    public PortfolioViewModel update(PortfolioViewModel portfolio) {
        List<Portfolio> portfolioList = portfolioService.findAllPortfolios();
        for (Portfolio p : portfolioList) {
            if (p.getPortfolioId() == portfolio.getPortfolioId()) {
                p.setOpeningDate(portfolio.getOpeningDate());
                p.setPortfolioName(portfolio.getPortfolioName());
                List<Investor> investorList = investorService.findAllInvestors();
                Investor investor = null;
                for (Investor i : investorList) {
                    if (i.getInvestorId() == portfolio.getInvestorId()) {
                        investor = i;
                    }
                }
                if (investor == null) {
                    Notification.show("Investor with this ID does not exist!", 3000, Notification.Position.MIDDLE);
                } else {
                    p.setInvestor(investor);
                    portfolioService.updatePortfolio(p);
                }
            }
        }

        return portfolio;
    }

    @Override
    public void delete(PortfolioViewModel portfolio) {
        List<Portfolio> portfolioList = portfolioService.findAllPortfolios();
        for (Portfolio p : portfolioList) {
            if (p.getPortfolioId() == portfolio.getPortfolioId()) {
                portfolioService.deletePortfolio(p);
            }
        }
    }
}
