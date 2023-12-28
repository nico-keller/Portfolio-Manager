package com.example.bookexample.presentation;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.service.InvestorService;
import com.example.bookexample.service.PortfolioService;
import com.vaadin.flow.component.combobox.ComboBox;
import org.vaadin.crudui.crud.CrudListener;
import java.util.Collection;
import java.util.List;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class InvestorCrudListener implements CrudListener<Investor> {

    private final InvestorService investorService;
    private ComboBox<Investor> investorComboBox;
    private ComboBox<Portfolio> portfolioComboBox;

    public InvestorCrudListener(InvestorService investorService, PortfolioService portfolioService) {
        this.investorService = investorService;
        investorComboBox = new ComboBox<>("Select Investor", investorService.findAllInvestors());
        investorComboBox.setItemLabelGenerator(Investor::getEmail); // Or another identifier
        portfolioComboBox = new ComboBox<>("Select Portfolio", portfolioService.findAllPortfolios());
        portfolioComboBox.setItemLabelGenerator(Portfolio::getPortfolioName); // Or another identifier
    }
    public void associatePortfolioWithInvestor() {
        Long investorId = investorComboBox.getValue().getInvestorId();
        Long portfolioId = portfolioComboBox.getValue().getPortfolioId();
        investorService.associatePortfolioWithInvestor(investorId, portfolioId);
    }

    @Override
    public Collection<Investor> findAll() {
        return investorService.findAllInvestors();
    }

    @Override
    public Investor add(Investor investor) {
        investorService.addInvestor(investor);
        return investor;
    }

    @Override
    public Investor update(Investor investor) {
        investorService.updateInvestor(investor);
        return investor;
    }

    @Override
    public void delete(Investor investor) {
        investorService.deleteInvestorById(investor.getInvestorId());
    }
}
