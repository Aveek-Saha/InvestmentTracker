/**
 * This is an interface that represents the Controller portion of the MVC for the GUI based view.
 * It contains methods that coordinate between the new GUI view and the model.
 * This interface contains the methods used to call the display methods in the GUI view and append
 * data to the portfolios using the model.
 */
public interface Features {

  /**
   * Shows the panel used for buying stocks.
   */
  void showBuy();

  /**
   * Shows the Main Menu after the showBuy panel is called.
   */
  void backMenuBuyStock();

  /**
   * Lets user buy stock.
   *
   * @param portfolioName name of the portfolio
   * @param stockName name of the stock
   * @param date the date on which the user wants to buy stock
   * @param amount the amount of stock the user wants to buy
   * @param commission the commission fee required to buy stock
   */

  void submitBuy(String portfolioName, String stockName, String date, String amount,
                 String commission);

  /**
   * Shows the panel used for selling stocks.
   */

  void showSell();

  /**
   * Shows the Main Menu after the showSell panel is called.
   */

  void backMenuSellStock();

  /**
   * Lets user sell stock.
   *
   * @param portfolioName name of the portfolio
   * @param stockName name of the stock
   * @param date the date on which the user wants to buy stock
   * @param amount the amount of stock the user wants to buy
   * @param commission the commission fee required to buy stock
   */

  void submitSell(String portfolioName, String stockName, String date, String amount,
                  String commission);

  /**
   * Shows the panel asking for portfolio name before portfolio information is displayed.
   */
  void showPortfolio();

  /**
   * Displays the portfolio contents based on the portfolio name.
   *
   * @param portfolioName name of the portfolio
   */

  void submitPortfolioName(String portfolioName);

  /**
   * Shows the Main Menu after the showPortfolio panel is called.
   */

  void backMenuPortfolioStock();

  /**
   * Shows the Main Menu after the submitPortfolioName panel is called.
   */

  void backMenuDisplayPortfolio();

  /**
   * Displays the dollar-cost averaging.
   */

  void showDollarCostAveraging();

  /**
   * Shows the Main Menu after the showDollarCostAveraging panel is called.
   */

  void backMenuDCA();

  /**
   * Lets user perform dollar-cost averaging.
   *
   * @param money money to be invested
   * @param stocks stocks to be invested in
   * @param percentages percentage of stock to be invested
   * @param portfolioName name of the portfolio
   * @param start start date for the range
   * @param end end date for the range
   * @param interval the frequency for investing
   * @param commission amount of money to be commissioned
   */

  void submitDCA(String money, String stocks, String percentages, String portfolioName,
                 String start, String end, String interval, String commission);

  /**
   * Shows the weight of stocks to be bought.
   */

  void showBuyWeight();

  /**
   * Shows the Main Menu after the submitDCA panel is called.
   */

  void backMenuBuyWeight();

  /**
   * Allows user to submit the percentage of stocks to be invested in.
   *
   * @param money money to be invested
   * @param stocks stocks to be invested in
   * @param percentages percentage of stock to be invested
   * @param portfolioName name of the portfolio
   * @param date date on which stock is to be bought
   * @param commission amount of money to be commissioned
   */

  void submitBuyWeight(String money, String stocks, String percentages, String portfolioName,
                       String date, String commission);

  /**
   * Displays the cost input.
   */
  void showCostInput();

  /**
   * Shows the Main Menu after the submitBuyWeight panel is called.
   */

  void backMenuCostInput();

  /**
   * Allows the user to submit the cost input.
   *
   * @param portfolioName name of portfolio
   * @param date date on which stock is to be bought
   */

  void submitCostInput(String portfolioName, String date);

  /**
   * Shows the value input.
   */

  void showValueInput();

  /**
   * Shows the Main Menu after the submitCostInput panel is called.
   */

  void backMenuValueInput();

  /**
   * Shows the Main Menu after the showValueInput panel is called.
   */

  void backMenuCostValue();

  /**
   * Allows the user to submit value input.
   *
   * @param portfolioName name of the portfolio
   * @param date date on which value needs to be checked
   */

  void submitValueInput(String portfolioName, String date);

  /**
   * Panel that shows error message displayed when an incorrect input is entered.
   *
   * @param error the error message
   */

  void showError(String error);

  /**
   * Shows the Main Menu after the showError panel is called.
   */

  void backMenuError();
}
