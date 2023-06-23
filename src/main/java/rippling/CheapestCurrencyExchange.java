package rippling;


import java.util.*;

class Rate {
    String fromCurrency;
    String toCurrency;
    double amount;

    public Rate(String fromCurrency, String toCurrency, double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }
}

class Edge implements Comparable<Edge> {
    String toCurrency;
    String fromCurrency;
    double cost;

    public Edge(String fromCurrency, String toCurrency, double cost) {
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.cost == o.cost) {
            return 1;
        } else {
            return Double.compare(this.cost, o.cost);
        }
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", cost=" + cost +
                '}';
    }
}

class Solution {
    Map<String, Map<String, Double>> graph = new HashMap<>();

    public double solve(List<Rate> rates, String from, String to) {

        buildGraph(rates);

        if (!graph.containsKey(from)) {
            return -1;
        }

        Map<String, String> parent = new HashMap<>();
        parent.put(from, from);

        Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(null, from, 1.0));
        Map<String, Double> minCostMap = new HashMap<>();

        while (!queue.isEmpty()) {

            System.out.println();
            System.out.println(graph);
            System.out.println(minCostMap);
            System.out.println(queue);
            System.out.println();

            Edge edge = queue.poll();
            String toCurrency = edge.toCurrency;

            if (minCostMap.containsKey(toCurrency) && minCostMap.get(toCurrency) < edge.cost) {
                continue;
            }

            minCostMap.put(edge.toCurrency, edge.cost); // update min cost
            graph.get(from).put(edge.toCurrency, edge.cost); // update edge weights

            double currRate = graph.get(from).get(toCurrency); // cost of reaching until this currency

            for (var dest : graph.get(toCurrency).entrySet()) {

                double newRate = currRate * dest.getValue(); // calculate cost of going from here to destination

                System.out.println(toCurrency + " " + dest.getKey() +" " + dest.getValue() +" "+ newRate);

                if ( dest.getKey().equals(from) ||  (minCostMap.containsKey(dest.getKey()) && minCostMap.get(dest.getKey()) >= newRate) ) {

                    continue;   // take care in next iteration
                }

                queue.add(new Edge(toCurrency, dest.getKey(), newRate)); // from -> to  with this new rate
            }
            parent.put(toCurrency, edge.fromCurrency);
        }

        if (!graph.get(from).containsKey(to)) {
            return -1;
        }

        System.out.println(getPath(parent, from, to));
        return graph.get(from).get(to);
    }

    private String getPath(Map<String, String> parent, String from, String to) {
        String current = to;
        System.out.println(current);
        Stack<String> res = new Stack<>();
        res.add(to);
        while (!parent.get(current).equals(from)) {
            current = parent.get(current);
            res.add(current);
        }

        StringBuilder stringBuilder = new StringBuilder();
        res.add(from);

        while (res.size() != 0) {

            stringBuilder.append(res.pop());
            if (res.size() > 0) {
                stringBuilder.append(" -> ");
            }
        }
        return stringBuilder.toString();
    }

    private void buildGraph(List<Rate> rates) {

        for (Rate rate : rates) {
            if (!graph.containsKey(rate.fromCurrency)) {
                graph.put(rate.fromCurrency, new HashMap<>());
            }

            graph.get(rate.fromCurrency).put(rate.fromCurrency, 1.0);

            graph.get(rate.fromCurrency).put(rate.toCurrency, rate.amount);
        }

        for (Rate rate : rates) {
            for (var val : graph.get(rate.fromCurrency).entrySet()) {
                if (!graph.containsKey(val.getKey())) {
                    graph.put(val.getKey(), new HashMap<>());
                }

                graph.get(val.getKey()).put(val.getKey(), 1.0);

                if (!graph.get(val.getKey()).containsKey(rate.fromCurrency)) {
                    graph.get(val.getKey()).put(rate.fromCurrency, 1.0 / val.getValue());
                }
            }
        }
        System.out.println(graph);
    }
}

public class CheapestCurrencyExchange {

    public static void main(String[] args) {

        Solution solution = new Solution();

        List<Rate> rates = List.of(
                new Rate("USD", "JPY", 110),
                new Rate("USD", "AUD", 1),
                new Rate("AUD", "JPY", 4),
                new Rate("AUD", "MOS", 4),
                new Rate("MOS", "JPY", 2)
        );

        String from = "USD";
        String to = "JPY";

        double result = solution.solve(rates, from, to);

        System.out.println(result);
    }
}