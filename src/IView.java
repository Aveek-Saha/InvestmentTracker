import java.util.LinkedHashMap;

/**
 * This interface represents the View portion of MVC architecture
 * for the stock portfolio.
 * It has methods that displays strings, options and menus.
 * None of the methods return anything.
 */
public interface IView {

  /**
   * Displays the string passed to it.
   *
   * @param s the string to be displayed
   */
  void showString(String s);

  /**
   * Displays the menu options.
   */
  void showOptions();

  /**
   * Displayed when the menu option entered by the user is incorrect.
   */
  void showOptionError();

  /**
   * Displayed when the menu option entered by the user is incorrect.
   */
  void showStringEnterPortfolioName();

  /**
   * Asks the user to enter the name of the stock.
   */
  void showStringEnterStockName();

  /**
   * Asks the user to enter the amount of stock to add to the portfolio.
   */
  void showStringEnterStockAmount();

  /**
   * Asks the user to enter the date to calculate the portfolio value for.
   */
  void showStringEnterDate();

  /**
   * Asks the user if they want to enter more stocks.
   */
  void showStringEnterMoreStocks();

  /**
   * Displayed when the stock name entered by the user is invalid.
   */
  void showStringWrongStockName();

  /**
   * Displayed when the date entered by the user is invalid.
   */
  void showStringWrongDate();

  /**
   * Displayed when the portfolio name entered by the user is invalid.
   */
  void showStringWrongPortfolioName();

  /**
   * Displayed when the portfolio name entered by the user already exists.
   */
  void showStringExistingPortfolioName();

  /**
   * Displays the total value of a portfolio.
   *
   * @param s the value of the portfolio
   */
  void showValue(String s);

  /**
   * Displayed when the stock amount entered by the user is invalid.
   */
  void showStringWrongStockAmount();

  /**
   * Show menu options for the flexible portfolio.
   */
  void showOptionsV2();

  /**
   * Show menu for selecting the type of portfolio.
   */
  void showPortfolioOptions();

  /**
   * Displayed when the stock amount to be sold entered by the user is invalid.
   */
  void showStringWrongSell();

  /**
   * Displays the cost basis of a portfolio.
   *
   * @param s the cost basis of the portfolio
   */
  void showCostBasis(String s);

  /**
   * Asks the user to enter the commission for a transaction.
   */
  void showStringEnterCommission();

  /**
   * Displayed when the commission amount entered by the user is invalid.
   */
  void showStringWrongCommission();

  /**
   * Displays the performance bar chart of a portfolio over a time span.
   *
   * @param scale the scale of the bar chart
   * @param startDate the starting date of the range
   * @param endDate the end date of the range
   * @param portfolioName the name of the portfolio
   * @param dateMap the dates and corresponding values
   */
  void showPerformanceOverTime(int scale, String startDate, String endDate,
                               String portfolioName, LinkedHashMap<String, Float> dateMap);

  /**
   * Asks the user to enter the start date for the bar chart.
   */
  void showStringEnterStartDate();

  /**
   * Asks the user to enter the end date for the bar chart.
   */
  void showStringEnterEndDate();

  /**
   * Displayed when the date range entered by the user is invalid.
   */
  void showStringWrongDateRange();
}
