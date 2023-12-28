package com.example.bookexample.presentation;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.service.InvestorService;
import com.example.bookexample.service.PortfolioService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@SpringComponent
public class PortfolioCrudListener implements CrudListener<Portfolio> {

    private final PortfolioService portfolioService;
    private final ComboBox<Investor> investorComboBox;
    private Binder<Portfolio> binder;

    public PortfolioCrudListener(PortfolioService portfolioService, InvestorService investorService) {
        this.portfolioService = portfolioService;
        this.investorComboBox = new ComboBox<>("Select Investor", investorService.findAllInvestors());
        investorComboBox.setItemLabelGenerator(investor -> String.valueOf(investor.getInvestorId())); // Identifier
        binder = new Binder<>(Portfolio.class);
    }

    public FormLayout createPortfolioForm() {
        FormLayout formLayout = new FormLayout();

        TextField portfolioNameField = new TextField("Portfolio Name");
        binder.forField(portfolioNameField)
                .bind(Portfolio::getPortfolioName, Portfolio::setPortfolioName);

        formLayout.add(portfolioNameField, investorComboBox);
        return formLayout;
    }

    @Override
    public Collection<Portfolio> findAll() {
        return portfolioService.findAllPortfolios();
    }

    @Override
    public Portfolio add(Portfolio portfolio) {
        FormLayout formLayout = createPortfolioForm();
        Button saveButton = new Button("Save", event -> savePortfolio(portfolio));
        formLayout.add(saveButton);

        // Display the form to the user
        // ...

        return portfolio;
    }

    private void savePortfolio(Portfolio portfolio) {
        Investor selectedInvestor = investorComboBox.getValue();
        if (selectedInvestor == null) {
            Notification.show("Please select an investor", 3000, Notification.Position.MIDDLE);
            return;
        }

        binder.writeBeanIfValid(portfolio);
        portfolio.setInvestor(selectedInvestor);
        portfolioService.addPortfolio(portfolio);
        Notification.show("Portfolio added", 3000, Notification.Position.MIDDLE);
    }

    @Override
    public Portfolio update(Portfolio portfolio) {
        FormLayout formLayout = createPortfolioForm();
        Button saveButton = new Button("Save", event -> savePortfolio(portfolio));
        formLayout.add(saveButton);

        binder.readBean(portfolio);
        investorComboBox.setValue(portfolio.getInvestor());

        // Display the form to the user for editing
        // ...

        return portfolio;
    }

    @Override
    public void delete(Portfolio portfolio) {
        portfolioService.deletePortfolioById(portfolio.getPortfolioId());
    }
}
