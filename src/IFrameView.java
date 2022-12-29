/**
 * This interface represents the GUI View portion of MVC architecture
 * for the stock portfolio.
 * It has methods that displays options and menus.
 * None of the methods return anything.
 */
public interface IFrameView {

  /**
   * Displays the main menu options.
   */
  void displayMainMenu();

  /**
   * Remove the main menu options.
   */
  void removeMainMenu();

  /**
   * Displays the buy stock options.
   */
  void displayBuyStock();

  /**
   * Removes the buy stock options.
   */
  void removeBuyStock();

  /**
   * Displays the sell stock options.
   */
  void displaySellStock();

  /**
   * Removes the sell stock options.
   */
  void removeSellStock();

  /**
   * Displays the portfolio in a table.
   *
   * @param s the portfolio data
   */
  void displayPortfolio(String s);

  /**
   * Removes the portfolio data.
   */
  void removeDisplayPortfolio();

  /**
   * Displays the portfolio name options.
   */
  void displayEnterPortfolioName();

  /**
   * Removes the portfolio name options.
   */
  void removeEnterPortfolioName();

  /**
   * Displays the Dollar cost averaging options.
   */
  void displayDollarCostAveraging();

  /**
   * Removes the Dollar cost averaging options.
   */
  void removeDollarCostAveraging();

  /**
   * Displays the buy by weight options.
   */
  void displayBuyWeight();

  /**
   * Removes the buy by weight options.
   */
  void removeBuyWeight();

  /**
   * Displays the value input options.
   */
  void displayValueInput();

  /**
   * Removes the value input options.
   */
  void removeValueInput();

  /**
   * Displays the cost input options.
   */
  void displayCostInput();

  /**
   * Removes the cost input options.
   */
  void removeCostInput();

  /**
   * Displays the cost/value of a portfolio.
   *
   * @param amount the cost/value
   * @param type the type, cost or value
   */
  void displayCostValue(String type, float amount);

  /**
   * Removes the cost/value of a portfolio.
   */
  void removeCostValue();

  /**
   * Displays the error message.
   *
   * @param error the message
   */
  void displayError(String error);

  /**
   * Removes the error message.
   */
  void removeError();

  /**
   * Adds features for the main menu.
   *
   * @param features the features object
   */
  void addFeatures(Features features);

  /**
   * Adds features for the buy menu.
   *
   * @param features the features object
   */
  void addFeaturesBuyMenu(Features features);

  /**
   * Adds features for the sell menu.
   *
   * @param features the features object
   */
  void addFeaturesSellMenu(Features features);

  /**
   * Adds features for the main menu.
   *
   * @param features the features object
   */
  void addFeaturesPortfolioNameMenu(Features features);

  /**
   * Adds features for the display portfolio menu.
   *
   * @param features the features object
   */
  void addFeaturesDisplayPortfolioMenu(Features features);

  /**
   * Adds features for the dollar cost averaging menu.
   *
   * @param features the features object
   */
  void addFeaturesDCAMenu(Features features);

  /**
   * Adds features for the buy by weight menu.
   *
   * @param features the features object
   */
  void addFeaturesWeightMenu(Features features);

  /**
   * Adds features for the cost input menu.
   *
   * @param features the features object
   */
  void addFeaturesCostInputMenu(Features features);

  /**
   * Adds features for the value input menu.
   *
   * @param features the features object
   */
  void addFeaturesValueInputMenu(Features features);

  /**
   * Adds features for the cost/value menu.
   *
   * @param features the features object
   */
  void addFeaturesCostValueMenu(Features features);

  /**
   * Adds features for the error menu.
   *
   * @param features the features object
   */
  void addFeaturesErrorMenu(Features features);
}