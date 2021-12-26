package lambdasinaction.chap1.verbiage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author matchfu
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println(greenApples);
        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println(heavyApples);

        /** 匿名类 */
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApples);
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
