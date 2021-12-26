package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author matchfu
 */
public class PuttingIntoPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> tr2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011);

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> trades = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(trades);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // Query 5: Are there any trader based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(milanBased);

        // Query 6: 打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // Query 7: What's the highest value in all the transactions?
        int highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(highestValue);

        // Query 8: 找到交易额最小的交易
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        smallestTransaction.ifPresent(System.out::println);

    }
}
