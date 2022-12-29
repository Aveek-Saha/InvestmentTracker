# Features:

## New:
Allows a user to use the GUI to:

- The ability to create a new flexible portfolio.
- Buy/sell stocks by specifying the number of shares.
- Query the cost basis and value of a flexible portfolio at a certain date.
- Save and retrieve flexible portfolios from files.
- Invest using weights.
- Create a portfolio using dollar cost averaging


## Old:
Allows a user to:

- Choose between flexible and inflexible portfolio
- Create a portfolio with one or more valid stocks. Does not allow edits or rewrites of created portfolios. The amount of each stock must be a positive integer.
- Examine an existing portfolio to view the stocks and their respective amounts. 
- Enter an existing portfolio name and a valid date to view the value of the stocks on the given date. If no data for a particular stock exists on a given date, the value will be considered $0 for that stock.
- Portfolios are persisted as `.txt` files in the `portfolios` directory and have the following format
```
stock1,amount
stock2,amount
stock3,amount
```
- Create flexible portfolios, can be edited after creation.
- User can buy stocks on valid trading days.
- User can sell stocks on valid trading days.
- User can get value of an existing portfolio on a given date.
- User can check and existing portfolio performance over a valid time range.
- User can get cost basis of an existing portfolio on a given date.
- User can enter a valid commission amount for a transaction.
- Flexible Portfolios are persisted as `.txt` files in the `portfolios` directory and have the following format.
```
stock1,amount,YYYY-MM-DD,b/s,commission
stock2,amount,YYYY-MM-DD,b/s,commission
stock3,amount,YYYY-MM-DD,b/s,commission
```
