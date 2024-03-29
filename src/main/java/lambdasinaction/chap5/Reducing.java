package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static lambdasinaction.chap4.Dish.menu;

/**
 * @author matchfu
 */
public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Integer sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        Integer max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        Integer calories = menu.stream().map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories: " + calories);
    }
}
