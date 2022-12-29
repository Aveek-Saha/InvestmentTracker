import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;


/**
 * This class represents the implementation of the GUI View portion of MVC architecture
 * for the stock portfolio.
 * This class contains all methods mentioned in the IFrameView interface.
 * This class has methods to display options and menus in the GUI.
 */
public class JFrameView extends JFrame implements IFrameView {

  private JLabel displayPortfolioName;
  private JLabel displayStock;
  private JLabel displayAmount;
  private JLabel displayDate;
  private JLabel displayCommission;
  private JLabel displayStartDate;
  private JLabel displayEndDate;
  private JLabel displayStockList;
  private JLabel displayPercentList;
  private JLabel displayMoney;
  private JLabel displayInterval;
  private JLabel displayCostValue;
  private JLabel displayError;
  private JButton buyButton;
  private JButton sellButton;
  private JButton displayButton;
  private JButton dcaButton;
  private JButton weightButton;
  private JButton costButton;
  private JButton valueButton;
  private JButton backButton;
  private JButton submitBuyStockButton;
  private JButton submitSellStockButton;
  private JButton submitPortfolioNameButton;
  private JButton submitDCAButton;
  private JButton submitBuyWeightButton;
  private JButton submitValueInputButton;
  private JButton submitCostInputButton;
  private JTextField inputPortfolioName;
  private JTextField inputStock;
  private JTextField inputDate;
  private JTextField inputCommission;
  private JTextField inputAmount;
  private JTextField inputStartDate;
  private JTextField inputEndDate;
  private JTextField inputStockList;
  private JTextField inputPercentList;
  private JTextField inputMoney;
  private JTextField inputInterval;

  private JScrollPane tablePane;


