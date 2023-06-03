package salesforce;

import java.util.*;

class Exchange implements Comparable<Exchange> {

    String from;
    String to;
    double rate;

    public Exchange(String from, String to, double r) {
        this.from = from;
        this.to = to;
        this.rate = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return Objects.equals(from, exchange.from) && Objects.equals(to, exchange.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public int compareTo(Exchange o) {
        return (int)(this.rate - o.rate);
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

public class CurrencyExchange {
    public static void main(String[] args) {

        Map<String, List<Exchange>> map = new HashMap<>();

/*
        addCurrency(map, new Exchange("USD", "JPY", 1));
        addCurrency(map, new Exchange("USD", "AUD", 5));
        addCurrency(map, new Exchange("JPY", "GBP", 4));
*/

        addCurrency(map, new Exchange("GBP", "USD", .5));
        addCurrency(map, new Exchange("GBP", "JPY", .7));
        addCurrency(map, new Exchange("USD", "JPY", 1));
        addCurrency(map, new Exchange("JPY", "AUD", 2));

        for(Map.Entry<String, List<Exchange>> e: map.entrySet())
            System.out.println(e);
        System.out.println("finding....");
        PriorityQueue<Exchange> pq = new PriorityQueue<>();
        String from = "GBP";
        String to ="AUD";

        pq.add(new Exchange("" , "GBP", 0));
        Set<Exchange> visited = new HashSet<>();

        while (!pq.isEmpty()) {

            Exchange poll = pq.poll();
            System.out.println(poll);
            visited.add(poll);
            List<Exchange> exchanges = map.get(poll.to);
            for(Exchange ex : exchanges) {

                if(ex.to.equals(to)) {
                    System.out.println(" found => "+ (ex.rate + poll.rate));
                    return;
                }
                if(visited.contains(ex))
                    continue;
                pq.add(new Exchange(ex.from, ex.to, poll.rate + ex.rate));
            }
        }
    }

    private static void addCurrency(Map<String, List<Exchange>> map, Exchange exchange) {
        List<Exchange> exchangeList = map.getOrDefault(exchange.from, new ArrayList<>());
        exchangeList.add(exchange);
        map.put(exchange.from, exchangeList);

        List<Exchange> exchangeList2 = map.getOrDefault(exchange.to, new ArrayList<>());
        double d = 1/exchange.rate;
        exchangeList2.add(new Exchange(exchange.to, exchange.from,  d));
        map.put(exchange.to, exchangeList2);
    }
}
