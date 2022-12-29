import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the TextView class.
 * This class contains methods that tests the functionality of the view.
 */
public class TextViewTest {

  IView view;
  OutputStream out;
  String lineSep = System.lineSeparator();

  @Before
  public void setup() {
    out = new ByteArrayOutputStream();
    view = new TextView(new PrintStream(out));
  }

  @Test
  public void showString() {
    view.showString("test");
    assertEquals("test" + lineSep, out.toString());
  }

  @Test
  public void showOptions() {
    view.showOptions();
    assertEquals(lineSep + "1: Create a portfolio"
            + lineSep + "2: Display a portfolio"
            + lineSep + "3: Get value of portfolio"
            + lineSep + "Q: Quit"
            + lineSep + "Enter your choice: ", out.toString());
  }

  @Test
  public void showStringEnterPortfolioName() {
    view.showStringEnterPortfolioName();
    assertEquals("\nEnter portfolio name: ", out.toString());
  }

  @Test
  public void showStringWrongPortfolioName() {
    view.showStringWrongPortfolioName();
    assertEquals("\nThe portfolio name entered does not exist", out.toString());
  }

  @Test
  public void showStringEnterStockName() {
    view.showStringEnterStockName();
    assertEquals("\nEnter name of the stock: ", out.toString());
  }

  @Test
  public void showStringWrongStockName() {
    view.showStringWrongStockName();
    assertEquals("\nThe stock name you entered does not exist", out.toString());
  }

  @Test
  public void showStringExistingPortfolioName() {
    view.showStringExistingPortfolioName();
    assertEquals("\nThe portfolio name you entered already exists", out.toString());
  }

  @Test
  public void showStringEnterMoreStocks() {
    view.showStringEnterMoreStocks();
    assertEquals("\nDo you want to add another stock? [y/n]: ", out.toString());
  }

  @Test
  public void showStringEnterStockAmount() {
    view.showStringEnterStockAmount();
    assertEquals("\nEnter the number of stocks: ", out.toString());
  }

  @Test
  public void showStringEnterDate() {
    view.showStringEnterDate();
    assertEquals("\nEnter the date [YYYY-MM-DD]: ", out.toString());
  }

  @Test
  public void showStringWrongDate() {
    view.showStringWrongDate();
    assertEquals("\nThe date format is incorrect", out.toString());
  }

  @Test
  public void showStringWrongStockAmount() {
    view.showStringWrongStockAmount();
    assertEquals("\nThe Stock amount must be a positive integer", out.toString());
  }

  @Test
  public void showValue() {
    view.showValue("20");
    assertEquals("\nThe value of the portfolio on the given date is = $20"
            + lineSep, out.toString());
  }

  @Test
  public void showOptionError() {
    view.showOptionError();
    assertEquals("\nInvalid option. Please try again.", out.toString());
  }

  @Test
  public void showOptionsV2() {
    view.showOptionsV2();
    assertEquals(lineSep + "1: Buy stocks"
            + lineSep + "2: Sell stocks"
            + lineSep + "3: Get cost basis for a portfolio"
            + lineSep + "4: Get value of portfolio"
            + lineSep + "5: Display a portfolio"
            + lineSep + "6: Show portfolio performance over time"
            + lineSep + "Q: Quit"
            + lineSep + "Enter your choice: ", out.toString());
  }

  @Test
  public void showPortfolioOptions() {
    view.showPortfolioOptions();
    assertEquals("\n\nSelect the type of portfolio: "
            + lineSep + "1: Flexible portfolio (v2)"
            + lineSep + "2: Inflexible portfolio (v1)"
            + lineSep + "Q: Quit"
            + lineSep + "Enter your choice: ", out.toString());
  }

  @Test
  public void showStringWrongSell() {
    view.showStringWrongSell();
    assertEquals("\nThe amount of stock you are trying to sell on the date is invalid",
            out.toString());
  }

  @Test
  public void showCostBasis() {
    view.showCostBasis("10");
    assertEquals("\nThe cost basis of the portfolio on the given date is = $10" + lineSep,
            out.toString());
  }

  @Test
  public void showStringEnterCommission() {
    view.showStringEnterCommission();
    assertEquals("\nEnter the commission amount ($): ", out.toString());
  }

  @Test
  public void showStringWrongCommission() {
    view.showStringWrongCommission();
    assertEquals("\nThe commission amount must be a positive integer", out.toString());
  }

  @Test
  public void showPerformanceOverTime() {
    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();
    dateMap.put("date 1", 13F);
    dateMap.put("date 2", 12F);
    dateMap.put("date 3", 5F);
    dateMap.put("date 4", 6F);
    dateMap.put("date 5", 18F);
    view.showPerformanceOverTime(5, "date 1", "date 5", "Portfolio", dateMap);
    assertEquals("\nPerformance of portfolio Portfolio from date 1 to date 5" + lineSep
            + lineSep
            + "date 1: *************" + lineSep
            + "date 2: ************" + lineSep
            + "date 3: *****" + lineSep
            + "date 4: ******" + lineSep
            + "date 5: ******************" + lineSep
            + lineSep
            + "Scale: * = 5" + lineSep, out.toString());
  }

  @Test
  public void showStringEnterStartDate() {
    view.showStringEnterStartDate();
    assertEquals("\nEnter the start date [YYYY-MM-DD]: ", out.toString());
  }

  @Test
  public void showStringEnterEndDate() {
    view.showStringEnterEndDate();
    assertEquals("\nEnter the end date [YYYY-MM-DD]: ", out.toString());
  }

  @Test
  public void showStringWrongDateRange() {
    view.showStringWrongDateRange();
    assertEquals("\nThe date range entered cannot be shown on the chart", out.toString());
  }
}