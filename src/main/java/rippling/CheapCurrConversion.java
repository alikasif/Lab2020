package rippling;


import java.util.*;

class Rate1 {
    String fromCurrency;
    String toCurrency;
    double amount;

    public Rate1(String fromCurrency, String toCurrency, double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }
}

class Edge1 implements Comparable<Edge1> {
    String toCurrency;
    String fromCurrency;
    double cost;

    public Edge1(String fromCurrency, String toCurrency, double cost) {
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge1 o) {
        if (this.cost == o.cost) {
            return 1;
        } else {
            return Double.compare(this.cost, o.cost);
        }
    }
}

class Solution1 {
    Map<String, Map<String, Double>> graph = new HashMap<>();

    public double solve(List<Rate1> rates, String from, String to) {

        buildGraph(rates);

        if (!graph.containsKey(from)) {
            return -1;
        }

        Queue<Edge1> queue = new PriorityQueue<>();

        queue.add(new Edge1(from, from, 1.0));

        Map<String, Double> fromToAllCostMap = new HashMap<>();

        Map<String, String> parent = new HashMap<>();

        parent.put(from, from);

        while (!queue.isEmpty()) {

            System.out.println();
            System.out.println(graph);
            System.out.println(fromToAllCostMap);
            System.out.println(parent);
            System.out.println();

            Edge1 edge = queue.poll();
            String fromCurr = edge.fromCurrency;

            if (fromToAllCostMap.containsKey(fromCurr) && fromToAllCostMap.get(fromCurr) < edge.cost) {
                continue;
            }

            fromToAllCostMap.put(fromCurr, edge.cost); // update min cost
            graph.get(from).put(fromCurr, edge.cost); // update edge weights

            for (var dest : graph.get(fromCurr).entrySet()) {
                double rate = graph.get(from).get(fromCurr) * dest.getValue();

                if (fromToAllCostMap.containsKey(dest.getKey()) && fromToAllCostMap.get(dest.getKey()) >= rate || dest.getKey().equals(to)) {
                    continue;
                }
                queue.add(new Edge1(fromCurr, dest.getKey(), rate));
            }
            parent.put(fromCurr, edge.toCurrency);
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

    private void buildGraph(List<Rate1> rates) {

        for (Rate1 rate : rates) {
            if (!graph.containsKey(rate.fromCurrency)) {
                graph.put(rate.fromCurrency, new HashMap<>());
            }

            graph.get(rate.fromCurrency).put(rate.fromCurrency, 1.0);

            graph.get(rate.fromCurrency).put(rate.toCurrency, rate.amount);
        }

        for (Rate1 rate : rates) {
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

public class CheapCurrConversion {

    public static void main(String[] args) {

        Solution1 solution = new Solution1();

        List<Rate1> rates = List.of(
                new Rate1("USD", "JPY", 110),
                new Rate1("USD", "AUD", 1),
                new Rate1("AUD", "MOS", 4),
                new Rate1("MOS", "JPY", 2)
        );

        String from = "MOS";
        String to = "USD";

        double result = solution.solve(rates, from, to);

        System.out.println(result);
    }
}