import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the JFrameController class.
 * This class contains methods that tests the functionality of the
 * GUI controller.
 */
public class JFrameControllerTest {

  IFrameView view;
  IModel model;
  JFrameController controller;
  StringBuilder viewLog = new StringBuilder();

  @Before
  public void setup() {
    view = new MockJFrameView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    controller = new JFrameController(model);

    controller.setView(view);
  }

  @Test
  public void showError() {
    controller.showError("");
    assertEquals("In view addFeatures()\n"
            + "In view displayError()\n"
            + "In view addFeaturesErrorMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuError() {
    controller.backMenuError();
    assertEquals("In view addFeatures()\n" +
            "In view removeError()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showBuy() {
    controller.showBuy();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayBuyStock()\n" +
            "In view addFeaturesBuyMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuBuyStock() {
    controller.backMenuBuyStock();
    assertEquals("In view addFeatures()\n" +
            "In view removeBuyStock()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void submitBuy() {
    controller.submitBuy("", "", "", "", "");
    assertEquals("In view addFeatures()\n" +
            "In view removeBuyStock()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showSell() {
    controller.showSell();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displaySellStock()\n" +
            "In view addFeaturesSellMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuSellStock() {
    controller.backMenuSellStock();
    assertEquals("In view addFeatures()\n" +
            "In view removeSellStock()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void submitSell() {
    StringBuilder modelLog = new StringBuilder();
    IModel modelExisting = new MockModelExistingPortfolio(modelLog);
    JFrameController controllerExisting = new JFrameController(modelExisting);
    controllerExisting.setView(view);

    controllerExisting.submitSell("", "", "", "", "");
    assertEquals("In view addFeatures()\n" +
            "In view addFeatures()\n" +
            "In view removeSellStock()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showPortfolio() {
    controller.showPortfolio();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayEnterPortfolioName()\n" +
            "In view addFeaturesPortfolioNameMenu()\n", viewLog.toString());
  }

  @Test
  public void submitPortfolioName() {
    view = new MockJFrameView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new JFrameController(model);
    controller.setView(view);

    controller.submitPortfolioName("");
    assertEquals("In view addFeatures()\n" +
            "In view addFeatures()\n" +
            "In view removeEnterPortfolioName()\n" +
            "In view displayPortfolio()\n" +
            "In view addFeaturesDisplayPortfolioMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuDisplayPortfolio() {
    controller.backMenuDisplayPortfolio();
    assertEquals("In view addFeatures()\n" +
            "In view removeDisplayPortfolio()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void backMenuPortfolioStock() {
    controller.backMenuPortfolioStock();
    assertEquals("In view addFeatures()\n" +
            "In view removeEnterPortfolioName()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showDollarCostAveraging() {
    controller.showDollarCostAveraging();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayDollarCostAveraging()\n" +
            "In view addFeaturesDCAMenu()\n", viewLog.toString());
  }

  @Test
  public void submitDCA() {
    StringBuilder modelLog = new StringBuilder();
    IModel modelExisting = new MockModelExistingPortfolio(modelLog);
    JFrameController controllerExisting = new JFrameController(modelExisting);
    controllerExisting.setView(view);

    controllerExisting.submitDCA("", "A", "0", "", "", "", "", "");
    assertEquals("In view addFeatures()\n" +
            "In view addFeatures()\n" +
            "In view removeDollarCostAveraging()\n" +
            "In view displayError()\n" +
            "In view addFeaturesErrorMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuDCA() {
    controller.backMenuDCA();
    assertEquals("In view addFeatures()\n" +
            "In view removeDollarCostAveraging()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showBuyWeight() {
    controller.showBuyWeight();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayBuyWeight()\n" +
            "In view addFeaturesWeightMenu()\n", viewLog.toString());
  }

  @Test
  public void submitBuyWeight() {
    StringBuilder modelLog = new StringBuilder();
    IModel modelExisting = new MockModelExistingPortfolio(modelLog);
    JFrameController controllerExisting = new JFrameController(modelExisting);
    controllerExisting.setView(view);

    controllerExisting.submitBuyWeight("", "A", "0", "", "", "");
    assertEquals("In view addFeatures()\n" +
            "In view addFeatures()\n" +
            "In view removeBuyWeight()\n" +
            "In view displayError()\n" +
            "In view addFeaturesErrorMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuBuyWeight() {
    controller.backMenuBuyWeight();
    assertEquals("In view addFeatures()\n" +
            "In view removeBuyWeight()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showCostInput() {
    controller.showCostInput();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayCostInput()\n" +
            "In view addFeaturesCostInputMenu()\n", viewLog.toString());
  }

  @Test
  public void submitCostInput() {
    controller.showError("");
    assertEquals("In view addFeatures()\n"
            + "In view displayError()\n"
            + "In view addFeaturesErrorMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuCostInput() {
    controller.backMenuCostInput();
    assertEquals("In view addFeatures()\n" +
            "In view removeCostInput()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void showValueInput() {
    controller.showValueInput();
    assertEquals("In view addFeatures()\n" +
            "In view removeMainMenu()\n" +
            "In view displayValueInput()\n" +
            "In view addFeaturesValueInputMenu()\n", viewLog.toString());
  }

  @Test
  public void submitValueInput() {
    view = new MockJFrameView(viewLog);
    StringBuilder modelLog = new StringBuilder();
    model = new MockModelExistingPortfolio(modelLog);
    controller = new JFrameController(model);
    controller.setView(view);

    controller.submitValueInput("", "");
    assertEquals("In view addFeatures()\n" +
            "In view addFeatures()\n" +
            "In view removeValueInput()\n" +
            "In view displayCostValue()\n" +
            "In view addFeaturesCostValueMenu()\n", viewLog.toString());
  }

  @Test
  public void backMenuValueInput() {
    controller.backMenuValueInput();
    assertEquals("In view addFeatures()\n" +
            "In view removeValueInput()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }

  @Test
  public void backMenuCostValue() {
    controller.backMenuCostValue();
    assertEquals("In view addFeatures()\n" +
            "In view removeCostValue()\n" +
            "In view displayMainMenu()\n" +
            "In view addFeatures()\n", viewLog.toString());
  }
}