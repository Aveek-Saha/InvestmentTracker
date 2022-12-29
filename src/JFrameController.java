import java.util.LinkedHashMap;

/**
 * This class represents the implementation of the GUI Controller portion of MVC architecture
 * for the stock portfolio.
 * It implements all the methods from thr Features interface.
 * This is where the GUI for flexible portfolios are supported.
 */
public class JFrameController implements Features {
  private final IModel model;
  private IFrameView view;

  /**
   * Constructs a new JFrameController object initialized with the model.
   *
   * @param m the model
   */
  public JFrameController(IModel m) {
    model = m;
  }

  /**
   * Initializes the view and adds the features.
   *
   * @param v the view
   */
  public void setView(IFrameView v) {
    view = v;

    view.addFeatures(this);
  }

  @Override
  public void showError(String error) {
    view.displayError(error);
    view.addFeaturesErrorMenu(this);
  }

  @Override
  public void backMenuError() {
    view.removeError();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void showBuy() {
    view.removeMainMenu();
    view.displayBuyStock();
    view.addFeaturesBuyMenu(this);
  }

  @Override
  public void backMenuBuyStock() {
    view.removeBuyStock();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void submitBuy(String portfolioName, String stock, String date, String quantity,
                        String commission) {
    boolean invalid = false;
    StringBuilder str = new StringBuilder();
    if (model.checkStockName(stock)) {
      str.append(stock);
      if (model.validAmount(quantity)) {
        str.append(",");
        str.append(quantity);
        if (model.validDateFormat(date) && model.validStockDate(date, stock)) {
          str.append(",");
          str.append(date);

          str.append(",");
          str.append("b");
          if (model.validAmount(commission)) {
            str.append(",");
            str.append(commission);
          } else {
            invalid = true;
            view.removeBuyStock();
            showError("Commission");
          }
        } else {
          invalid = true;
          view.removeBuyStock();
          showError("Date");
        }
      } else {
        invalid = true;
        view.removeBuyStock();
        showError("Stock quantity");
      }
    } else {
      invalid = true;
      view.removeBuyStock();
      showError("Stock name");
    }

    if (!invalid) {
      String portfolioType = "portfolio_v2";
      if (model.checkPortfolioName(portfolioName, portfolioType)) {
        model.appendPortfolio(portfolioName, str.toString(), portfolioType);
      } else {
        model.writePortfolio(portfolioName, str.toString(), portfolioType);
      }
      this.backMenuBuyStock();
    }
  }

  @Override
  public void showSell() {
    view.removeMainMenu();
    view.displaySellStock();
    view.addFeaturesSellMenu(this);
  }

  @Override
  public void backMenuSellStock() {
    view.removeSellStock();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void submitSell(String portfolioName, String stock, String date, String quantity,
                         String commission) {

    StringBuilder str = new StringBuilder();
    boolean valid = true;
    String portfolioType = "portfolio_v2";
    if (model.checkPortfolioName(portfolioName, portfolioType)) {
      if (model.checkStockName(stock)) {
        str.append(stock);
        if (model.validAmount(quantity)) {
          str.append(",");
          str.append(quantity);
          if (model.validDateFormat(date) && model.validStockDate(date, stock)) {
            str.append(",");
            str.append(date);

            str.append(",");
            str.append("s");
            if (model.validAmount(commission)) {
              str.append(",");
              str.append(commission);
              if (!model.checkValidStockSell(portfolioName, portfolioType, date, stock, quantity)) {
                view.removeSellStock();
                showError("Selling");
                valid = false;
              } else {
                model.appendPortfolio(portfolioName, str.toString(), portfolioType);
              }
            } else {
              view.removeSellStock();
              showError("Commission");
              valid = false;
            }
          } else {
            view.removeSellStock();
            showError("Date");
            valid = false;
          }
        } else {
          view.removeSellStock();
          showError("Stock quantity");
          valid = false;
        }
      } else {
        view.removeSellStock();
        showError("Stock name");
        valid = false;
      }
    } else {
      view.removeSellStock();
      showError("Portfolio Name");
      valid = false;
    }
    if (valid) {
      this.backMenuSellStock();
    }
  }

  @Override
  public void showPortfolio() {
    view.removeMainMenu();
    view.displayEnterPortfolioName();
    view.addFeaturesPortfolioNameMenu(this);
  }

  @Override
  public void submitPortfolioName(String portfolioName) {
    view.removeEnterPortfolioName();
    if (model.checkPortfolioName(portfolioName, "portfolio_v2")) {
      String portfolio = model.readPortfolio(portfolioName, "portfolio_v2");
      view.displayPortfolio(portfolio);
      view.addFeaturesDisplayPortfolioMenu(this);
    } else {
      view.removeEnterPortfolioName();
      showError("Portfolio Name");
    }
  }

  @Override
  public void backMenuDisplayPortfolio() {
    view.removeDisplayPortfolio();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void backMenuPortfolioStock() {
    view.removeEnterPortfolioName();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void showDollarCostAveraging() {
    view.removeMainMenu();
    view.displayDollarCostAveraging();
    view.addFeaturesDCAMenu(this);
  }

  @Override
  public void submitDCA(String money, String stocks, String percentages, String portfolioName,
                        String start, String end, String interval, String commission) {

    String portfolioType = "portfolio_v2";
    boolean valid = true;
    if (model.validAmount(money)) {
      if (model.validAmount(interval)) {
        if (model.validAmount(commission)) {
          if (model.validDateFormat(start)
                  && (model.validDateFormat(end) || model.checkEmptyInput(end))) {
            String[] stockList = stocks.split(",");
            String[] percentList = percentages.split(",");

            if (stockList.length == percentList.length) {
              boolean validStock = true;
              for (String stock : stockList) {
                if (!model.checkStockName(stock)) {
                  validStock = false;
                  break;
                }
              }
              if (validStock) {
                boolean validPercent = true;
                for (String percent : percentList) {
                  if (!model.validAmount(percent) && !model.validAmountDecimal(percent)) {
                    validPercent = false;
                    break;
                  }
                }
                if (validPercent) {
                  LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
                  for (int i = 0; i < stockList.length; i++) {
                    stockMap.put(stockList[i], Float.parseFloat(percentList[i]));
                  }
                  if (model.validDCAPercentage(stockMap)) {
                    model.dollarCostAveraging(Integer.parseInt(money), stockMap, portfolioName,
                            start, end, Integer.parseInt(interval), commission, portfolioType);
                  } else {
                    view.removeDollarCostAveraging();
                    showError("Percentage total");
                    valid = false;
                  }
                } else {
                  view.removeDollarCostAveraging();
                  showError("Percentage");
                  valid = false;
                }
              } else {
                view.removeDollarCostAveraging();
                showError("Stock name");
                valid = false;
              }
            } else {
              view.removeDollarCostAveraging();
              showError("Stocks and percentages");
              valid = false;
            }
          } else {
            view.removeDollarCostAveraging();
            showError("Date format");
            valid = false;
          }
        } else {
          view.removeDollarCostAveraging();
          showError("Commission");
          valid = false;
        }
      } else {
        view.removeDollarCostAveraging();
        showError("Investment interval");
        valid = false;
      }
    } else {
      view.removeDollarCostAveraging();
      showError("Investment amount");
      valid = false;
    }
    if (valid) {
      this.backMenuDCA();
    }
  }

  @Override
  public void backMenuDCA() {
    view.removeDollarCostAveraging();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void showBuyWeight() {
    view.removeMainMenu();
    view.displayBuyWeight();
    view.addFeaturesWeightMenu(this);
  }

  @Override
  public void submitBuyWeight(String money, String stocks, String percentages, String portfolioName,
                              String date, String commission) {

    String portfolioType = "portfolio_v2";
    boolean valid = true;
    if (model.validAmount(money)) {
      if (model.validAmount(commission)) {
        if (model.validDateFormat(date)) {
          String[] stockList = stocks.split(",");
          String[] percentList = percentages.split(",");

          if (stockList.length == percentList.length) {
            boolean validStock = true;
            for (String stock : stockList) {
              if (!model.checkStockName(stock)) {
                validStock = false;
                break;
              }
            }
            if (validStock) {
              boolean validPercent = true;
              for (String percent : percentList) {
                if (!model.validAmount(percent) && !model.validAmountDecimal(percent)) {
                  validPercent = false;
                  break;
                }
              }
              if (validPercent) {
                LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
                for (int i = 0; i < stockList.length; i++) {
                  stockMap.put(stockList[i], Float.parseFloat(percentList[i]));
                }
                if (model.validDCAPercentage(stockMap)) {
                  String contents = model.investByWeight(Integer.parseInt(money), stockMap,
                          date, commission);
                  model.appendPortfolio(portfolioName, contents, portfolioType);
                } else {
                  view.removeBuyWeight();
                  showError("Percentage total");
                  valid = false;
                }
              } else {
                view.removeBuyWeight();
                showError("Percentage");
                valid = false;
              }
            } else {
              view.removeBuyWeight();
              showError("Stock name");
              valid = false;
            }
          } else {
            view.removeBuyWeight();
            showError("Stocks and percentages");
            valid = false;
          }
        } else {
          view.removeBuyWeight();
          showError("Date format");
          valid = false;
        }
      } else {
        view.removeBuyWeight();
        showError("Commission");
        valid = false;
      }
    } else {
      view.removeBuyWeight();
      showError("Investment amount");
      valid = false;
    }

    if (valid) {
      this.backMenuBuyWeight();
    }
  }

  @Override
  public void backMenuBuyWeight() {
    view.removeBuyWeight();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void showCostInput() {
    view.removeMainMenu();
    view.displayCostInput();
    view.addFeaturesCostInputMenu(this);
  }

  @Override
  public void submitCostInput(String portfolioName, String date) {
    view.removeCostInput();
    if (model.checkPortfolioName(portfolioName, "portfolio_v2")) {
      if (model.validDateFormat(date)) {
        float costBasis = model.getCostBasis(portfolioName, date, "portfolio_v2");
        view.displayCostValue("Cost Basis", costBasis);
        view.addFeaturesCostValueMenu(this);
      } else {
        view.removeCostInput();
        showError("Date");
      }
    } else {
      view.removeCostInput();
      showError("Portfolio Name");
    }
  }

  @Override
  public void backMenuCostInput() {
    view.removeCostInput();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void showValueInput() {
    view.removeMainMenu();
    view.displayValueInput();
    view.addFeaturesValueInputMenu(this);
  }

  @Override
  public void submitValueInput(String portfolioName, String date) {
    view.removeValueInput();
    if (model.checkPortfolioName(portfolioName, "portfolio_v2")) {
      if (model.validDateFormat(date)) {
        float value = model.getPortfolioValue(date, portfolioName, "portfolio_v2");
        view.displayCostValue("Value", value);
        view.addFeaturesCostValueMenu(this);
      } else {
        view.removeValueInput();
        showError("Date");
      }
    } else {
      view.removeValueInput();
      showError("Portfolio Name");
    }
  }

  @Override
  public void backMenuValueInput() {
    view.removeValueInput();
    view.displayMainMenu();
    view.addFeatures(this);
  }

  @Override
  public void backMenuCostValue() {
    view.removeCostValue();
    view.displayMainMenu();
    view.addFeatures(this);
  }
}