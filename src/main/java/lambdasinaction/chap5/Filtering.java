package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lambdasinaction.chap4.Dish.menu;
/**
 * @author matchfu
 */
public class Filtering {
    public static void main(String[] args) {
        // Filtering with predicate
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianMenu.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++");
        // Filtering unique element
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++");
        // Truncating a stream
        List<Dish> dishesLimit3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        dishesLimit3.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++");
        // Skipping elements
        List<Dish> dishesSkip2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        dishesSkip2.forEach(System.out::println);
    }
}
