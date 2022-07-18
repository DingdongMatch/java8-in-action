package lambdasinaction.chap6;

import java.util.*;
import java.util.stream.Collectors;

public class Grouping {
    enum CaloricLevel {DEIT, NORMAL, FAT}

    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return Dish.menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DEIT;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return Dish.menu.stream().collect(Collectors
                .groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DEIT;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return Dish.menu.stream().collect(Collectors
                .groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DEIT;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, Collectors.toSet())));
    }
}
