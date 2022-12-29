import java.util.LinkedHashMap;

/**
 * A mock class for the View for testing.
 * This class implements all methods from the IView interface.
 * The methods append strings to the view log used for testing.
 */
public class MockView implements IView {

  StringBuilder viewLog;

  public MockView(StringBuilder viewLog) {
    this.viewLog = viewLog;
  }

  /**
   * Displays the string passed to it.
   *
   * @param s the string to be displayed
   */
  @Override
  public void showString(String s) {
    viewLog.append("In view showString()\n");
    return;
  }

  /**
   * Displays the menu options.
   */
  @Override
  public void showOptions() {
    viewLog.append("In view showOptions()\n");
    return;
  }

  /**
   * Displayed when the menu option entered by the user is incorrect.
   */
  @Override
  public void showOptionError() {
    viewLog.append("In view showOptionError()\n");
    return;
  }

  /**
   * Displayed when the menu option entered by the user is incorrect.
   */
  @Override
  public void showStringEnterPortfolioName() {
    viewLog.append("In view showStringEnterPortfolioName()\n");
    return;
  }

  /**
   * Asks the user to enter the name of the stock.
   */
  @Override
  public void showStringEnterStockName() {
    viewLog.append("In view showStringEnterStockName()\n");
    return;
  }

  /**
   * Asks the user to enter the amount of stock to add to the portfolio.
   */
  @Override
  public void showStringEnterStockAmount() {
    viewLog.append("In view showStringEnterStockAmount()\n");
    return;
  }

  /**
   * Asks the user to enter the date to calculate the portfolio value for.
   */
  @Override
  public void showStringEnterDate() {
    viewLog.append("In view showStringEnterDate()\n");
    return;
  }

  /**
   * Asks the user if they want to enter more stocks.
   */
  @Override
  public void showStringEnterMoreStocks() {
    viewLog.append("In view showStringEnterMoreStocks()\n");
    return;
  }

  /**
   * Displayed when the stock name entered by the user is invalid.
   */
  @Override
  public void showStringWrongStockName() {
    viewLog.append("In view showStringWrongStockName()\n");
    return;
  }

  /**
   * Displayed when the date entered by the user is invalid.
   */
  @Override
  public void showStringWrongDate() {
    viewLog.append("In view showStringWrongDate()\n");
    return;
  }

  /**
   * Displayed when the portfolio name entered by the user is invalid.
   */
  @Override
  public void showStringWrongPortfolioName() {
    viewLog.append("In view showStringWrongPortfolioName()\n");
    return;
  }

  /**
   * Displayed when the portfolio name entered by the user already exists.
   */
  @Override
  public void showStringExistingPortfolioName() {
    viewLog.append("In view showStringExistingPortfolioName()\n");
    return;
  }

  /**
   * Displays the total value of a portfolio.
   *
   * @param s the value of the portfolio
   */
  @Override
  public void showValue(String s) {
    viewLog.append("In view showValue()\n");
    return;
  }

  /**
   * Displayed when the stock amount entered by the user is invalid.
   */
  @Override
  public void showStringWrongStockAmount() {
    viewLog.append("In view showStringWrongStockAmount()\n");
    return;
  }

  @Override
  public void showOptionsV2() {
    viewLog.append("In view showOptionsV2()\n");
    return;
  }

  @Override
  public void showPortfolioOptions() {
    viewLog.append("In view showPortfolioOptions()\n");
    return;
  }

  @Override
  public void showStringWrongSell() {
    viewLog.append("In view showStringWrongSell()\n");
    return;
  }

  @Override
  public void showCostBasis(String s) {
    viewLog.append("In view showCostBasis()\n");
    return;
  }

  @Override
  public void showStringEnterCommission() {
    viewLog.append("In view showStringEnterCommission()\n");
    return;
  }

  @Override
  public void showStringWrongCommission() {
    viewLog.append("In view showStringWrongCommission()\n");
    return;
  }

  @Override
  public void showPerformanceOverTime(int scale, String startDate, String endDate,
                                      String portfolioName, LinkedHashMap<String, Float> dateMap) {
    viewLog.append("In view showPerformanceOverTime()\n");
    return;
  }

  @Override
  public void showStringEnterStartDate() {
    viewLog.append("In view showStringEnterStartDate()\n");
    return;
  }

  @Override
  public void showStringEnterEndDate() {
    viewLog.append("In view showStringEnterEndDate()\n");
    return;
  }

  @Override
  public void showStringWrongDateRange() {
    viewLog.append("In view showStringWrongDateRangef()\n");
    return;
  }
}
