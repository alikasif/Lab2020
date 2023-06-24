package reipplingpractise;

import java.util.*;

class Currency {

    String from;
    String to;
    Double rate;

    public Currency(String f, String t, Double r) {
        from = f;
        to = t;
        rate = r;
    }
}

class Pair implements Comparable<Pair>{
    String currency;
    Double rate;

    public Pair(String c, Double d) {
        currency = c;
        rate = d;
    }

    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.rate, o.rate);
    }
}

class CurrencyExchange {

    public Map<String, Map<String, Double>> prepareCurrencyAdjList(List<Currency> currencies) {

        Map<String, Map<String, Double>> currMap = new HashMap<>();

        for(Currency c : currencies) {
            Map<String, Double> doubleMap = currMap.getOrDefault(c.from, new HashMap<>());
            doubleMap.put(c.to, c.rate);
            currMap.put(c.from, doubleMap);

            Map<String, Double> doubleMapRev = currMap.getOrDefault(c.to, new HashMap<>());
            doubleMapRev.put(c.from, 1/c.rate);
            currMap.put(c.to, doubleMapRev);
        }
        return currMap;
    }

    public Double getShortestPath(String source, String dest, Map<String, Map<String, Double>> currMap) {

        if(source.equals(dest))
            return 1.0;

        if(!currMap.containsKey(source) || !currMap.containsKey(dest))
            return 0.0;

        Queue<Pair> queue  = new LinkedList<>();
        queue.add(new Pair(source, 1.0));
        Set<String> visited = new HashSet<>();
        visited.add(source);

        while (!queue.isEmpty()) {

            Pair poll = queue.poll();
            if(poll.currency.equals(dest))
                return poll.rate;

            Map<String, Double> stringDoubleMap = currMap.get(poll.currency);
            for(Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {
                if( !visited.contains(entry.getKey())) {
                    visited.add(entry.getKey());
                    queue.add(new Pair(entry.getKey(), poll.rate * entry.getValue()));
                }
            }
        }
        return 0.0;
    }

    public Double getCheapestRate(String source, String dest, Map<String, Map<String, Double>> currMap) {

        if(source.equals(dest))
            return 1.0;

        if(!currMap.containsKey(source) || !currMap.containsKey(dest))
            return 0.0;

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair(source, 1.0));
        Map<String, Double> minRatemap = new HashMap<>();

        while (!priorityQueue.isEmpty()) {

            Pair poll = priorityQueue.poll();

            if(minRatemap.containsKey(poll.currency) && minRatemap.get(poll.currency) <= poll.rate)
                continue;

            minRatemap.put(poll.currency, poll.rate);
            currMap.get(source).put(poll.currency, poll.rate);

            Map<String, Double> stringDoubleMap = currMap.get(poll.currency);

            for(Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

                Double newRate = poll.rate * entry.getValue();

                if(entry.getKey().equals(source))
                    continue;

                if(minRatemap.containsKey(entry.getKey()) && minRatemap.get(entry.getKey()) > newRate)
                    minRatemap.put(entry.getKey(), newRate );

                priorityQueue.add(new Pair(entry.getKey(), newRate));
            }
        }
        return currMap.get(source).get(dest);
    }
}

public class CurrencyExchangePractise {
    public static void main(String[] args) {

        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "JPY", 110.0));
        currencies.add(new Currency("USD", "AUD", 1.0));
        currencies.add(new Currency("AUD", "JPY", 4.0));
        currencies.add(new Currency("USD", "MOS", 1.0));
        currencies.add(new Currency("MOS", "JPY", 2.0));

        CurrencyExchange currencyExchange = new CurrencyExchange();
        Map<String, Map<String, Double>> stringMapMap = currencyExchange.prepareCurrencyAdjList(currencies);
        System.out.println(stringMapMap);

        Double rate = currencyExchange.getShortestPath("USD", "JPY", stringMapMap);
        System.out.println(rate);

        rate = currencyExchange.getCheapestRate("USD", "JPY", stringMapMap);
        System.out.println(rate);

    }
}
