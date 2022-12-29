import java.util.LinkedHashMap;

/**
 * A mock class for the Model for testing.
 * This class implements methods from the IModel interface.
 * The methods in this class update the model log used for testing.
 */
public class MockModel implements IModel {
  StringBuilder modelLog;

  public MockModel(StringBuilder modelLog) {
    this.modelLog = modelLog;
  }

  /**
   * Creates a portfolio containing stock names and number
   * of stocks.
   *
   * @param portfolioName name of the portfolio
   * @param contents      the stock names and amounts
   */

  @Override
  public void writePortfolio(String portfolioName, String contents, String portfolioType) {
    modelLog.append("WritePortfolio called");
    return;
  }

  /**
   * Returns the stock names and amounts for a particular portfolio.
   *
   * @param portfolioName name of the portfolio
   * @return the stock names and amounts for the portfolio
   */
  @Override
  public String readPortfolio(String portfolioName, String portfolioType) {
    modelLog.append("readPortfolio called");
    return null;
  }

  /**
   * Checks if a valid stock name has been given.
   *
   * @param portfolioName name of the portfolio
   * @return true if the stock name is valid, false otherwise
   */
  @Override
  public boolean checkStockName(String portfolioName) {
    modelLog.append("checkStockName called");
    return true;
  }

  /**
   * Get the price of a stock at a particular date.
   *
   * @param date  the date at which the price has to be found
   * @param stock name of the stock
   * @return the opening price of the stock on the given date
   */
  @Override
  public float getPriceAtDate(String date, String stock) {
    modelLog.append("getPriceAtDate called");
    return 0;
  }

  /**
   * Get the value of a portfolio at a particular date.
   *
   * @param date          the date at which the value has to be found
   * @param portfolioName name of the portfolio
   * @return the opening value of the portfolio on the given date
   */
  @Override
  public float getPortfolioValue(String date, String portfolioName, String portfolioType) {
    modelLog.append("getPortfolioValue called");
    return 0;
  }

  /**
   * Check if a given date matches the yyyy-mm-dd format.
   *
   * @param date the date to check
   * @return true if the date is valid, false otherwise
   */
  @Override
  public boolean validDateFormat(String date) {
    modelLog.append("validDateFormat called");
    return true;
  }

  /**
   * Check if a portfolio exists or not.
   *
   * @param portfolioName name of the portfolio to check
   * @return true if the portfolio exists, false otherwise
   */
  @Override
  public boolean checkPortfolioName(String portfolioName, String portfolioType) {
    modelLog.append("checkPortfolioName called");
    return false;
  }

  /**
   * Check if the stock amount is a positive integer.
   *
   * @param quantity quantity of stock
   * @return true if the stock amount is valid, false otherwise
   */
  @Override
  public boolean validAmount(String quantity) {
    modelLog.append("validStockAmount called");
    return true;
  }

  /**
   * Check if the given string is empty.
   *
   * @param s input string
   * @return true if the string is empty, false otherwise
   */
  @Override
  public boolean checkEmptyInput(String s) {
    modelLog.append("checkEmptyInput called");
    return false;
  }

  @Override
  public void appendPortfolio(String portfolioName, String contents, String portfolioType) {
    modelLog.append("appendPortfolio called");
    return;
  }

  @Override
  public boolean checkValidStockSell(String portfolioName, String portfolioType,
                                     String date, String stock, String amount) {
    modelLog.append("checkValidStockSell called");
    return true;
  }

  @Override
  public boolean validStockDate(String date, String stock) {
    modelLog.append("validStockDate called");
    return true;
  }

  @Override
  public float getCostBasis(String portfolioName, String date, String portfolioType) {
    modelLog.append("getCostBasis called");
    return 0.0F;
  }

  @Override
  public LinkedHashMap<String, Float> splitTimeRange(String start, String end, String portfolioName,
                                                     String portfolioType) {
    modelLog.append("splitTimeRange called");
    return new LinkedHashMap<>();
  }

  @Override
  public LinkedHashMap<String, Float> normHash(LinkedHashMap<String, Float> dateMap) {
    modelLog.append("normHash called");
    return new LinkedHashMap<>();
  }

  @Override
  public boolean validAmountDecimal(String quantity) {
    modelLog.append("validAmountDecimal called");
    return false;
  }

  @Override
  public boolean validDCAPercentage(LinkedHashMap<String, Float> stockMap) {
    modelLog.append("validDCAPercentage called");
    return false;
  }

  @Override
  public void dollarCostAveraging(int money, LinkedHashMap<String,
          Float> stockMap, String portfolioName, String start, String end, int interval,
                                  String commission, String portfolioType) {
    modelLog.append("dollarCostAveraging called");
  }

  @Override
  public String investByWeight(int money, LinkedHashMap<String, Float> stockMap, String date,
                               String commission) {
    modelLog.append("investByWeight called");
    return null;
  }
}