package com.example.bookexample.ui;

import com.example.bookexample.model.Portfolio;
import com.example.bookexample.presentation.PortfolioCrudListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("portfolio")
public class PortfolioView extends VerticalLayout {

    private final PortfolioCrudListener portfolioCrudListener;
    private TextField portfolioNameField;
    private Button saveButton;

    public PortfolioView(PortfolioCrudListener portfolioCrudListener) {
        this.portfolioCrudListener = portfolioCrudListener;
        initLayout();
    }

    private void initLayout() {
        portfolioNameField = new TextField("Portfolio Name");
        saveButton = new Button("Save", event -> savePortfolio());

        add(portfolioCrudListener.createPortfolioForm(), portfolioNameField, saveButton);
    }

    private void savePortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioName(portfolioNameField.getValue());
        portfolioCrudListener.add(portfolio);
    }
}
