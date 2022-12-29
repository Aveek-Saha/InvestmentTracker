import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the TextController class.
 * This class contains methods that tests the functionality of the controller.
 */
public class TextControllerTest {

  IView view;
  IModel model;
  IController controller;
  OutputStream out;
  InputStream in;
  String lineSep = System.lineSeparator();

  @Test
  public void startMenuQuit() {
    in = new ByteArrayInputStream("Q".getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals("In view showPortfolioOptions()\n", viewLog.toString());
  }

  @Test
  public void startMenuCreatePortfolio() {
    String inputString = "2" + lineSep
            + "1" + lineSep
            + "port1" + lineSep
            + "AAPL" + lineSep
            + "5" + lineSep
            + "n" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptions()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringEnterStockName()\n"
            + "In view showStringEnterStockAmount()\n"
            + "In view showStringEnterMoreStocks()\n"
            + "In view showOptions()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuCreatePortfolioInvalidName() {
    String inputString = "2" + lineSep
            + "1" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptions()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringExistingPortfolioName()\n"
            + "In view showOptions()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuViewPortfolioInvalidName() {
    String inputString = "2" + lineSep
            + "2" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptions()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringWrongPortfolioName()\n"
            + "In view showOptions()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuValuePortfolioInvalidName() {
    String inputString = "3" + lineSep
            + "port1" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionError()\n"
            + "In view showPortfolioOptions()\n"
            + "In view showOptionError()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuViewPortfolio() {
    String inputString = "2" + lineSep
            + "2" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q" + lineSep;
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptions()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showString()\n"
            + "In view showOptions()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuValuePortfolio() {
    String inputString = "2" + lineSep
            + "3" + lineSep
            + "port1" + lineSep
            + "2022-01-27" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptions()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringEnterDate()\n"
            + "In view showValue()\n"
            + "In view showOptions()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }


  @Test
  public void startMenuCreatePortfolioV2() {
    String inputString = "1" + lineSep
            + "1" + lineSep
            + "port1" + lineSep
            + "AAPL" + lineSep
            + "5" + lineSep
            + "2022-07-29" + lineSep
            + "2" + lineSep
            + "n" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionsV2()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringEnterStockName()\n"
            + "In view showStringEnterStockAmount()\n"
            + "In view showStringEnterDate()\n"
            + "In view showStringEnterCommission()\n"
            + "In view showStringEnterMoreStocks()\n"
            + "In view showOptionsV2()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuViewPortfolioV2InvalidName() {
    String inputString = "1" + lineSep
            + "5" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionsV2()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringWrongPortfolioName()\n"
            + "In view showOptionsV2()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuValuePortfolioV2InvalidName() {
    String inputString = "1" + lineSep
            + "4" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionsV2()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringWrongPortfolioName()\n"
            + "In view showOptionsV2()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuViewPortfolioV2() {
    String inputString = "1" + lineSep
            + "5" + lineSep
            + "port1" + lineSep
            + "Q" + lineSep
            + "Q" + lineSep;
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionsV2()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showString()\n"
            + "In view showOptionsV2()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }

  @Test
  public void startMenuValuePortfolioV2() {
    String inputString = "1" + lineSep
            + "4" + lineSep
            + "port1" + lineSep
            + "2022-01-27" + lineSep
            + "Q" + lineSep
            + "Q";
    String expectedOutput = "In view showPortfolioOptions()\n"
            + "In view showOptionsV2()\n"
            + "In view showStringEnterPortfolioName()\n"
            + "In view showStringEnterDate()\n"
            + "In view showValue()\n"
            + "In view showOptionsV2()\n"
            + "In view showPortfolioOptions()\n";
    in = new ByteArrayInputStream(inputString.getBytes());
    out = new ByteArrayOutputStream();

    StringBuilder viewLog = new StringBuilder();
    view = new MockView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new TextController(model, in, view);

    controller.startMenu();
    assertEquals(expectedOutput, viewLog.toString());
  }
}