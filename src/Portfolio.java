/**
 * This class creates instances of the Model, View and Controller and starts execution
 * of the stock portfolio application.
 * It contains the main method that allows the user to choose between the text view and
 * the GUI view using command line arguments.
 */

public class Portfolio {
  /**
   * Starts the execution of the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {

    IModel model = new Model();
    if (args.length > 0 && args[0].equals("text")) {
      IView view = new TextView(System.out);
      IController controller = new TextController(model, System.in, view);
      controller.startMenu();
    } else {
      JFrameView view = new JFrameView("Portfolio");
      JFrameController controller = new JFrameController(model);
      controller.setView(view);
    }
  }
}