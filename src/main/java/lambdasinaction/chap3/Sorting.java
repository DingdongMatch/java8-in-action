package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author matchfu
 */
public class Sorting {

    public static void main(String[] args) {
        // 1、传递代码
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red")));
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        inventory.set(1, new Apple(30, "green"));

        // 2、使用匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        System.out.println(inventory);

        inventory.set(1, new Apple(20, "red"));

        // 3、使用Lambda 表达式
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        System.out.println(inventory);

        inventory.set(1, new Apple(10, "red"));

        // 4、使用方法引用
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
