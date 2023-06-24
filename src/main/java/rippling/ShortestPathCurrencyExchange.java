package rippling;

import java.util.*;

class Currency {

    String from;
    String to;
    double rate;

    public Currency(String from, String to, double r) {
        this.from = from;
        this.to = to;
        this.rate = r;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                '}';
    }
}

class Pair implements Comparable<Pair> {
    String currency;
    Double rate;

    Pair(String s,Double d){
        this.currency =s;
        this.rate =d;
    }

    @Override
    public int compareTo(Pair o) {
        if(o.rate.equals(this.rate))
            return 0;
        return Double.compare(this.rate, o.rate);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}

class CurrencyConverter {
    //Map<String, Map<String, Double>> table = new HashMap<>();
    public Map<String, Map<String, Double>> prepareAdjList(Currency[] currencies) {

        Map<String, Map<String, Double>> table = new HashMap<>();

        for(Currency currency : currencies) {
            Map<String, Double> doubleMap = table.getOrDefault(currency.from, new HashMap<>());

            doubleMap.put(currency.to, currency.rate);
            table.put(currency.from, doubleMap);

            Map<String, Double> reverseDoubleMap = table.getOrDefault(currency.to, new HashMap<>());

            reverseDoubleMap.put(currency.from, 1/currency.rate);
            table.put(currency.to, reverseDoubleMap);
        }
        System.out.println(table);
        return table;
    }

    public Double getExchangeRate(String source, String target, Map<String, Map<String, Double>> table) {

        if(source.equals(target))
            return 1.0;

        // adding each currency with the rate calculated till here
        Queue<Pair> queue = new LinkedList<>();

        // visited to keep track of already visited currency
        Set<String> visited=new HashSet<>();

        queue.add(new Pair(source,1.0));
        visited.add(source);

        while(!queue.isEmpty()) {

            Pair poll = queue.poll();

            // if curr currency equals to target then return ans
            if((poll.currency).equals(target))
                return poll.rate;

            // if the curr currency has neighbor which is equal to the target then return directly without processing further
            if(table.get(poll.currency).containsKey(target)) {
                return poll.rate * (table.get(poll.currency).get(target));
            }

            // else add all its neighbors multiplied by the rate calculated until now
            for(Map.Entry<String, Double> entry : table.get(poll.currency).entrySet()) {
                if(!visited.contains(entry.getKey())) {
                    //Double currRate = table.get(poll.currency).get(key);
                    queue.add(new Pair(entry.getKey(),poll.rate * entry.getValue()));
                    visited.add(entry.getKey());
                }
            }
        }
        return 0.0;
    }

    public Double getCheapestExchangeRate(String source, String target, Map<String, Map<String, Double>> table) {

        if( !table.containsKey(source) || !table.containsKey(target))
            return 0.0;

        if(source.equals(target))
            return 1.0;

        Map<String, Double> minCostMap = new HashMap<>();

        // adding each currency with the rate calculated till here
        Queue<Pair> queue = new PriorityQueue<>();
        queue.add(new Pair(source,1.0));

        while(!queue.isEmpty()) {

            Pair poll = queue.poll();

            if (minCostMap.containsKey(poll.currency) && minCostMap.get(poll.currency) <= poll.rate) {
                continue;
            }

            minCostMap.put(poll.currency, poll.rate); // update min cost
            table.get(source).put(poll.currency, poll.rate); // update/add edge weights from source to this currency

            //double currRate = table.get(source).get(poll.currency); // cost of reaching until this currency

            // else add all its neighbors multiplied by the rate calculated until now
            for(Map.Entry<String, Double> dest : table.get(poll.currency).entrySet()) {

                double newRate = poll.rate * dest.getValue(); // calculate cost of going from here to destination

                if ( dest.getKey().equals(source)) {
                    continue;   // take care in next iteration
                }

                if (minCostMap.containsKey(dest.getKey()) && minCostMap.get(dest.getKey()) > newRate)  {
                    System.out.println("min cost is more:  " + dest.getKey() + " " + newRate + " "+ minCostMap);
                    minCostMap.put(dest.getKey(), newRate);
                    //continue;
                }

                queue.add(new Pair(dest.getKey(), newRate)); // from -> to  with this new rate
                System.out.println(queue);
            }
        }
        if (!table.get(source).containsKey(target)) {
            return -1.0;
        }

        return table.get(source).get(target);
    }
}

public class ShortestPathCurrencyExchange {
    public static void main(String[] args) {

        Currency[] curr  = {

                new Currency("USD", "JPY", 110),
                new Currency("USD", "AUD", 1),
                new Currency("AUD", "JPY", 4),
                new Currency("USD", "MOS", 1),
                new Currency("MOS", "JPY", 2)

                /*new Currency("USD","JPY",113.6),
                new Currency("USD","AUD",1.39),
                new Currency("AUD","JPY",3.6),
                new Currency("JPY","GBP",0.0065),
                new Currency("INR","USD",0.013),
                new Currency("INR","ABC",0.001),
                new Currency("ABC","XYZ",0.001),
                new Currency("XYZ","AUD",0.01),
                new Currency("GBP","EUR",1.19)*/
                };
        /*Map<String, Map<String, Double>> currMap = prepareMap(curr);
        System.out.println(getExchangeRate("INR", "AUD", currMap));*/

        CurrencyConverter currencyConverter = new CurrencyConverter();
        Map<String, Map<String, Double>> stringMapMap = currencyConverter.prepareAdjList(curr);
        Double exchangeRate = currencyConverter.getExchangeRate("USD", "JPY", stringMapMap);
        System.out.println(exchangeRate);

        exchangeRate = currencyConverter.getCheapestExchangeRate("USD", "JPY", stringMapMap);
        System.out.println(exchangeRate);
    }
}