import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the Model class.
 * This class contains methods that tests the functionality of the model.
 */
public class ModelTest {

  Model instance = new Model();

  @Before
  public void setup() {
    instance.writePortfolio("aveek", "AAPL,13,2022-07-29,b,5",
            "portfolio_v2");
    instance.checkStockName("AAPL");
  }

  @Test
  public void testCreateFileWithDirSuccess() {
    instance.createFileWithDir("testing");
    File file = new File("testing");
    assertTrue(file.exists());
  }

  @Test(expected = AssertionError.class)
  public void testCreateFileWithDirFail() {
    instance.createFileWithDir("test");
    File file = new File("testi");
    assertTrue(file.exists());
  }

  @Test
  public void testWriteFile() {
    instance.writePortfolio("shreya", "APPL, 13", "portfolio_v1");
    assertEquals("APPL, 13", instance.readPortfolio("shreya",
            "portfolio_v1"));
  }

  @Test
  public void testNegativeWriteFile() {
    instance.writePortfolio("shrey", "APPL, -13", "portfolio_v1");
    assertEquals("APPL, -13", instance.readPortfolio("shrey",
            "portfolio_v1"));
  }

  @Test(expected = AssertionError.class)
  public void testInvalidPathWriteFile() {
    instance.writePortfolio("invalid", "APPL, 13", "portfolio_v1");
    assertEquals("APPL, 13", instance.readPortfolio("invalidpath",
            "portfolio_v1"));
  }

  @Test
  public void testReadFile() {
    assertEquals("APPL, 13",
            instance.readPortfolio("shreya", "portfolio_v1"));
  }

  @Test(expected = AssertionError.class)
  public void testWrongReadFile() {
    assertEquals("TSLA,3\n", instance.readPortfolio("shreya",
            "portfolio_v1"));
  }

  @Test(expected = AssertionError.class)
  public void testNoFileReadFile() {
    assertEquals("TSLA,3\n", instance.readPortfolio("Saha",
            "portfolio_v1"));
  }

  @Test
  public void testCheckWrongPortfolioName() {
    assertFalse(instance.checkPortfolioName("Saha", "portfolio_v1"));
  }

  @Test
  public void testCheckStockName() {
    assertTrue(instance.checkStockName("AAPL"));
  }

  @Test
  public void testWrongCheckStockName() {
    assertFalse(instance.checkStockName("PDP.csv"));
  }

