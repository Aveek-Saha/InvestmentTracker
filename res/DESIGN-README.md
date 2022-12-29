# Design

Our design for the portfolio application follows the MVC architecture. The components are as follows:

1. Model: The model handles all the storing and retrieving of data, as well as all the checks for valid, portfolio names, stock ticker symbols and dates.

2. View: The view handles all the GUI/text output and displaying menu options and wrong input error messages.

3. Controller: the controller coordinates the flow of the data between the model and view, deciding what messages to display when and what checks to perform on inputs.

# Stocks

The stocks are stored as `<ticker symbol>.csv` files.

# Portfolios

The inflexible portfolios are stored as `portfolio_v1/<portfolio name>.txt` files with the following format.

```
stock1,amount
stock2,amount
stock3,amount
```

The flexible portfolios are stored as `portfolio_v2/<portfolio name>.txt` files with the following format.

```
stock1,amount,YYYY-MM-DD,b/s,commission
stock2,amount,YYYY-MM-DD,b/s,commission
stock3,amount,YYYY-MM-DD,b/s,commission
```

# Changes

No changes were made to the MVC architecture.

Added methods to the existing interfaces to implement new functionality.
- In the Model added methods to perform dollar cost averaging and invest using weights.

Added new classes and intefaces.
- For the Controller: added support for GUI.
- For the View: added support for the GUI display.

