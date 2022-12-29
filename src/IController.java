/**
 * This interface represents the Controller portion of MVC architecture
 * for the stock portfolio.
 * It has one method that calls the functions in the view to display the menu.
 * This is where flexible and inflexible portfolios are supported.
 */

public interface IController {

  /**
   * Starts the controller for menu to take inputs and display results.
   * The startMenu method does not return any value.
   */
  void startMenu();
}