  @Test
  public void testGetPriceAtDate() {
    assertEquals(102.97000122070312,
            instance.getPriceAtDate("2022-10-24", "GOOG"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testWrongGetPriceAtDate() {
    assertEquals(10.0900,
            instance.getPriceAtDate("2022-10-24", "GOOG"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testNegativeGetPriceAtDate() {
    assertEquals(-10.0900,
            instance.getPriceAtDate("2022-10-24", "GOOG"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testWrongDateGetPriceAtDate() {
    assertEquals(10.0900,
            instance.getPriceAtDate("2029-10-24", "GOOG"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testWrongStockGetPriceAtDate() {
    assertEquals(10.0900,
            instance.getPriceAtDate("2022-10-24", "PDP"), 0.2);
  }

  @Test
  public void testgetPortfolioValue() {
    assertEquals(1942.8499755859375,
            instance.getPortfolioValue("2022-10-24", "aveek",
                    "portfolio_v2"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testWrongGetPortfolioValue() {
    assertEquals(300,
            instance.getPortfolioValue("2022-10-24", "aveek",
                    "portfolio_v1"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testInvalidDateGetPortfolioValue() {
    assertEquals(300,
            instance.getPortfolioValue("2029-10-24", "aveek",
                    "portfolio_v1"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testNegativeDateGetPortfolioValue() {
    assertEquals(-300,
            instance.getPortfolioValue("2029-10-24", "aveek",
                    "portfolio_v1"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testInvalidNameGetPortfolioValue() {
    assertEquals(300,
            instance.getPortfolioValue("2022-10-24", "Saha",
                    "portfolio_v1"), 0.2);
  }

  @Test
  public void testValidDateFormat() {

    assertTrue(instance.validDateFormat("2022-10-24"));
  }

  @Test
  public void testAlphabeticalDateFormat() {
    assertFalse(instance.validDateFormat("twenty fourth"));
  }

  @Test
  public void testSymbolsReverseDateFormat() {
    assertFalse(instance.validDateFormat("24/10/2022"));
  }

  @Test
  public void testIncompleteDateFormat() {
    assertFalse(instance.validDateFormat("22-10-24"));
  }

  @Test
  public void testValidStockAmount() {
    assertTrue(instance.validAmount("20"));
  }

  @Test
  public void testNegativeStockAmount() {

    assertFalse(instance.validAmount("-20"));
  }

  @Test
  public void testFloatStockAmount() {
    assertFalse(instance.validAmount("2.5"));
  }

  @Test
  public void testAlphabeticalStockAmount() {
    assertFalse(instance.validAmount("Aabc21"));
  }

  @Test
  public void testSymbolStockAmount() {
    assertFalse(instance.validAmount("@[3"));
  }

  @Test
  public void testNumberfirstStockAmount() {
    assertFalse(instance.validAmount("42@[3"));
  }

  @Test
  public void testBigStockAmount() {
    assertTrue(instance.validAmount("999999999999999"));
  }

  @Test
  public void testUptoDateStockCache() {
    assertFalse(instance.upToDateStockCache("AAPL"));
  }

  @Test
  public void testStockAmountTillDate() {
    assertEquals(13, instance.getStockAmountTillDate("aveek",
            "portfolio_v2", "2022-07-29", "AAPL"));
  }

  @Test (expected = AssertionError.class)
  public void testNegativeStockAmountTillDate() {
    assertEquals(-3, instance.getStockAmountTillDate("aveek",
            "portfolio_v2", "2022-07-29", "AAPL"));
  }

  @Test(expected = AssertionError.class)
  public void testWrongStockAmountTillDate() {
    assertEquals(41, instance.getStockAmountTillDate("aveek",
            "portfolio_v2", "2022-07-29", "AAPL"));
  }

  @Test
  public void testCheckValidStockSell() {
    assertTrue(instance.checkValidStockSell("aveek",
            "portfolio_v2", "2022-07-29",
            "AAPL", "2"));

    assertFalse(instance.checkValidStockSell("aveek",
            "portfolio_v2", "2022-07-29",
            "AAPL", "21"));
  }

  @Test (expected = AssertionError.class)
  public void testCheckNegativeValidStockSell() {
    assertFalse(instance.checkValidStockSell("aveek",
            "portfolio_v2", "2022-07-29",
            "AAPL", "-21"));
  }

  @Test
  public void testGetStockCountAtDate() {
    LinkedHashMap<String, Float> stockCount = new LinkedHashMap<>();
    stockCount.put("AAPL", 13.0F);
    assertEquals(stockCount, instance.getStockCountAtDate("2022-07-29",
            "aveek", "portfolio_v2"));
  }

  @Test (expected = AssertionError.class)
  public void testGetNegativeStockCountAtDate() {
    LinkedHashMap<String, Integer> stockCount = new LinkedHashMap<>();
    stockCount.put("AAPL", -3);
    assertEquals(stockCount, instance.getStockCountAtDate("2022-07-29",
            "aveek", "portfolio_v2"));
  }

  @Test(expected = AssertionError.class)
  public void testWrongGetStockCountAtDate() {
    LinkedHashMap<String, Integer> stockCount = new LinkedHashMap<>();
    stockCount.put("AAPL", 3);
    stockCount.put("V", 9);
    assertEquals(stockCount, instance.getStockCountAtDate("2022-07-29",
            "aveek", "portfolio_v2"));
  }

  @Test
  public void testValidStockDate() {
    assertTrue(instance.validStockDate("2022-07-29", "AAPL"));
    assertFalse(instance.validStockDate("2022-07-29", "HERO"));
    assertFalse(instance.validStockDate("2090-07-29", "AAPL"));
  }

  @Test
  public void testGetCostBasis() {
    assertEquals(2117.6298828125, instance.getCostBasis("aveek", "2022-07-29",
            "portfolio_v2"), 0.2);
  }

  @Test
  public void testGetNegativeCostBasis() {
    assertNotEquals(-3709.58, instance.getCostBasis("aveek", "2022-07-29",
            "portfolio_v2"), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testGetWrongCostBasis() {
    assertEquals(37, instance.getCostBasis("aveek", "2022-07-29",
            "portfolio_v2"), 0.2);
  }

  @Test
  public void testGetLastWorkingDay() {
    LocalDate lastWorkingDay = LocalDate.parse("2022-07-29");
    assertEquals(lastWorkingDay, instance.getLastWorkingDay(LocalDate.parse("2022-07-29")));
  }

  @Test(expected = AssertionError.class)
  public void testWrongGetLastWorkingDay() {
    LocalDate lastWorkingDay = LocalDate.parse("2022-07-28");
    assertEquals(lastWorkingDay, instance.getLastWorkingDay(LocalDate.parse("2022-07-29")));
  }

  @Test
  public void testNormalize() {
    assertEquals(34.7625,
            instance.normalize(16.343F, 8F, 20F), 0.2);
  }

  @Test(expected = AssertionError.class)
  public void testWrongNormalize() {
    assertEquals(39.7625, instance.normalize(16.343F, 8F, 20F), 0.2);
  }

  @Test
  public void testNegativeNormalize() {
    assertNotEquals(-39.7625, instance.normalize(16.343F, 8F, 20F), 0.2);
  }

  @Test
  public void testNormHash() {
    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();
    dateMap.put("2022-07-29", 16.343F);
    LinkedHashMap<String, Float> dateMap2 = new LinkedHashMap<>();
    dateMap2.put("2022-07-29", 16.343F);
    assertEquals(dateMap, instance.normHash(dateMap));
  }

  @Test
  public void testWrongNormHash() {
    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();
    dateMap.put("2022-07-29", 16.343F);
    LinkedHashMap<String, Float> dateMap2 = new LinkedHashMap<>();
    dateMap2.put("2022-07-28", 16.343F);
    assertNotEquals(dateMap, instance.normHash(dateMap2));
  }

  @Test
  public void testSplitTimeRange() {
    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();
    assertEquals(dateMap, instance.splitTimeRange("2022-07-27", "2022-07-29",
            "aveek", "portfolio_v2"));
  }

  @Test(expected = DateTimeParseException.class)
  public void testWrongSplitTimeRange() {
    LinkedHashMap<String, Float> dateMap = new LinkedHashMap<>();
    assertEquals(dateMap, instance.splitTimeRange("JAN 2018", "JUN 2018",
            "aveek", "portfolio_v2"));
  }

  @Test
  public void testValidAmountDecimal() {
    assertTrue(instance.validAmountDecimal("45.6"));
    assertFalse(instance.validAmountDecimal("-45.6"));
    assertFalse(instance.validAmountDecimal("abc"));
  }

  @Test
  public void testGetStockAmount() {
    assertEquals(2.655161142349243,
            instance.getStockAmount(400, "AAPL", "2022-11-01"), 0.2);
  }

  @Test (expected = AssertionError.class)
  public void testWrongGetStockAmount() {
    assertEquals(20,
            instance.getStockAmount(400, "AAPL", "2022-11-01"), 0.2);
  }

  @Test (expected = AssertionError.class)
  public void testNegativeExpectedGetStockAmount() {
    assertEquals(-2.655161142349243,
            instance.getStockAmount(400, "AAPL", "2022-11-01"), 0.2);
  }

  @Test (expected = AssertionError.class)
  public void testNegativeGetStockAmount() {
    assertEquals(2.655161142349243,
            instance.getStockAmount(-400, "AAPL", "2022-11-01"), 0.2);
  }

  @Test (expected = AssertionError.class)
  public void testWrongNameGetStockAmount() {
    assertEquals(2.655161142349243,
            instance.getStockAmount(400, "HELLO", "2022-11-01"), 0.2);
  }

  @Test
  public void testInvestByWeight() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("AAPL", 16.343F);
    assertEquals("AAPL,0.4022645,2022-07-29,b,5",
            instance.investByWeight(400,stockMap,"2022-07-29", "5"));
  }

  @Test (expected = AssertionError.class)
  public void testWrongInvestByWeight() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("AAPL", 16.343F);
    assertEquals("AAP,0.4022645,2022-07-29,b,5",
            instance.investByWeight(400,stockMap,"2022-07-29", "5"));
    assertEquals("AAPL,0.4022645,2022-07-29,b,5",
            instance.investByWeight(-400,stockMap,"2022-07-29", "5"));
    assertEquals("AAPL,0.4022645,2022-07-29,b,5",
            instance.investByWeight(400,stockMap,"2089-07-29", "5"));
    assertEquals("AAPL,0.4022645,2022-07-29,b,5",
            instance.investByWeight(400,stockMap,"2022-07-29", "b"));
  }

  @Test
  public void testReadAndWriteDCA() {
    assertEquals("2022-07-29,2022-11-01,15,400,5,VZ:16.343",
            instance.readDCA("Shreya","dca"));
    assertNotEquals("idk", instance.readDCA("Shreya","dca"));
  }

  @Test
  public void testUpdateDCA() {
    String lineSep = System.lineSeparator();
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("VZ", 16.343F);
    instance.updateDCA(400, stockMap, "UpdateDCATest","2022-07-29",
            "2022-11-01", 15, "5", "portfolio_v2");
    assertEquals("VZ,1.4152848,2022-07-29,b,5" + lineSep +
            "VZ,1.4478848,2022-08-12,b,5" + lineSep +
            "VZ,1.5114914,2022-08-26,b,5" + lineSep +
            "VZ,1.5476326,2022-09-09,b,5" + lineSep +
            "VZ,1.6541498,2022-09-23,b,5" + lineSep +
            "VZ,1.7740028,2022-10-07,b,5" + lineSep +
            "VZ,1.8492788,2022-10-21,b,5", instance.readPortfolio("UpdateDCATest",
            "portfolio_v2"));
  }

  @Test (expected = AssertionError.class)
  public void testWrongUpdateDCA() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("META", 16.343F);
    instance.updateDCA(-400, stockMap, "UpdateWrongDCATest","2022-07-29",
            "2022-11-01", 15, "5", "portfolio_v2");
    assertEquals("VZ,1.4152848,2022-07-29,b,5\n" +
            "VZ,1.4478848,2022-08-12,b,5\n" +
            "VZ,1.5114914,2022-08-26,b,5\n" +
            "VZ,1.5476326,2022-09-09,b,5\n" +
            "VZ,1.6541498,2022-09-23,b,5\n" +
            "VZ,1.7740028,2022-10-07,b,5\n" +
            "VZ,1.8492788,2022-10-21,b,5", instance.readPortfolio("UpdateWrongDCATest",
            "portfolio_v2"));
  }

  @Test (expected = AssertionError.class)
  public void testNegativeUpdateDCA() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("VZ", 16.343F);
    instance.updateDCA(-400, stockMap, "UpdateNegativeDCATest","2022-07-29",
            "2022-11-01", 15, "5", "portfolio_v2");
    assertEquals("VZ,1.4152848,2022-07-29,b,5\n" +
            "VZ,1.4478848,2022-08-12,b,5\n" +
            "VZ,1.5114914,2022-08-26,b,5\n" +
            "VZ,1.5476326,2022-09-09,b,5\n" +
            "VZ,1.6541498,2022-09-23,b,5\n" +
            "VZ,1.7740028,2022-10-07,b,5\n" +
            "VZ,1.8492788,2022-10-21,b,5", instance.readPortfolio(
                    "UpdateNegativeDCATest", "portfolio_v2"));
  }

  @Test (expected = AssertionError.class)
  public void testWrongDateUpdateDCA() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("VZ", 16.343F);
    instance.updateDCA(-400, stockMap, "UpdateWrongDateDCATest","2092-07-29",
            "2052-11-01", 15, "5", "portfolio_v2");
    assertEquals("VZ,1.4152848,2022-07-29,b,5\n" +
            "VZ,1.4478848,2022-08-12,b,5\n" +
            "VZ,1.5114914,2022-08-26,b,5\n" +
            "VZ,1.5476326,2022-09-09,b,5\n" +
            "VZ,1.6541498,2022-09-23,b,5\n" +
            "VZ,1.7740028,2022-10-07,b,5\n" +
            "VZ,1.8492788,2022-10-21,b,5", instance.readPortfolio(
            "UpdateWrongDateDCATest", "portfolio_v2"));
  }

  @Test
  public void testDollarCostAveraging() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    instance.checkStockName("VZ");
    stockMap.put("VZ", 16.343F);
    instance.dollarCostAveraging(400, stockMap,"Shreya", "2022-07-29",
            "2022-11-01", 15, "5", "portfolio_v2");
    assertEquals("2022-07-29,2022-11-01,15,400,5,VZ:16.343",
            instance.readDCA("Shreya", "dca"));
  }

  @Test(expected = AssertionError.class)
  public void testNegativeDollarCostAveraging() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("VZ", 16.343F);
    instance.dollarCostAveraging(-400, stockMap,"Shreya2", "2092-07-29",
            "2062-11-01", 15, "5", "portfolio_v2");
    assertEquals("2022-07-29,2022-11-01,15,400,5,VZ:16.343",
            instance.readDCA("Shreya2", "dca"));
  }

  @Test
  public void testValidDCAPercentage() {
    LinkedHashMap<String, Float> stockMap = new LinkedHashMap<>();
    stockMap.put("META", 16.343F);
    assertFalse(instance.validDCAPercentage(stockMap));

    LinkedHashMap<String, Float> stockMap2 = new LinkedHashMap<>();
    stockMap2.put("VZ", 100F);
    assertTrue(instance.validDCAPercentage(stockMap2));

    LinkedHashMap<String, Float> stockMap3 = new LinkedHashMap<>();
    stockMap3.put("META", -16.343F);
    assertFalse(instance.validDCAPercentage(stockMap3));
  }
}