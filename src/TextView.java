import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * This class represents the implementation of the View portion of MVC architecture
 * for the stock portfolio.
 * This class contains all methods mentioned in the IView interface.
 * This class has methods displays strings, options and menus.
 */
public class TextView implements IView {
  private final PrintStream out;

  /**
   * Constructs a new TextView object initialized with the output stream.
   *
   * @param out the output stream
   */
  public TextView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void showString(String s) {
    out.println(s);
  }

  @Override
  public void showOptions() {
    out.println();
    out.println("1: Create a portfolio");
    out.println("2: Display a portfolio");
    out.println("3: Get value of portfolio");
    out.println("Q: Quit");
    out.print("Enter your choice: ");
  }

  @Override
  public void showOptionsV2() {
    out.println();
    out.println("1: Buy stocks");
    out.println("2: Sell stocks");
    out.println("3: Get cost basis for a portfolio");
    out.println("4: Get value of portfolio");
    out.println("5: Display a portfolio");
    out.println("6: Show portfolio performance over time");
    out.println("Q: Quit");
    out.print("Enter your choice: ");
  }

  @Override
  public void showPortfolioOptions() {
    out.println("\n\nSelect the type of portfolio: ");
    out.println("1: Flexible portfolio (v2)");
    out.println("2: Inflexible portfolio (v1)");
    out.println("Q: Quit");
    out.print("Enter your choice: ");
  }

  @Override
  public void showStringEnterPortfolioName() {
    out.print("\nEnter portfolio name: ");
  }

  @Override
  public void showStringWrongPortfolioName() {
    out.print("\nThe portfolio name entered does not exist");
  }

  @Override
  public void showStringEnterStockName() {
    out.print("\nEnter name of the stock: ");
  }

  @Override
  public void showStringWrongStockName() {
    out.print("\nThe stock name you entered does not exist");
  }

  @Override
  public void showStringExistingPortfolioName() {
    out.print("\nThe portfolio name you entered already exists");
  }

  @Override
  public void showStringEnterMoreStocks() {
    out.print("\nDo you want to add another stock? [y/n]: ");
  }

  @Override
  public void showStringEnterStockAmount() {
    out.print("\nEnter the number of stocks: ");
  }

  @Override
  public void showStringEnterDate() {
    out.print("\nEnter the date [YYYY-MM-DD]: ");
  }

  @Override
  public void showStringEnterStartDate() {
    out.print("\nEnter the start date [YYYY-MM-DD]: ");
  }

  @Override
  public void showStringEnterEndDate() {
    out.print("\nEnter the end date [YYYY-MM-DD]: ");
  }

  @Override
  public void showStringWrongDateRange() {
    out.print("\nThe date range entered cannot be shown on the chart");
  }

  @Override
  public void showStringEnterCommission() {
    out.print("\nEnter the commission amount ($): ");
  }

  @Override
  public void showStringWrongSell() {
    out.print("\nThe amount of stock you are trying to sell on the date is invalid");
  }

  @Override
  public void showStringWrongDate() {
    out.print("\nThe date format is incorrect");
  }

  @Override
  public void showStringWrongStockAmount() {
    out.print("\nThe Stock amount must be a positive integer");
  }

  @Override
  public void showStringWrongCommission() {
    out.print("\nThe commission amount must be a positive integer");
  }

  @Override
  public void showValue(String s) {
    out.println("\nThe value of the portfolio on the given date is = $" + s);
  }

  @Override
  public void showCostBasis(String s) {
    out.println("\nThe cost basis of the portfolio on the given date is = $" + s);
  }

  @Override
  public void showPerformanceOverTime(int scale, String startDate, String endDate,
                                      String portfolioName, LinkedHashMap<String, Float> dateMap) {
    out.println("\nPerformance of portfolio " + portfolioName + " from "
            + startDate + " to " + endDate + "\n");
    for (String d : dateMap.keySet()) {
      float val = dateMap.get(d);
      out.println(d + ": " + "*".repeat((int) val));
    }
    out.println("\nScale: * = " + scale);
  }

  @Override
  public void showOptionError() {
    out.print("\nInvalid option. Please try again.");
  }

}
