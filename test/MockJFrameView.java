
/**
 * A mock class for the GUI View for testing.
 * This class implements all methods from the IFrameView interface.
 * The methods append strings to the view log used for testing.
 */
public class MockJFrameView implements IFrameView {

  StringBuilder viewLog;

  public MockJFrameView(StringBuilder viewLog) {
    this.viewLog = viewLog;
  }

  @Override
  public void displayMainMenu() {
    viewLog.append("In view displayMainMenu()\n");
    return;
  }

  @Override
  public void removeMainMenu() {
    viewLog.append("In view removeMainMenu()\n");
    return;
  }

  @Override
  public void displayBuyStock() {
    viewLog.append("In view displayBuyStock()\n");
    return;
  }

  @Override
  public void removeBuyStock() {
    viewLog.append("In view removeBuyStock()\n");
    return;
  }

  @Override
  public void displaySellStock() {
    viewLog.append("In view displaySellStock()\n");
    return;
  }

  @Override
  public void removeSellStock() {
    viewLog.append("In view removeSellStock()\n");
    return;
  }

  @Override
  public void displayPortfolio(String s) {
    viewLog.append("In view displayPortfolio()\n");
    return;
  }

  @Override
  public void removeDisplayPortfolio() {
    viewLog.append("In view removeDisplayPortfolio()\n");
    return;
  }

  @Override
  public void displayEnterPortfolioName() {
    viewLog.append("In view displayEnterPortfolioName()\n");
    return;
  }

  @Override
  public void removeEnterPortfolioName() {
    viewLog.append("In view removeEnterPortfolioName()\n");
    return;
  }

  @Override
  public void displayDollarCostAveraging() {
    viewLog.append("In view displayDollarCostAveraging()\n");
    return;
  }

  @Override
  public void removeDollarCostAveraging() {
    viewLog.append("In view removeDollarCostAveraging()\n");
    return;
  }

  @Override
  public void displayBuyWeight() {
    viewLog.append("In view displayBuyWeight()\n");
    return;
  }

  @Override
  public void removeBuyWeight() {
    viewLog.append("In view removeBuyWeight()\n");
    return;
  }

  @Override
  public void displayValueInput() {
    viewLog.append("In view displayValueInput()\n");
    return;
  }

  @Override
  public void removeValueInput() {
    viewLog.append("In view removeValueInput()\n");
    return;
  }

  @Override
  public void displayCostInput() {
    viewLog.append("In view displayCostInput()\n");
    return;
  }

  @Override
  public void removeCostInput() {
    viewLog.append("In view removeCostInput()\n");
    return;
  }

  @Override
  public void displayCostValue(String type, float amount) {
    viewLog.append("In view displayCostValue()\n");
    return;
  }

  @Override
  public void removeCostValue() {
    viewLog.append("In view removeCostValue()\n");
    return;
  }

  @Override
  public void displayError(String error) {
    viewLog.append("In view displayError()\n");
    return;
  }

  @Override
  public void removeError() {
    viewLog.append("In view removeError()\n");
    return;
  }

  @Override
  public void addFeatures(Features features) {
    viewLog.append("In view addFeatures()\n");
    return;
  }

  @Override
  public void addFeaturesBuyMenu(Features features) {
    viewLog.append("In view addFeaturesBuyMenu()\n");
    return;
  }

  @Override
  public void addFeaturesSellMenu(Features features) {
    viewLog.append("In view addFeaturesSellMenu()\n");
    return;
  }

  @Override
  public void addFeaturesPortfolioNameMenu(Features features) {
    viewLog.append("In view addFeaturesPortfolioNameMenu()\n");
    return;
  }

  @Override
  public void addFeaturesDisplayPortfolioMenu(Features features) {
    viewLog.append("In view addFeaturesDisplayPortfolioMenu()\n");
    return;
  }

  @Override
  public void addFeaturesDCAMenu(Features features) {
    viewLog.append("In view addFeaturesDCAMenu()\n");
    return;
  }

  @Override
  public void addFeaturesWeightMenu(Features features) {
    viewLog.append("In view addFeaturesWeightMenu()\n");
    return;
  }

  @Override
  public void addFeaturesCostInputMenu(Features features) {
    viewLog.append("In view addFeaturesCostInputMenu()\n");
    return;
  }

  @Override
  public void addFeaturesValueInputMenu(Features features) {
    viewLog.append("In view addFeaturesValueInputMenu()\n");
    return;
  }

  @Override
  public void addFeaturesCostValueMenu(Features features) {
    viewLog.append("In view addFeaturesCostValueMenu()\n");
    return;
  }

  @Override
  public void addFeaturesErrorMenu(Features features) {
    viewLog.append("In view addFeaturesErrorMenu()\n");
    return;
  }
}
