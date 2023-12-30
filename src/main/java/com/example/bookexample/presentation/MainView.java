package com.example.bookexample.presentation;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import com.example.bookexample.model.Stock;
import com.example.bookexample.model.StockTrade;
import com.example.bookexample.viewmodel.InvestorViewModel;
import com.example.bookexample.viewmodel.PortfolioViewModel;
import com.example.bookexample.viewmodel.StockTradeViewModel;
import com.example.bookexample.viewmodel.StockViewModel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
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

        Tab tabInvestors = new Tab(VaadinIcon.USER.create(), new Span("Investors"));
        Tab tabPortfolios = new Tab(VaadinIcon.BRIEFCASE.create(), new Span("Portfolios"));
        Tab tabStockTrades = new Tab(VaadinIcon.EXCHANGE.create(), new Span("Stock Trades"));
        Tab tabStocks = new Tab(VaadinIcon.INVOICE.create(), new Span("Stocks"));

        tabs.add(tabInvestors, tabPortfolios, tabStockTrades, tabStocks);

        GridCrud<InvestorViewModel> investorCrud = new GridCrud<>(InvestorViewModel.class, investorCrudListener);
        GridCrud<StockViewModel> stockCrud = new GridCrud<>(StockViewModel.class, stockCrudListener);
        GridCrud<PortfolioViewModel> portfolioCrud = new GridCrud<>(PortfolioViewModel.class, portfolioCrudListener);
        GridCrud<StockTradeViewModel> stockTraderCrud = new GridCrud<>(StockTradeViewModel.class, stockTraderCrudListener);

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
            } else if (selectedTab.equals(tabStockTrades)) {
                add(stockTraderCrud);
            }
        });

        add(tabs);
        add(investorCrud);
    }
}
