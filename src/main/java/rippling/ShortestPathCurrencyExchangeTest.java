package rippling;

import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class ShortestPathCurrencyExchangeTest {

    @Test
    public void testAdjList() {

        Currency[] currencies  = {

                new Currency("USD", "JPY", 110),
                new Currency("USD", "AUD", 1),
                new Currency("AUD", "JPY", 4),
                new Currency("USD", "MOS", 1),
                new Currency("MOS", "JPY", 2)
        };

        CurrencyConverter currencyConverter = new CurrencyConverter();
        Map<String, Map<String, Double>> exchangeRateTable = currencyConverter.prepareAdjList(currencies);
        Assert.assertNotNull(exchangeRateTable);

        Map<String, Double> audRates = exchangeRateTable.get("AUD");
        Assert.assertEquals((Double) 4.0, audRates.get("JPY"));
        Assert.assertEquals((Double) 1.0, audRates.get("USD"));

        Map<String, Double> mosRates = exchangeRateTable.get("MOS");
        Assert.assertEquals((Double) 2.0, mosRates.get("JPY"));
        Assert.assertEquals((Double) 1.0, mosRates.get("USD"));

        Map<String, Double> jpyRates = exchangeRateTable.get("JPY");
        Assert.assertEquals((Double) 0.25, jpyRates.get("AUD"));
        Assert.assertEquals((Double) 0.5, jpyRates.get("MOS"));
        Assert.assertEquals((Double) 0.00909090909090909, jpyRates.get("USD"));

        Map<String, Double> usdRates = exchangeRateTable.get("USD");
        Assert.assertEquals((Double) 1.0, usdRates.get("AUD"));
        Assert.assertEquals((Double) 1.0, usdRates.get("MOS"));
        Assert.assertEquals((Double) 110.0, usdRates.get("JPY"));
    }

    @Test
    public void testShortestPath() {

        Currency[] currencies  = {

                new Currency("USD", "JPY", 110),
                new Currency("USD", "AUD", 1),
                new Currency("AUD", "JPY", 4),
                new Currency("USD", "MOS", 1),
                new Currency("MOS", "JPY", 2)
        };

        CurrencyConverter currencyConverter = new CurrencyConverter();
        Map<String, Map<String, Double>> exchangeRateTable = currencyConverter.prepareAdjList(currencies);
        Assert.assertNotNull(exchangeRateTable);

        Double exchangeRate = currencyConverter.getExchangeRate("USD", "JPY", exchangeRateTable);
        Assert.assertEquals((Double) 110.0, exchangeRate);

        exchangeRate = currencyConverter.getExchangeRate("AUD", "MOS", exchangeRateTable);
        Assert.assertEquals((Double) 2.0, exchangeRate);
    }
}
