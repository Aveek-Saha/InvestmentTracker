import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * This class represents the implementation of the Controller portion of MVC architecture
 * for the stock portfolio.
 * It implements all the methods from thr IController interface.
 * This is where the flexible and inflexible portfolios are supported.
 */
public class TextController implements IController {
  private final Scanner in;
  private final IView view;
  private final IModel model;

  /**
   * Constructs a new TextController object initialized with the model, input stream and the view.
   *
   * @param model the model
   * @param in    the input stream
   * @param view  the view
   */
  public TextController(IModel model, InputStream in, IView view) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);

  }

  protected void portfolioV2() {
    boolean quit = false;
    String portfolioType = "portfolio_v2";

    while (!quit) {
      view.showOptionsV2();
      String option = in.next();
      String name;

      String done;
      boolean invalid;
      StringBuilder str;
      StringJoiner contents = new StringJoiner("\n");
      switch (option) {
        case "1":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          do {

            invalid = false;
            str = new StringBuilder();

            view.showStringEnterStockName();
            String stock = in.nextLine();
            str.append(stock);

            if (model.checkStockName(stock)) {
              view.showStringEnterStockAmount();
              String quantity = in.nextLine();

              if (model.validAmount(quantity)) {
                str.append(",");
                str.append(quantity);

                view.showStringEnterDate();
                String date = in.nextLine();

                if (model.validDateFormat(date) && model.validStockDate(date, stock)) {
                  str.append(",");
                  str.append(date);

                  str.append(",");
                  str.append("b");

                  view.showStringEnterCommission();
                  String com = in.nextLine();

                  if (model.validAmount(com)) {
                    str.append(",");
                    str.append(com);
                  } else {
                    invalid = true;
                    view.showStringWrongCommission();
                  }
                } else {
                  invalid = true;
                  view.showStringWrongDate();
                }
              } else {
                invalid = true;
                view.showStringWrongStockAmount();
              }
            } else {
              invalid = true;
              view.showStringWrongStockName();
            }

            view.showStringEnterMoreStocks();
            done = in.nextLine();
            if (!invalid) {
              contents.add(str.toString());
            }
          }
          while (!(done.equals("n")));

          if (!model.checkEmptyInput(contents.toString())) {
            if (model.checkPortfolioName(name, portfolioType)) {
              model.appendPortfolio(name, contents.toString(), portfolioType);
            } else {
              model.writePortfolio(name, contents.toString(), portfolioType);
            }
          }
          break;
        case "2":

          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          do {

            invalid = false;
            str = new StringBuilder();

            view.showStringEnterStockName();
            String stock = in.nextLine();
            str.append(stock);

            if (model.checkStockName(stock)) {
              view.showStringEnterStockAmount();
              String quantity = in.nextLine();

              if (model.validAmount(quantity)) {
                str.append(",");
                str.append(quantity);

                view.showStringEnterDate();
                String date = in.nextLine();

                if (model.validDateFormat(date) && model.validStockDate(date, stock)) {
                  str.append(",");
                  str.append(date);

                  str.append(",");
                  str.append("s");

                  if (model.checkPortfolioName(name, portfolioType)) {
                    if (!model.checkValidStockSell(name, portfolioType, date, stock, quantity)) {
                      invalid = true;
                      view.showStringWrongSell();
                    } else {
                      model.appendPortfolio(name, str.toString(), portfolioType);
                    }
                  } else {
                    invalid = true;
                    view.showStringWrongPortfolioName();
                  }
                } else {
                  invalid = true;
                  view.showStringWrongDate();
                }
              } else {
                invalid = true;
                view.showStringWrongStockAmount();
              }
            } else {
              invalid = true;
              view.showStringWrongStockName();
            }

            view.showStringEnterMoreStocks();
            done = in.nextLine();
            if (!invalid) {
              contents.add(str.toString());
            }
          }
          while (!(done.equals("n")));
          break;
        case "3":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          if (model.checkPortfolioName(name, portfolioType)) {
            view.showStringEnterDate();
            String date = in.nextLine();

            if (model.validDateFormat(date)) {
              float costBasis = model.getCostBasis(name, date, portfolioType);
              view.showCostBasis(String.valueOf(costBasis));
            } else {
              view.showStringWrongDate();
            }
          } else {
            view.showStringWrongPortfolioName();
          }

          break;
        case "4":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          if (model.checkPortfolioName(name, portfolioType)) {
            view.showStringEnterDate();
            String date = in.nextLine();

            if (model.validDateFormat(date)) {
              float costBasis = model.getPortfolioValue(date, name, portfolioType);
              view.showValue(String.valueOf(costBasis));
            } else {
              view.showStringWrongDate();
            }
          } else {
            view.showStringWrongPortfolioName();
          }
          break;
        case "5":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();
          if (!model.checkEmptyInput(name) && model.checkPortfolioName(name, portfolioType)) {
            String port = model.readPortfolio(name, portfolioType);
            view.showString(port);
          } else {
            view.showStringWrongPortfolioName();
          }
          break;
        case "6":

          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          if (model.checkPortfolioName(name, portfolioType)) {
            view.showStringEnterStartDate();
            String dateStart = in.nextLine();

            view.showStringEnterEndDate();
            String dateEnd = in.nextLine();

            if (model.validDateFormat(dateStart) && model.validDateFormat(dateEnd)) {
              LinkedHashMap<String, Float> dateMap = model.splitTimeRange(dateStart, dateEnd, name,
                      portfolioType);

              if (!dateMap.isEmpty()) {
                int scale = (int) (Collections.max(dateMap.values()) / 50);
                dateMap = model.normHash(dateMap);
                view.showPerformanceOverTime(scale, dateStart, dateEnd, name, dateMap);
              } else {
                view.showStringWrongDateRange();
              }
            } else {
              view.showStringWrongDate();
            }
          } else {
            view.showStringWrongPortfolioName();
          }
          break;
        case "Q":
          quit = true;
          break;
        default:
          view.showOptionError();
      }
    }
  }

  protected void portfolioV1() {
    boolean quit = false;
    String portfolioType = "portfolio_v1";

    while (!quit) {
      view.showOptions();
      String option = in.next();
      String name;
      switch (option) {
        case "1":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();

          if (!model.checkEmptyInput(name) && !model.checkPortfolioName(name, portfolioType)) {

            String done;
            StringBuilder contents = new StringBuilder();

            do {
              view.showStringEnterStockName();
              String stock = in.nextLine();
              contents.append(stock);

              if (model.checkStockName(stock)) {
                view.showStringEnterStockAmount();
                String quantity = in.nextLine();

                if (model.validAmount(quantity)) {
                  contents.append(",");
                  contents.append(quantity);
                  contents.append("\n");
                } else {
                  view.showStringWrongStockAmount();
                }
              } else {
                view.showStringWrongStockName();
              }

              view.showStringEnterMoreStocks();
              done = in.nextLine();
            }
            while (!(done.equals("n")));

            model.writePortfolio(name, String.valueOf(contents), portfolioType);
          } else {
            view.showStringExistingPortfolioName();
          }
          break;
        case "2":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();
          if (!model.checkEmptyInput(name) && model.checkPortfolioName(name, portfolioType)) {
            String port = model.readPortfolio(name, portfolioType);
            view.showString(port);
          } else {
            view.showStringWrongPortfolioName();
          }
          break;
        case "3":
          view.showStringEnterPortfolioName();
          in.nextLine();
          name = in.nextLine();
          if (!model.checkEmptyInput(name) && model.checkPortfolioName(name, portfolioType)) {
            view.showStringEnterDate();
            String date = in.nextLine();

            if (model.validDateFormat(date)) {
              float value = model.getPortfolioValue(date, name, portfolioType);
              view.showValue(String.valueOf(value));
            } else {
              view.showStringWrongDate();
            }
          } else {
            view.showStringWrongPortfolioName();
          }

          break;
        case "Q":
          quit = true;
          break;
        default:
          view.showOptionError();
      }
    }
  }

  @Override
  public void startMenu() {
    boolean quit = false;

    while (!quit) {
      view.showPortfolioOptions();
      String option = in.next();
      switch (option) {
        case "1":
          this.portfolioV2();
          break;
        case "2":
          this.portfolioV1();
          break;
        case "Q":
          quit = true;
          break;
        default:
          view.showOptionError();
      }
    }
  }

}
