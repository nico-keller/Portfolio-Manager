package com.example.bookexample.presentation;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.model.Stock;
import com.example.bookexample.model.StockTrade;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@Route("")
public class MainView extends VerticalLayout {

    private final InvestorCrudListener investorCrudListener;
    private final StockCrudListener stockCrudListener;
    private final PortfolioCrudListener portfolioCrudListener;
    private final StockTradeCrudListener stockTraderCrudListener;

    @Autowired
    public MainView(InvestorCrudListener investorCrudListener,
                    StockCrudListener stockCrudListener,
                    PortfolioCrudListener portfolioCrudListener,
                    StockTradeCrudListener stockTraderCrudListener) {
        this.investorCrudListener = investorCrudListener;
        this.stockCrudListener = stockCrudListener;
        this.portfolioCrudListener = portfolioCrudListener;
        this.stockTraderCrudListener = stockTraderCrudListener;

        initLayout();
    }

    private void initLayout() {
        Tabs tabs = new Tabs();
        Tab tabInvestors = new Tab("Investors");
        Tab tabStocks = new Tab("Stocks");
        Tab tabPortfolios = new Tab("Portfolios");
        Tab tabStockTraders = new Tab("Stock Traders");
        tabs.add(tabInvestors, tabStocks, tabPortfolios, tabStockTraders);

        GridCrud<Investor> investorCrud = new GridCrud<>(Investor.class, investorCrudListener);
        GridCrud<Stock> stockCrud = new GridCrud<>(Stock.class, stockCrudListener);
        GridCrud<Portfolio> portfolioCrud = new GridCrud<>(Portfolio.class, portfolioCrudListener);
        GridCrud<StockTrade> stockTraderCrud = new GridCrud<>(StockTrade.class, stockTraderCrudListener);

        tabs.addSelectedChangeListener(event -> {
            removeAll();
            add(tabs);
            Tab selectedTab = tabs.getSelectedTab();
            if (selectedTab.equals(tabInvestors)) {
                add(investorCrud);
            } else if (selectedTab.equals(tabStocks)) {
                add(stockCrud);
            } else if (selectedTab.equals(tabPortfolios)) {
                add(portfolioCrud);
            } else if (selectedTab.equals(tabStockTraders)) {
                add(stockTraderCrud);
            }
        });

        add(tabs);
        add(investorCrud);
    }
}
