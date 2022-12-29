import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * This class represents the implementation of the Model portion of MVC architecture
 * for the stock portfolio.
 * It has methods that perform various functionalities on portfolios such as reading,
 * writing and validating portfolios.
 * It also includes functionality such as getting cost basis and performing dollar cost averaging.
 */
public class Model implements IModel {
  String lineSep = System.lineSeparator();

  protected void createFileWithDir(String directory) {
    File dir = new File(directory);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

  @Override
  public void writePortfolio(String portfolioName, String contents, String portfolioType) {
    try {
      createFileWithDir(portfolioType);
      FileWriter myWriter = new FileWriter(portfolioType + File.separatorChar
              + portfolioName + ".txt");
      myWriter.write(contents);
      myWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void appendPortfolio(String portfolioName, String contents, String portfolioType) {
    try {
      FileWriter myWriter = new FileWriter(portfolioType + File.separatorChar
              + portfolioName + ".txt", true);
      BufferedWriter myBufferedWriter = new BufferedWriter(myWriter);
      myBufferedWriter.write(lineSep + contents);
      myBufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String readPortfolio(String portfolioName, String portfolioType) {
    try {
      Path fileName = Path.of(portfolioType + File.separatorChar + portfolioName + ".txt");
      return Files.readString(fileName).trim();
    } catch (IOException e) {
      return "False";
    }
  }

  @Override
  public boolean checkPortfolioName(String portfolioName, String portfolioType) {
    Path fileName = Path.of(portfolioType + File.separatorChar + portfolioName + ".txt");
    return Files.exists(fileName);
  }

  @Override
  public boolean checkStockName(String stockName) {
    AlphaVantage api = new AlphaVantage();
    createFileWithDir("stocks");
    Path fileName = Path.of("stocks" + File.separatorChar + stockName + ".csv");
    if (Files.exists(fileName) && upToDateStockCache(stockName)) {
      return true;
    } else if (api.checkStockName(stockName)) {
      api.getStockData(stockName);
      return Files.exists(fileName);
    }
    return false;
  }

  protected boolean upToDateStockCache(String stockName) {
    Path fileName = Path.of("stocks" + File.separatorChar + stockName + ".csv");
    if (Files.exists(fileName)) {
      try {
        BasicFileAttributes attr = Files.readAttributes(fileName, BasicFileAttributes.class);
        FileTime fileTime = attr.creationTime();
        Date today = new Date();
        Instant instantToday = today.toInstant().truncatedTo(ChronoUnit.DAYS);
        Instant instantCreation = fileTime.toInstant().truncatedTo(ChronoUnit.DAYS);
        return instantToday.equals(instantCreation);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  protected int getStockAmountTillDate(String portfolioName, String portfolioType,
                                       String date, String stock) {
    try (BufferedReader br = new BufferedReader(new FileReader(portfolioType
            + File.separatorChar + portfolioName + ".txt"))) {
      String line;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date dateSell = format.parse(date);
      int count = 0;
      while ((line = br.readLine()) != null) {
        if (line.equals("")) {
          continue;
        }
        String[] values = line.split(",");
        Date dateBuy = format.parse(values[2]);
        if (values[0].equals(stock)
                && dateSell.compareTo(dateBuy) >= 0) {
          if (values[3].equals("b")) {
            count += Float.parseFloat(values[1]);
          } else if (values[3].equals("s")) {
            count -= Float.parseFloat(values[1]);
          }
        }
      }
      return count;
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public boolean checkValidStockSell(String portfolioName, String portfolioType,
                                     String date, String stock, String amount) {
    try (BufferedReader br = new BufferedReader(new FileReader(portfolioType
            + File.separatorChar + portfolioName + ".txt"))) {
      String line;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date dateSell = format.parse(date);
      while ((line = br.readLine()) != null) {
        if (line.equals("")) {
          continue;
        }
        String[] values = line.split(",");
        Date dateBuy = format.parse(values[2]);
        if (values[0].equals(stock)
                && (Integer.parseInt(amount)
                <= getStockAmountTillDate(portfolioName, portfolioType, date, stock))
                && dateSell.compareTo(dateBuy) >= 0
                && values[3].equals("b")) {
          return true;
        }
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public float getPriceAtDate(String date, String stock) {
    try (BufferedReader br = new BufferedReader(new FileReader("stocks"
            + File.separatorChar + stock + ".csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        if (values[0].equals(date)) {
          return Float.parseFloat(values[4]);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0.0F;
  }

  protected HashMap<String, Float> getStockCountAtDate(String date, String portfolioName,
                                                       String portfolioType) {
    HashMap<String, Float> stockCount = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(portfolioType
            + File.separatorChar + portfolioName + ".txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.equals("")) {
          continue;
        }
        String[] values = line.split(",");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = format.parse(date);
        Date dateTransaction = format.parse(values[2]);
        if (dateValue.compareTo(dateTransaction) >= 0) {
          if (stockCount.containsKey(values[0])) {
            float count = stockCount.get(values[0]);
            if (values[3].equals("b")) {
              stockCount.put(values[0], Float.parseFloat(values[1]) + count);
            } else {
              stockCount.put(values[0], count - Float.parseFloat(values[1]));
            }
          } else {
            stockCount.put(values[0], Float.parseFloat(values[1]));
          }
        }
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return stockCount;
  }

  @Override
  public float getPortfolioValue(String date, String portfolioName, String portfolioType) {
    float total = 0;
    if (portfolioType.equals("portfolio_v2")) {
      this.performDCA(portfolioName, "dca", portfolioType);
    }
    try (BufferedReader br = new BufferedReader(new FileReader(portfolioType
            + File.separatorChar + portfolioName + ".txt"))) {
      String line;
      HashMap<String, Float> stockCount =
              getStockCountAtDate(date, portfolioName, portfolioType);
      if (portfolioType.equals("portfolio_v2")) {
        for (String stock : stockCount.keySet()) {
          float cost = getPriceAtDate(date, stock);
          total += (cost * stockCount.get(stock));
        }
      } else {
        while ((line = br.readLine()) != null) {
          String[] values = line.split(",");
          float cost = getPriceAtDate(date, values[0]);
          total += (cost * Integer.parseInt(values[1]));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return total;
  }

  @Override
  public boolean validDateFormat(String date) {
    return date.matches("^([12][0-9]{3})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
  }

  @Override
  public boolean validStockDate(String date, String stock) {
    try (BufferedReader br = new BufferedReader(new FileReader("stocks"
            + File.separatorChar + stock + ".csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        if (values[0].equals(date)) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public float getCostBasis(String portfolioName, String date, String portfolioType) {
    float total = 0;
    this.performDCA(portfolioName, "dca", portfolioType);
    try (BufferedReader br = new BufferedReader(new FileReader(portfolioType
            + File.separatorChar + portfolioName + ".txt"))) {
      String line;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date dateCost = format.parse(date);
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        if (line.equals("")) {
          continue;
        }
        Date dateTransaction = format.parse(values[2]);
        if (dateCost.compareTo(dateTransaction) >= 0) {
          if (values[3].equals("b")) {
            float cost = getPriceAtDate(values[2], values[0]);
            total += (cost * Float.parseFloat(values[1]));
            total += Float.parseFloat(values[4]);
          } else if (values[3].equals("s")) {
            total += Float.parseFloat(values[4]);
          }
        }
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return total;
  }

  @Override
  public boolean validAmount(String quantity) {
    return quantity.matches("\\d+");
  }

  @Override
  public boolean validAmountDecimal(String quantity) {
    return quantity.matches("\\d*\\.\\d+|\\d+\\.\\d*");
  }

  @Override
  public boolean checkEmptyInput(String s) {
    return s.equals("");
  }

  protected LocalDate getLastWorkingDay(LocalDate lastDay) {
    LocalDate lastWorkingDay;
    switch (DayOfWeek.of(lastDay.get(ChronoField.DAY_OF_WEEK))) {
      case SATURDAY:
        lastWorkingDay = lastDay.minusDays(1);
        break;
      case SUNDAY:
        lastWorkingDay = lastDay.minusDays(2);
        break;
      default:
        lastWorkingDay = lastDay;
    }
    return lastWorkingDay;
  }

  protected float normalize(float x, float inMin, float inMax) {
    float inRange = inMax - inMin;
    return ((x - inMin) * 50 / inRange + 0);
  }

  @Override
  public LinkedHashMap<String, Float> normHash(LinkedHashMap<String, Float> dateMap) {

    float maxValue = Collections.max(dateMap.values());
    float minValue = Collections.min(dateMap.values());
    for (String d : dateMap.keySet()) {
      float val = dateMap.get(d);
      dateMap.put(d, normalize(val, minValue, maxValue));
    }
    return dateMap;
  }

  @Override
  public LinkedHashMap<String, Float> splitTimeRange(String start, String end, String portfolioName,
                                                     String portfolioType) {
    LocalDate dateStart = LocalDate.parse(start);
    LocalDate dateEnd = LocalDate.parse(end);

    long diffDays = ChronoUnit.DAYS.between(dateStart, dateEnd);
    long diffMonths = ChronoUnit.MONTHS.between(dateStart, dateEnd);
    long diffYears = ChronoUnit.YEARS.between(dateStart, dateEnd);

    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();

    if (dateStart.compareTo(dateEnd) < 0) {
      if (diffDays >= 5 && diffDays <= 30) {
        for (int i = 0; i <= diffDays; i++) {
          String valueDate = dateStart.plus(i, ChronoUnit.DAYS).toString();
          float value = getPortfolioValue(valueDate, portfolioName, portfolioType);
          dateMap.put(valueDate, value);
        }
      } else if (diffMonths >= 5 && diffMonths <= 30) {
        for (int i = 0; i <= diffMonths; i++) {
          LocalDate valueDate = getLastWorkingDay(dateStart.plus(i, ChronoUnit.MONTHS)
                  .with(TemporalAdjusters.lastDayOfMonth()));
          float value = getPortfolioValue(valueDate.toString(), portfolioName, portfolioType);
          String displayDate = valueDate.getMonth().toString().substring(0, 3) + " "
                  + valueDate.getYear();
          dateMap.put(displayDate, value);
        }
      } else if (diffYears >= 5 && diffYears <= 30) {
        for (int i = 0; i <= diffYears; i++) {
          LocalDate valueDate = getLastWorkingDay(dateStart.plus(i, ChronoUnit.YEARS)
                  .with(TemporalAdjusters.lastDayOfYear()));
          float value = getPortfolioValue(valueDate.toString(), portfolioName, portfolioType);
          String displayDate = String.valueOf(valueDate.getYear());
          dateMap.put(displayDate, value);
        }
      }
    }
    return dateMap;
  }

  protected float getStockAmount(float money, String stock, String date) {
    float price = getPriceAtDate(date, stock);
    if (Float.compare(price, 0F) == 0) {
      return 0;
    }
    return money / price;
  }


  @Override
  public String investByWeight(int money, LinkedHashMap<String, Float> stockMap,
                               String date, String commission) {
    StringBuilder contents = new StringBuilder();

    for (String stock : stockMap.keySet()) {
      float percent = stockMap.get(stock);
      float moneyStock = money * (percent / 100F);
      float stockAmount = getStockAmount(moneyStock, stock, date);
      while (Float.compare(stockAmount, 0F) == 0) {
        LocalDate newDate = LocalDate.parse(date).minusDays(1);
        stockAmount = getStockAmount(money, stock,
                newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      }
      contents.append(stock).append(",").append(stockAmount).append(",")
              .append(date).append(",b,").append(commission).append(lineSep);
    }
    return contents.toString().trim();
  }

  protected void writeDCA(String portfolioName, String contents, String stratName) {
    try {
      createFileWithDir("strats" + File.separatorChar
              + stratName);
      FileWriter myWriter = new FileWriter("strats" + File.separatorChar
              + stratName + File.separatorChar
              + portfolioName + ".txt", true);
      BufferedWriter myBufferedWriter = new BufferedWriter(myWriter);
      myBufferedWriter.write(contents + lineSep);
      myBufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected String readDCA(String portfolioName, String stratName) {
    try {
      Path fileName = Path.of("strats" + File.separatorChar
              + stratName + File.separatorChar
              + portfolioName + ".txt");
      return Files.readString(fileName).trim();
    } catch (IOException e) {
      return "";
    }
  }

  protected void updateDCA(int money, LinkedHashMap<String, Float> stockMap, String portfolioName,
                        String start, String end, int interval, String commission,
                        String portfolioType) {

    LocalDate dateEnd;
    if (end.equals("")) {
      dateEnd = LocalDate.now();
    } else {
      dateEnd = LocalDate.parse(end);
    }

    LocalDate dateStart = LocalDate.parse(start);
    LocalDate today = LocalDate.now();
    LocalDate curr = dateStart;
    String portfolio;
    if (this.checkPortfolioName(portfolioName, portfolioType)) {
      portfolio = readPortfolio(portfolioName, portfolioType);
    } else {
      portfolio = "";
      this.writePortfolio(portfolioName, portfolio, portfolioType);
    }

    while (curr.compareTo(today) <= 0 && curr.compareTo(dateEnd) <= 0) {
      curr = getLastWorkingDay(curr);
      String contents = investByWeight(money, stockMap, curr.toString(), commission);
      if (!portfolio.contains(contents)) {
        appendPortfolio(portfolioName, contents, portfolioType);
      }
      curr = curr.plusDays(interval);
    }
  }

  @Override
  public void dollarCostAveraging(int money, LinkedHashMap<String, Float> stockMap,
                                  String portfolioName, String start, String end, int interval,
                                  String commission, String portfolioType) {
    String dca;
    ArrayList<String> stocks = new ArrayList<>();
    for (String stock : stockMap.keySet()) {
      stocks.add(stock + ":" + stockMap.get(stock));
    }
    dca = start + "," + end + "," + interval + "," + money + "," + commission + ","
            + String.join("/", stocks);
    writeDCA(portfolioName, dca, "dca");

    performDCA(portfolioName, "dca", portfolioType);
  }

  protected void performDCA(String portfolioName, String stratName, String portfolioType) {
    String strats = readDCA(portfolioName, stratName);
    if (strats.equals("")) {
      return;
    }

    String[] dcas = strats.split(lineSep);

    for (String dca : dcas) {
      String[] params = dca.split(",");
      String start = params[0];
      String end = params[1];
      int interval = Integer.parseInt(params[2]);
      int money = Integer.parseInt(params[3]);
      String commission = params[4];
      String[] stocks = params[5].split("/");
      LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
      for (String stock : stocks) {
        String stockName = stock.split(":")[0];
        float stockPercent = Float.parseFloat(stock.split(":")[1]);
        stockMap.put(stockName, stockPercent);
      }
      updateDCA(money, stockMap, portfolioName, start, end, interval, commission,
              portfolioType);
    }
  }

  @Override
  public boolean validDCAPercentage(LinkedHashMap<String, Float> stockMap) {
    float total = 0;
    for (String stock : stockMap.keySet()) {
      total += stockMap.get(stock);
    }
    return Float.compare(total, 100) == 0;
  }


}