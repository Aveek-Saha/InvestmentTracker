import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class represents the AlphaVantage API and offers
 * all operations listed in the IAlphaVantage interface.
 * The class represents the data in form of files.
 * It has methods to fetch stock data.
 */
public class AlphaVantage implements IAlphaVantage {
  String apiKey = "OZXGSCWUKIBQFGCV";
  String lineSep = System.lineSeparator();

  @Override
  public boolean checkStockName(String stock) {

    URL url = null;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=SYMBOL_SEARCH"
              + "&keywords"
              + "=" + stock + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No data found for " + stock);
    }
    String[] lines = output.toString().split(lineSep);
    for (String line : lines) {
      String[] values = line.split(",");
      if (values[0].equals(stock)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void getStockData(String stock) {
    URL url = null;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stock + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock);
    }
    try (FileWriter myWriter = new FileWriter("stocks" + File.separatorChar
            + stock + ".csv")) {
      myWriter.write(output.toString());
      myWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
