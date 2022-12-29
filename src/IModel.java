import java.util.LinkedHashMap;

/**
 * This interface represents the Model portion of MVC architecture
 * for the stock portfolio.
 * This interface contains declaration of the methods that implement the
 * functionality of the stocks problem statement.
 * Contains methods that validate inputs, write to and read portfolios.
 */
public interface IModel {

  /**
   * Creates a portfolio containing stock names and number
   * of stocks.
   *
   * @param portfolioName name of the portfolio
   * @param contents the stock names and amounts
   * @param portfolioType the type of portfolio
   */
  void writePortfolio(String portfolioName, String contents, String portfolioType);

  /**
   * Returns the stock names and amounts for a particular portfolio.
   *
   * @param portfolioName name of the portfolio
   * @param portfolioType the type of portfolio
   * @return the stock names and amounts for the portfolio
   */
  String readPortfolio(String portfolioName, String portfolioType);

  /**
   * Checks if a valid stock name has been given.
   *
   * @param stock name of the stock
   * @return true if the stock name is valid, false otherwise
   */
  boolean checkStockName(String stock);

  /**
   * Get the price of a stock at a particular date.
   *
   * @param date the date at which the price has to be found
   * @param stock name of the stock
   * @return the opening price of the stock on the given date
   */
  float getPriceAtDate(String date, String stock);

  /**
   * Get the value of a portfolio at a particular date.
   *
   * @param date the date at which the value has to be found
   * @param portfolioName name of the portfolio
   * @param portfolioType the type of portfolio
   * @return the opening value of the portfolio on the given date
   */
  float getPortfolioValue(String date, String portfolioName, String portfolioType);

  /**
   * Check if a given date matches the yyyy-mm-dd format.
   *
   * @param date the date to check
   * @return true if the date is valid, false otherwise
   */
  boolean validDateFormat(String date);

  /**
   * Check if a portfolio exists or not.
   *
   * @param portfolioName name of the portfolio to check
   * @param portfolioType the type of portfolio
   * @return true if the portfolio exists, false otherwise
   */
  boolean checkPortfolioName(String portfolioName, String portfolioType);

  /**
   * Check if the stock amount is a positive integer.
   *
   * @param quantity quantity of stock
   * @return true if the stock amount is valid, false otherwise
   */
  boolean validAmount(String quantity);

  /**
   * Check if the given string is empty.
   *
   * @param s input string
   * @return true if the string is empty, false otherwise
   */
  boolean checkEmptyInput(String s);

  /**
   * Add data to an existing portfolio.
   *
   * @param portfolioName name of the portfolio
   * @param contents the content to be added
   * @param portfolioType the type of portfolio
   */
  void appendPortfolio(String portfolioName, String contents, String portfolioType);

  /**
   * Check if a stock sale on a particular date is valid.
   *
   * @param portfolioName name of the portfolio
   * @param portfolioType the type of portfolio
   * @param date date of sale
   * @param stock stock to be sold
   * @param amount amount of stock to be sold
   * @return true if the sale is valid, false otherwise
   */
  boolean checkValidStockSell(String portfolioName, String portfolioType,
                              String date, String stock, String amount);

  /**
   * Check if the stock date is valid.
   *
   * @param date date to check
   * @param stock name of stock
   * @return true if the date is valid, false otherwise
   */
  boolean validStockDate(String date, String stock);

  /**
   * Get the cost basis of the portfolio for a certain date.
   *
   * @param portfolioName name of the portfolio
   * @param portfolioType the type of portfolio
   * @param date date for the cost basis
   * @return the cost basis
   */
  float getCostBasis(String portfolioName, String date, String portfolioType);

  /**
   * Split the given time range and get value for each time slot.
   *
   * @param portfolioName name of the portfolio
   * @param portfolioType the type of portfolio
   * @param start start date for the range
   * @param end end date for the range
   * @return a map of date and value on date
   */
  LinkedHashMap<String, Float> splitTimeRange(String start, String end,
                                              String portfolioName, String portfolioType);

  /**
   * Normalize the map of dates and value on date.
   *
   * @param dateMap a map of date and value on date
   * @return a map of date and normalized value on date
   */
  LinkedHashMap<String, Float> normHash(LinkedHashMap<String, Float> dateMap);

  /**
   * Check if the stock amount is a decimal.
   *
   * @param quantity is the quantity of stock
   * @return true if it is a decimal, false otherwise
   */

  boolean validAmountDecimal(String quantity);

  /**
   * Checks if the total percentage of stocks adds upto 100.
   *
   * @param stockMap is hashmap of stocks and the percentage of stocks
   * @return true if it adds upto 100, false otherwise
   */

  boolean validDCAPercentage(LinkedHashMap<String, Float> stockMap);

  /**
   * Stores the DCA strategy in a file.
   *
   * @param money the amount of money to be invested
   * @param stockMap is hashmap of stocks and the percentage of stocks
   * @param portfolioName name of the portfolio
   * @param start start date for the range
   * @param end end date for the range
   * @param interval the frequency for investing
   * @param commission amount of money to be commissioned
   * @param portfolioType type of portfolio
   */

  void dollarCostAveraging(int money, LinkedHashMap<String, Float> stockMap, String portfolioName,
                      String start, String end, int interval, String commission,
                      String portfolioType);

  /**
   * Calculates the amount of money to be invested based on the percentage given.
   *
   * @param money the amount of money to be invested
   * @param stockMap is hashmap of stocks and the percentage of stocks
   * @param date date for the cost basis
   * @param commission amount of money to be commissioned
   * @return returns a string of contents in the required format to be stored in the portfolio
   */

  String investByWeight(int money, LinkedHashMap<String, Float> stockMap,
                 String date, String commission);

}
