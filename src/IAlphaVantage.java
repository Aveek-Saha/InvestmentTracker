/**
 * This interface represents the AlphaVantage API.
 * This interface offers operations such as checking the stock name
 * and getting the stock data.
 * Both the methods take String as an argument.
 */
public interface IAlphaVantage {
  /**
   * Checks if the stock name exists.
   * If it doesn't exist, the method gets it from the API.
   *
   * @param stock name of stock in String format.
   * @return true if the stock exists, false otherwise
   */
  boolean checkStockName(String stock);

  /**
   * Calculates price data from the stocks.
   * It does not return anything.
   *
   * @param stock name of stock.
   */
  void getStockData(String stock);
}