  /**
   * Constructs a new JFrameView object initialized with the title of the frame.
   *
   * @param caption the frame title
   */
  public JFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.displayMainMenu();

  }

  @Override
  public void displayMainMenu() {
    this.setLayout(new GridLayout(0, 2, 1, 1));

    buyButton = new JButton("Buy Stocks");
    buyButton.setActionCommand("Buy Stocks Button");
    this.add(buyButton);

    sellButton = new JButton("Sell Stocks");
    sellButton.setActionCommand("Sell Stocks Button");
    this.add(sellButton);

    displayButton = new JButton("Display Portfolio");
    displayButton.setActionCommand("Display Portfolio Button");
    this.add(displayButton);

    dcaButton = new JButton("Dollar Cost Averaging");
    dcaButton.setActionCommand("Dollar Cost Averaging Button");
    this.add(dcaButton);

    weightButton = new JButton("Buy Stocks using weights");
    weightButton.setActionCommand("Weights Button");
    this.add(weightButton);

    costButton = new JButton("Show Cost basis");
    costButton.setActionCommand("Cost Basis Button");
    this.add(costButton);

    valueButton = new JButton("Show Value");
    valueButton.setActionCommand("Value Button");
    this.add(valueButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeMainMenu() {
    this.remove(buyButton);
    this.remove(sellButton);
    this.remove(displayButton);
    this.remove(dcaButton);
    this.remove(weightButton);
    this.remove(costButton);
    this.remove(valueButton);
  }

  protected void displayStockTransaction() {
    displayPortfolioName = new JLabel("Enter the portfolio name");
    this.add(displayPortfolioName);
    inputPortfolioName = new JTextField(10);
    this.add(inputPortfolioName);

    displayStock = new JLabel("Enter the stock name");
    this.add(displayStock);
    inputStock = new JTextField(10);
    this.add(inputStock);

    displayAmount = new JLabel("Enter the stock amount");
    this.add(displayAmount);
    inputAmount = new JTextField(10);
    this.add(inputAmount);

    displayDate = new JLabel("Enter the date [YYYY-MM-DD]");
    this.add(displayDate);
    inputDate = new JTextField(10);
    this.add(inputDate);

    displayCommission = new JLabel("Enter the commission amount");
    this.add(displayCommission);
    inputCommission = new JTextField(10);
    this.add(inputCommission);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);
  }

  protected void removeStockTransaction() {
    this.remove(displayPortfolioName);
    this.remove(inputPortfolioName);
    this.remove(displayStock);
    this.remove(inputStock);
    this.remove(displayDate);
    this.remove(inputDate);
    this.remove(displayAmount);
    this.remove(inputAmount);
    this.remove(displayCommission);
    this.remove(inputCommission);
    this.remove(backButton);
  }

  @Override
  public void displayBuyStock() {
    displayStockTransaction();
    submitBuyStockButton = new JButton("Submit");
    submitBuyStockButton.setActionCommand("Submit Buy Stock Button");
    this.add(submitBuyStockButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeBuyStock() {
    removeStockTransaction();
    this.remove(submitBuyStockButton);
  }


  @Override
  public void displaySellStock() {
    displayStockTransaction();
    submitSellStockButton = new JButton("Submit");
    submitSellStockButton.setActionCommand("Submit Sell Stock Button");
    this.add(submitSellStockButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeSellStock() {
    removeStockTransaction();
    this.remove(submitSellStockButton);
  }

  @Override
  public void displayPortfolio(String s) {
    this.setLayout(new FlowLayout());

    String[] columnNames = {"Stock", "Quantity", "Date", "Buy/Sell", "Commission"};
    String lineSep = System.lineSeparator();
    String[] rows = s.split(lineSep);
    String[][] data = new String[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      data[i] = rows[i].split(",");
    }

    JTable table = new JTable(data, columnNames);
    table.setPreferredScrollableViewportSize(new Dimension(500, 500));
    table.setFillsViewportHeight(true);

    tablePane = new JScrollPane(table);
    this.add(tablePane);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeDisplayPortfolio() {
    this.remove(tablePane);
    this.remove(backButton);
  }

  @Override
  public void displayEnterPortfolioName() {
    displayPortfolioName = new JLabel("Enter the portfolio name");
    this.add(displayPortfolioName);
    inputPortfolioName = new JTextField(10);
    this.add(inputPortfolioName);

    submitPortfolioNameButton = new JButton("Submit");
    submitPortfolioNameButton.setActionCommand("Submit Portfolio Name Button");
    this.add(submitPortfolioNameButton);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeEnterPortfolioName() {
    this.remove(displayPortfolioName);
    this.remove(inputPortfolioName);
    this.remove(submitPortfolioNameButton);
    this.remove(backButton);
  }

  protected void displayDCAWeight() {

    displayMoney = new JLabel("Enter the investment amount");
    this.add(displayMoney);
    inputMoney = new JTextField(10);
    this.add(inputMoney);

    displayCommission = new JLabel("Enter the commission amount");
    this.add(displayCommission);
    inputCommission = new JTextField(10);
    this.add(inputCommission);

    displayStockList = new JLabel("Enter the stock names separated by commas");
    this.add(displayStockList);
    inputStockList = new JTextField(10);
    this.add(inputStockList);

    displayPercentList =
            new JLabel("Enter the corresponding stock percentages separated by commas");
    this.add(displayPercentList);
    inputPercentList = new JTextField(10);
    this.add(inputPercentList);
  }

  protected void removeDCAWeight() {
    this.remove(displayPortfolioName);
    this.remove(inputPortfolioName);
    this.remove(displayMoney);
    this.remove(inputMoney);
    this.remove(displayCommission);
    this.remove(inputCommission);
    this.remove(displayStockList);
    this.remove(inputStockList);
    this.remove(displayPercentList);
    this.remove(inputPercentList);
    this.remove(backButton);
  }

  @Override
  public void displayDollarCostAveraging() {
    displayPortfolioName = new JLabel("Enter the portfolio name");
    this.add(displayPortfolioName);
    inputPortfolioName = new JTextField(10);
    this.add(inputPortfolioName);

    displayStartDate = new JLabel("Enter the start date [YYYY-MM-DD]");
    this.add(displayStartDate);
    inputStartDate = new JTextField(10);
    this.add(inputStartDate);

    displayEndDate = new JLabel("Enter the end date [YYYY-MM-DD]");
    this.add(displayEndDate);
    inputEndDate = new JTextField(10);
    this.add(inputEndDate);

    displayInterval = new JLabel("Enter the investment interval in days");
    this.add(displayInterval);
    inputInterval = new JTextField(10);
    this.add(inputInterval);

    displayDCAWeight();

    submitDCAButton = new JButton("Submit");
    submitDCAButton.setActionCommand("Submit DCA Button");
    this.add(submitDCAButton);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeDollarCostAveraging() {
    removeDCAWeight();
    this.remove(displayStartDate);
    this.remove(inputStartDate);
    this.remove(displayEndDate);
    this.remove(inputEndDate);
    this.remove(displayInterval);
    this.remove(inputInterval);
    this.remove(submitDCAButton);
  }

  @Override
  public void displayBuyWeight() {
    displayPortfolioName = new JLabel("Enter the portfolio name");
    this.add(displayPortfolioName);
    inputPortfolioName = new JTextField(10);
    this.add(inputPortfolioName);

    displayDate = new JLabel("Enter the date [YYYY-MM-DD]");
    this.add(displayDate);
    inputDate = new JTextField(10);
    this.add(inputDate);

    displayDCAWeight();

    submitBuyWeightButton = new JButton("Submit");
    submitBuyWeightButton.setActionCommand("Submit Buy Weight Button");
    this.add(submitBuyWeightButton);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);

  }

  @Override
  public void removeBuyWeight() {
    removeDCAWeight();
    this.remove(displayDate);
    this.remove(inputDate);
    this.remove(submitBuyWeightButton);
  }

  protected void displayValueCostInput() {
    displayPortfolioName = new JLabel("Enter the portfolio name");
    this.add(displayPortfolioName);
    inputPortfolioName = new JTextField(10);
    this.add(inputPortfolioName);

    displayDate = new JLabel("Enter the date [YYYY-MM-DD]");
    this.add(displayDate);
    inputDate = new JTextField(10);
    this.add(inputDate);
  }

  protected void removeValueCostInput() {
    this.remove(displayPortfolioName);
    this.remove(inputPortfolioName);
    this.remove(displayDate);
    this.remove(inputDate);
    this.remove(backButton);
  }

  @Override
  public void displayValueInput() {
    displayValueCostInput();

    submitValueInputButton = new JButton("Submit");
    submitValueInputButton.setActionCommand("Submit Value Input Button");
    this.add(submitValueInputButton);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeValueInput() {
    removeValueCostInput();
    this.remove(submitValueInputButton);
  }

  @Override
  public void displayCostInput() {
    displayValueCostInput();

    submitCostInputButton = new JButton("Submit");
    submitCostInputButton.setActionCommand("Submit Cost Button");
    this.add(submitCostInputButton);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeCostInput() {
    removeValueCostInput();
    this.remove(submitCostInputButton);
  }

  @Override
  public void displayCostValue(String type, float amount) {
    this.setLayout(new GridLayout(0, 1, 1, 1));

    displayCostValue = new JLabel("The " + type + " of the portfolio on the given date is $"
            + amount);
    this.add(displayCostValue);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeCostValue() {
    this.remove(displayCostValue);
    this.remove(backButton);
  }

  @Override
  public void displayError(String error) {
    this.setLayout(new GridLayout(0, 1, 1, 1));
    displayError = new JLabel("Incorrect input for " + error);
    this.add(displayError);

    backButton = new JButton("Back");
    backButton.setActionCommand("Back Button");
    this.add(backButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void removeError() {
    this.remove(displayError);
    this.remove(backButton);
  }

  @Override
  public void addFeatures(Features features) {
    buyButton.addActionListener(evt -> features.showBuy());
    sellButton.addActionListener(evt -> features.showSell());
    displayButton.addActionListener(evt -> features.showPortfolio());
    dcaButton.addActionListener(evt -> features.showDollarCostAveraging());
    weightButton.addActionListener(evt -> features.showBuyWeight());
    costButton.addActionListener(evt -> features.showCostInput());
    valueButton.addActionListener(evt -> features.showValueInput());
  }

  @Override
  public void addFeaturesBuyMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuBuyStock());
    submitBuyStockButton.addActionListener(evt -> features.submitBuy(inputPortfolioName.getText(),
            inputStock.getText(), inputDate.getText(), inputAmount.getText(),
            inputCommission.getText()));
  }

  @Override
  public void addFeaturesSellMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuSellStock());
    submitSellStockButton.addActionListener(evt -> features.submitSell(inputPortfolioName.getText(),
            inputStock.getText(), inputDate.getText(), inputAmount.getText(),
            inputCommission.getText()));
  }

  @Override
  public void addFeaturesPortfolioNameMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuPortfolioStock());
    submitPortfolioNameButton.addActionListener(evt ->
            features.submitPortfolioName(inputPortfolioName.getText()));
  }

  @Override
  public void addFeaturesDisplayPortfolioMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuDisplayPortfolio());
  }

  @Override
  public void addFeaturesDCAMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuDCA());
    submitDCAButton.addActionListener(evt -> features.submitDCA(inputMoney.getText(),
            inputStockList.getText(), inputPercentList.getText(), inputPortfolioName.getText(),
            inputStartDate.getText(), inputEndDate.getText(), inputInterval.getText(),
            inputCommission.getText()));
  }

  @Override
  public void addFeaturesWeightMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuBuyWeight());
    submitBuyWeightButton.addActionListener(evt -> features.submitBuyWeight(inputMoney.getText(),
            inputStockList.getText(), inputPercentList.getText(), inputPortfolioName.getText(),
            inputDate.getText(), inputCommission.getText()));
  }

  @Override
  public void addFeaturesCostInputMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuCostInput());
    submitCostInputButton.addActionListener(evt -> features.submitCostInput(
            inputPortfolioName.getText(), inputDate.getText()));
  }

  @Override
  public void addFeaturesValueInputMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuValueInput());
    submitValueInputButton.addActionListener(evt -> features.submitValueInput(
            inputPortfolioName.getText(), inputDate.getText()));
  }

  @Override
  public void addFeaturesCostValueMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuCostValue());
  }

  @Override
  public void addFeaturesErrorMenu(Features features) {
    backButton.addActionListener(evt -> features.backMenuError());
  }
}